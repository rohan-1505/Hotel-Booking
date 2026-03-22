package com.example.hotel.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.BookingDTO;
import com.example.hotel.DTO.BookingRequest;
import com.example.hotel.DTO.GuestDTO;
import com.example.hotel.entities.Booking;
import com.example.hotel.entities.Guest;
import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.entities.inventory;
import com.example.hotel.enums.BookingStatus;
import com.example.hotel.repository.BookingRepo;
import com.example.hotel.repository.GuestRepo;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.InventoryRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BookingServiceImpl	 implements BookingService {

    private final ModelMapper modelmapper;

	private final BookingRepo bookingRepo;
	private final HotelRepository hotelrepo;
	private final RoomRepository roomrepo;
	private final InventoryRepository inventoryrepo;
	private final UserRepo userRepo;
	private final GuestRepo guestRepo;
	

  
	@Override
	@Transactional
	public BookingDTO initialiseBooking(BookingRequest bookingrequest) {
		
		Hotel hotel =  hotelrepo.findById(bookingrequest.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found with id: " + bookingrequest.getHotelId()));
	
		Room room = roomrepo.findByIdAndHotelId(
		        bookingrequest.getRoomId(),
		        bookingrequest.getHotelId()
		).orElseThrow(() -> new RuntimeException("Room does not belong to this hotel"));
		
		List<inventory> invlist= inventoryrepo.findAndLockAvailableInventory(
				room.getId(), 
				bookingrequest.getCheckInDate(), 
				bookingrequest.getCheckOutDate(), 
				bookingrequest.getRoomsCount());
		
		long dayscount= ChronoUnit.DAYS.between(bookingrequest.getCheckInDate(), bookingrequest.getCheckOutDate()) + 1;
		if(invlist.size() != dayscount) {
			throw new RuntimeException("Not enough inventory available for the selected dates");
		}
		//Reserved the rooms/ Update the Booked count in inventory
		
		for(inventory inv: invlist) {
			inv.setReserveCount(inv.getReserveCount() + bookingrequest.getRoomsCount());
		}
		inventoryrepo.saveAll(invlist);
		
		

		//TODO: calculate dynamic price

		Booking booking = Booking.builder()
				.bookingStatus(BookingStatus.RESERVED)
				.hotel(hotel)
				.room(room)
				.checkInDate(bookingrequest.getCheckInDate())
				.checkOutDate(bookingrequest.getCheckOutDate())
				.user(getCurrentUser())
				.roomCount(bookingrequest.getRoomsCount())
				.amount(BigDecimal.TEN)
				.build();
		
		Booking savedbooking = bookingRepo.save(booking);
		return modelmapper.map(savedbooking, BookingDTO.class);				
	}



	@Override
	public BookingDTO addGuestToBooking(List<GuestDTO> guestDTOlist, Long bookingId) {
		Booking book =  bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking  not found with id: " + bookingId));
		if(hasBookingExpired(book)) {
			throw new RuntimeException("Booking has expired");
		}
		if(book.getBookingStatus()!=BookingStatus.RESERVED) {
			throw new RuntimeException("Booking is not under Reserved state, cant add Guest");

		}
		
		for(GuestDTO guestdto :  guestDTOlist) {
			Guest guest= modelmapper.map(guestdto, Guest.class);
			guest.setUser(getCurrentUser());
			guest= guestRepo.save(guest);
			book.getGuests().add(guest);
			
		}
		book.setBookingStatus(BookingStatus.GUEST_ADDED);
		book= bookingRepo.save(book);
		return modelmapper.map(book, BookingDTO.class);
	}
	
	
	
	
	
	
	public Boolean hasBookingExpired(Booking booking) {
	    return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
	}	
	
	public User getCurrentUser() {
				User user = userRepo.findById(1L)
				        .orElseThrow(() -> new RuntimeException("User not found"));
				return user;
	}

}
