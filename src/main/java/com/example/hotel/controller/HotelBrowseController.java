package com.example.hotel.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.HotelDTO;
import com.example.hotel.DTO.HotelInfoDTO;
import com.example.hotel.DTO.HotelSearchRequestDTO;
import com.example.hotel.services.HotelService;
import com.example.hotel.services.HotelServiceImpl;
import com.example.hotel.services.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hotels")  //We initiallize the initial of every api in app.prop as /api/v1
@RequiredArgsConstructor
public class HotelBrowseController {

    private final HotelServiceImpl hotelServiceImpl;
	
	
	private final InventoryService invservice;
	private final HotelService hotelservice;



	
	
	@PostMapping("/search")
	public ResponseEntity<Page<HotelDTO>> searchHotels(
	        @RequestBody HotelSearchRequestDTO hotelsearchrequest) {

	    Page<HotelDTO> page = invservice.searchHotels(hotelsearchrequest);
	    return ResponseEntity.ok(page);
	}
	
	
	@GetMapping("/{hotelid}/info")
	public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelid){
		return ResponseEntity.ok(hotelservice.getHotelInfoById(hotelid));
		
		
		
	}

}
