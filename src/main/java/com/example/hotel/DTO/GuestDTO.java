package com.example.hotel.DTO;

import com.example.hotel.entities.User;
import com.example.hotel.enums.Gender;

import lombok.Data;

@Data
public class GuestDTO {
	private Long id;
	

	private User user;
	
	private String name;
	

	private Gender gender;
	
	private Integer age;
	

}
