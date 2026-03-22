package com.example.hotel.services;

import org.springframework.data.domain.Page;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.DTO.HotelSearchRequestDTO;
import com.example.hotel.entities.Room;

public interface InventoryService {
	
	void initializeRoomForaYear(Room room);

	Page<HotelDTO> searchHotels(HotelSearchRequestDTO hotelsearchrequest);
	
	
	

}
