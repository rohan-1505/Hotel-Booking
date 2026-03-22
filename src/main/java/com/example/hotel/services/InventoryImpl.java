package com.example.hotel.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.DTO.HotelSearchRequestDTO;
import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.inventory;
import com.example.hotel.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryImpl implements InventoryService{

    private final ModelMapper modelmapper;
	
	private final InventoryRepository invrepo;

 
	
	@Override
	public void initializeRoomForaYear(Room room) {
		
		LocalDate today= LocalDate.now();
		LocalDate enddate = today.plusYears(1);
		for(; !today.isAfter(enddate); today = today.plusDays(1)) { 
			inventory inv = inventory.builder()
					.hotel(room.getHotel())
					.room(room)
					.date(today)				
					.totalCount(room.getTotalCount())
					.bookedCount(0)
					.city(room.getHotel().getCity())
					.price(room.getBasePrice())
					.surgeFactor(BigDecimal.ONE)
					.closed(false)
							.build();
			invrepo.save(inv);
			log.info("Inventory initialized for room {} on date {}", room.getId(), today);
		}
		
	}

	@Override
	public Page<HotelDTO> searchHotels(HotelSearchRequestDTO hotelsearchrequest) {
		Pageable pageable = PageRequest.of(hotelsearchrequest.getPage(), hotelsearchrequest.getSize());
		Long datecount = ChronoUnit.DAYS.between(
			    hotelsearchrequest.getCheckInDate(),
			    hotelsearchrequest.getCheckOutDate()
			) + 1;
	Page<Hotel> hotelpage=	invrepo.findHotelswithAvailaleInventory(hotelsearchrequest.getCity(), hotelsearchrequest.getCheckInDate(),
				hotelsearchrequest.getCheckOutDate(), hotelsearchrequest.getRoomscount(), datecount, pageable);
		
		return hotelpage.map((elements)->modelmapper.map(elements, HotelDTO.class));
	}

//	@Override
//	public void deleteFutureInventories(Room room) {
//		LocalDateTime now = LocalDateTime.now();;		
//		invrepo.deleteByDateAfterAndRoom(now, room);
//	}
//	

}
