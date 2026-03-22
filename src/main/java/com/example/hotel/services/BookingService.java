package com.example.hotel.services;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.hotel.DTO.BookingDTO;
import com.example.hotel.DTO.BookingRequest;
import com.example.hotel.DTO.GuestDTO;

public interface BookingService {

	BookingDTO initialiseBooking(BookingRequest bookingrequest);

	BookingDTO addGuestToBooking(List<GuestDTO> guestDTO, Long bookingId);

	
	
	

}
