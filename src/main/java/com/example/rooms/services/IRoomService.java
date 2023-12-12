package com.example.rooms.services;

import java.util.List;

import com.example.rooms.model.Room;
import com.example.rooms.model.RoomsHotel;
import com.example.rooms.model.RoomsReservation;

public interface IRoomService {
	List<Room> search();
	List<Room> searchRoomByHotelId(long hotelId);

	//ROOM HOTEL BY ID WITH RESERVATIONS
	List<RoomsReservation> searchByIdHotelWithReservations(long hotelId);
	List<RoomsReservation> searchByIdHotelOutReservations(long hotelId);
	
	//HOTEL
	List<RoomsHotel> searchRoomWithHotelById(long hotelId);
	List<RoomsHotel> searchRoomWithHotelOutById(long hotelId);
	
	//RESERVATIONS
	RoomsReservation searchRoomByIdWithReservation(long roomId);
	RoomsReservation searchRoomByIdOutReservation(long roomId);
}
