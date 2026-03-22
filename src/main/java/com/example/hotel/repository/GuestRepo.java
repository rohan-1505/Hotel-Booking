package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entities.Guest;

public interface GuestRepo extends JpaRepository<Guest, Long> {

}
