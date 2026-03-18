package com.example.hotel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.exception.ResourcenotFoundexception;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class RoomServiceImpl  implements RoomService{
	
	private final RoomRepository roomrepo;
	private final ModelMapper modelmapper;
	private final HotelRepository hotelrepo;
	private final InventoryService inventoryservice;

	
	@Override
	public RoomDTO createNewRoom(RoomDTO roomdto, long hotelid) {
		log.info("creating the room in hotel with ID: {}", hotelid);
		Hotel hotel  = hotelrepo.findById(hotelid).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + hotelid)));
		Room room = modelmapper.map(roomdto, Room.class);
		room.setHotel(hotel);
		 room = roomrepo.save(room);
		 if(hotel.getActive()) {
			 inventoryservice.initializeRoomForaYear(room);
			 
		 }
		return modelmapper.map(room, RoomDTO.class);
		
		
		
		
	}

	@Override
	public List<RoomDTO> getAllRoomsInHotel(long id) {
		log.info("getting all rooms in Hotel with ID:  {}", id);

		Hotel hotel  = hotelrepo.findById(id).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));
		return hotel.getRooms()
				.stream()
				.map((element) -> modelmapper.map(element, RoomDTO.class))
				.collect(Collectors.toList());
		

		
	}

	@Override
	public RoomDTO getRoomById(long id) {
		log.info("getting the rooms with ID:  {}", id);
		Room room = roomrepo.findById(id).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));
		return modelmapper.map(room, RoomDTO.class);
	}

	@Transactional
	@Override
	public void deleteRoomById(long id) {
		log.info("getting the rooms with ID:  {}", id);
		Room room = roomrepo.findById(id).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));

		inventoryservice.deleteFutureInventories(room);
		roomrepo.deleteById(id);

		
	}

}
