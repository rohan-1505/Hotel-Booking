package com.example.hotel.DTO;

import java.util.List;

import com.example.hotel.entities.HotelContactInfo;

import lombok.Data;

@Data
public class HotelDTO  {
	
	private Long id;
	
	private String name;
	
	private String city;
	
	private List<String> photos;

	private List<String> amenities;
	
	private Boolean active;
	
	
	private HotelContactInfo contactinfo;
	
}



