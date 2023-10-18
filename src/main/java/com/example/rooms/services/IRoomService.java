package com.example.rooms.services;

import java.util.List;

import com.example.rooms.model.Room;
import com.example.rooms.model.RoomsHotel;

public interface IRoomService {
	List<Room> search();
	List<Room> searchRoomByHotelId(long hotelId);
	
	List<RoomsHotel> searchRoomWithHotelById(long hotelId);
	List<RoomsHotel> searchRoomWithHotelOutById(long hotelId);
}
