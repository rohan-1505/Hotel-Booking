package com.example.hotel.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HotelSearchRequestDTO {
	private String city;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer roomscount;
	private Integer page=0;
	private Integer size=10 ;
}
