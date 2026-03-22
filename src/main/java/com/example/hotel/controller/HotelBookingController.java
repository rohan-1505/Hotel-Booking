package com.example.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.BookingDTO;
import com.example.hotel.DTO.BookingRequest;
import com.example.hotel.DTO.GuestDTO;
import com.example.hotel.services.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {
	
	private final BookingService bookingService;
	
	
	@PostMapping("/initialise")
	public ResponseEntity<BookingDTO> initialiseBooking(@RequestBody BookingRequest bookingrequest){
			return ResponseEntity.ok(bookingService.initialiseBooking(bookingrequest));
		
		
	}
	
	@PostMapping("/{bookingId}/addGuest")
	public ResponseEntity<BookingDTO> addGuestToBooking(@RequestBody List<GuestDTO> guestDTOlist, @PathVariable Long bookingId){
		return ResponseEntity.ok(bookingService.addGuestToBooking(guestDTOlist, bookingId));
	}
}
