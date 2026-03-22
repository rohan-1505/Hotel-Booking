package com.example.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	Optional<Room> findByIdAndHotelId(Long roomId, Long hotelId);

}
