package com.example.rooms.model;
import lombok.Data;

@Data
public class RoomsHotel {
	private long roomId;
	private long hotelId;
	private String roomName;
	private String roomAvailable;
	private Hotel hotel;
}
