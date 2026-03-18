package com.example.hotel.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data


public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
//	so like when we creating room we will be mentioning the hotel and new column in Root will be created by name of hotel_id 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hotel_id")  //in Db it will be hotel_id
	private Hotel hotel;
	
	
	private String type;
	
	
	private BigDecimal basePrice;

	@ElementCollection
	private List<String> photos;

	@ElementCollection
	private List<String> amenities;
	
	private Integer totalCount;
	
	private Integer capacity;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "room", cascade = jakarta.persistence.CascadeType.REMOVE, orphanRemoval = true)
	private List<inventory> inventories;
	
	
	

}
