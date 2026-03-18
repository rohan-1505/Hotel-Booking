package com.example.hotel.services;

import java.util.List;

import com.example.hotel.DTO.RoomDTO;

public interface RoomService {
	
	RoomDTO createNewRoom(RoomDTO roomdto, long hotelid);
	
	List<RoomDTO> getAllRoomsInHotel(long id);
	
	RoomDTO getRoomById(long id);
	
	void deleteRoomById(long id);

}
