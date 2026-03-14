package com.example.hotel.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(
		uniqueConstraints = @UniqueConstraint(name= "unique_hotel_room_date",
		columnNames = {"hotel_id","room_id","date"
				})
		)
public class inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id")
	private Room room;	
	
	private LocalDateTime date;
	
	@Column(columnDefinition = "INTEGER DEFAULT 0")
	private Integer bookedCount;
	
	private Integer totalCount;
	
	@Column(precision = 5,scale = 2)
	private BigDecimal surgeFactor;
	
	@Column(precision = 5,scale = 2)
	private BigDecimal price;  //basePrice*surgeFactor
	
	private String city;
	
	private Boolean closed;
	
	@CreationTimestamp
	private LocalDate createdAt;
	
	@UpdateTimestamp
	private LocalDate updatedAt;
	
}
