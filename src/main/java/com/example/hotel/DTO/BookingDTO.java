package com.example.hotel.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.enums.BookingStatus;

import lombok.Data;
@Data

public class BookingDTO {

	
	private Long id;
	private Integer roomCount; 
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private BookingStatus bookingStatus;
	private Set<GuestDTO> guests;
}
