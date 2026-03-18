package com.example.hotel.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entities.Room;
import com.example.hotel.entities.inventory;

public interface InventoryRepository extends JpaRepository<inventory, Long> {

	void deleteByDateAfterAndRoom(LocalDateTime now, Room room );
}
