package com.example.hotel.services;

import org.jspecify.annotations.Nullable;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.DTO.HotelInfoDTO;
import com.example.hotel.entities.Hotel;

public interface HotelService {
	
	HotelDTO createNewHotel(HotelDTO hoteldto);
	
	HotelDTO getHotelById(long id);
	
	HotelDTO updateHotelById(long id, HotelDTO hoteldto);
	
	void deleteHotelById(long id);
	
	void activateHotel(long id);

	HotelInfoDTO getHotelInfoById(Long hotelid);

	

}
