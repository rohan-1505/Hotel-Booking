package com.example.hotel.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class RoomDTO {

	private Long id;
	

	
	
	private String type;
	
	
	private BigDecimal basePrice;

	private List<String> photos;

	private List<String> amenities;
	
	private Integer totalCount;
	
	private Integer capacity;
	

}
