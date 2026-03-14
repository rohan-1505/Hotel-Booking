	package com.example.hotel.entities;
	
	
	
	import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.hotel.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
	
	@Entity
	@Getter
	@Setter
	public class Booking {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="hotel_id")
		private Hotel hotel;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="room_id")
		private Room room;	
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="user_id")
		private User user;
		
		
		private Integer roomCount; //COunt the no of rooms to booked through one Id
		private LocalDateTime checkedInDate;
		private LocalDateTime checkedOutDate;
		
		@CreationTimestamp
		private LocalDateTime createdAt;
		
		@UpdateTimestamp
		private LocalDateTime updatedAt;
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "payment_id")
		private Payment payment;
		
		@Enumerated(EnumType.STRING)
		private BookingStatus bookingStatus;
		
		@ManyToMany
		@JoinTable(
		    name = "booking_guests",
		    joinColumns = @JoinColumn(name = "booking_id"),
		    inverseJoinColumns = @JoinColumn(name = "guest_id")
		)
		private Set<Guest> guests;
	}
