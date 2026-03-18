package com.example.hotel.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.hotel.entities.HotelContactInfo;
import com.example.hotel.entities.Room;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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



