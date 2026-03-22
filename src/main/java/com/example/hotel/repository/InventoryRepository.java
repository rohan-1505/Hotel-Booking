package com.example.hotel.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.hotel.entities.Hotel;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.inventory;

import jakarta.persistence.LockModeType;

public interface InventoryRepository extends JpaRepository<inventory, Long> {

	void deleteByDateAfterAndRoom(LocalDateTime now, Room room );
	
	@Query("""
			SELECT DISTINCT i.hotel FROM inventory i
			WHERE i.city = :city
				AND i.date BETWEEN :checkInDate AND :checkOutDate
				AND i.closed = false
				AND (i.totalCount - i.bookedCount-i.reserveCount) >= :roomscount   
				
				GROUP BY i.hotel
				HAVING COUNT(DISTINCT i.date) = :datecount
			""")
	
	//Here we are using a JPQL query to find distinct hotels that have available inventory for the specified criteria.
//	The query checks for hotels in the given city, with inventory dates between the check-in and check-out dates, 
//	that are not closed, and have enough available rooms (totalCount - bookedCount) to accommodate the requested room count.
//	here totalcount-bookedcount >= roomscount means that the hotel has enough available rooms and they should be more than we requested in the search criteria.
	
	org.springframework.data.domain.Page<Hotel> findHotelswithAvailaleInventory(
			String city, 
			LocalDate checkInDate,
			LocalDate checkOutDate, 
			int roomscount,
			Long datecount,
			Pageable pageable
			
			
			);
	
	@Query("""
			SELECT i FROM inventory i
			WHERE i.room.id = :roomId
				AND i.date BETWEEN :checkInDate AND :checkOutDate
				AND i.closed = false
				AND (i.totalCount - i.bookedCount-i.reserveCount) >= :roomscount   
			""")
	
	
	@Lock(LockModeType.PESSIMISTIC_WRITE) //This annotation is used to acquire a pessimistic write lock on the selected inventory records.
	List<inventory> findAndLockAvailableInventory(
			Long roomId,
			LocalDate checkInDate,
			LocalDate checkOutDate,
			Integer roomscount

	);
}
