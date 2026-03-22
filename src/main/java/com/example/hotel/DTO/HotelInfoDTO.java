package com.example.hotel.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelInfoDTO {
	private HotelDTO hotel;
	private List<RoomDTO> rooms;
	

}
