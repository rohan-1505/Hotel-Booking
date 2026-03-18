package com.example.hotel.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.exception.ResourcenotFoundexception;
import com.example.hotel.repository.HotelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
	
	private final HotelRepository hotelrepo ;
	private final InventoryService inventoryservice;
	private final ModelMapper modelmapper;

	@Override
	public HotelDTO createNewHotel(HotelDTO hoteldto) {
		log.info("creating new hotel with name:{} ", hoteldto.getName());
		Hotel hotel= modelmapper.map(hoteldto, Hotel.class);
		hotel.setActive(false); //initially when hotel been created it should be not active later will do active
		hotel = hotelrepo.save(hotel);
		log.info("created a new hotel with name:{} ", hoteldto.getName());
		return modelmapper.map(hotel, HotelDTO.class);
		
		
	}

	@Override
	public HotelDTO getHotelById(long id) {
		log.info("Getting the hotel with id:{} ", id);
		Hotel hotel = hotelrepo
		.findById(id)
		.orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id" + id)));
		return  modelmapper.map(hotel, HotelDTO.class);

		
	}

	@Override
	public HotelDTO updateHotelById(long id, HotelDTO hoteldto) {
		Hotel hotel = hotelrepo
				.findById(id)
				.orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));
		modelmapper.map(hoteldto, hotel);
		hotel.setId(id);
		hotel = hotelrepo.save(hotel);
		return modelmapper.map(hotel, HotelDTO.class);
	}

	@Transactional
	public void deleteHotelById(long id) {
		Hotel hotel= hotelrepo.findById(id).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));
		hotelrepo.deleteById(id);
		for(Room room : hotel.getRooms()) {
			inventoryservice.deleteFutureInventories(room);
		}
	}

	@Transactional
	@Override
	public void activateHotel(long id) {
		Hotel hotel  = hotelrepo.findById(id).orElseThrow(() -> new ResourcenotFoundexception(("Hotel not found with id: " + id)));
		hotel.setActive(true);
		
		for (Room room : hotel.getRooms()) {
			inventoryservice.initializeRoomForaYear(room);
		}
		
	}


}
