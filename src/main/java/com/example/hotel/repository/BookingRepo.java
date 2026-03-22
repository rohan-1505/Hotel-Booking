package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
