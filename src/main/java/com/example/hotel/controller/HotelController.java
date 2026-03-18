package com.example.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.entities.Room;
import com.example.hotel.services.HotelService;
import com.example.hotel.services.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/hotels")  //We initiallize the initial of every api in app.prop as /api/v1
@RequiredArgsConstructor
@Slf4j
public class HotelController {
	
	private final HotelService hotelservice;
	private final InventoryService inventoryservice;
	
	@PostMapping
	public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hoteldto){
		
		HotelDTO hotel= hotelservice.createNewHotel(hoteldto);
		return new ResponseEntity<>(hotel,HttpStatus.CREATED);
		
	}

	
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId){
		HotelDTO hotel= hotelservice.getHotelById(hotelId);
		return ResponseEntity.ok(hotel);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<HotelDTO> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDTO hoteldto){
		HotelDTO hotel= hotelservice.updateHotelById(hotelId, hoteldto);
		return ResponseEntity.ok(hotel);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
		 hotelservice.deleteHotelById(hotelId);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{hotelId}")
	public ResponseEntity<Void> activeHotel(@PathVariable Long hotelId){
		hotelservice.activateHotel(hotelId);
		return ResponseEntity.noContent().build();
		
	}
	

}
