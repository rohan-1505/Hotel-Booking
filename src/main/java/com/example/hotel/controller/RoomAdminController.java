package com.example.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.services.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels/{hotelId}/rooms")
public class RoomAdminController {
	
	private final RoomService roomservice;
	
	@PostMapping
	public ResponseEntity<RoomDTO> createNewRoom(@RequestBody RoomDTO roomdto, @PathVariable Long hotelId){
		RoomDTO room = roomservice.createNewRoom(roomdto, hotelId);
		return new ResponseEntity<>(room,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<RoomDTO>> getAllRoomsInHotel(@PathVariable Long hotelId){
		List<RoomDTO> rooms = roomservice.getAllRoomsInHotel(hotelId);
		return ResponseEntity.ok(rooms);
		
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId , @PathVariable Long hotelId){
		RoomDTO room = roomservice.getRoomById(roomId);
		return ResponseEntity.ok(room);
		
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId , @PathVariable Long hotelId){
		roomservice.deleteRoomById(roomId);
		return ResponseEntity.noContent().build();
		
	}
}
