package com.example.hotel.services;

import com.example.hotel.entities.Room;

public interface InventoryService {
	
	void initializeRoomForaYear(Room room);
	
	void deleteFutureInventories(Room room);//we used cascade
	
	

}
