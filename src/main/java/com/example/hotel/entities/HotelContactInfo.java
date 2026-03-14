package com.example.hotel.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Embeddable

public class HotelContactInfo {
	
	private String address;
	private String phoneNo;
	private String email;
	private String location;
	
	
	

}
