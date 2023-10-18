package com.example.rooms.services;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.rooms.dao.IRoomDao;
import com.example.rooms.model.Hotel;
import com.example.rooms.model.Room;
import com.example.rooms.model.RoomsHotel;
import com.example.rooms.services.client.HotelFeingClient;
import com.google.common.base.Optional;

@Service
public class IRoomServiceImpl implements IRoomService {
	
	@Autowired
	private IRoomDao roomDao;
	
//	@Autowired
//	private RestTemplate clientRest;
	
	@Autowired
	HotelFeingClient hotelFeingClient;

	public List<Room> search() {
		return (List<Room>) roomDao.findAll();
	}

	@Override
	public List<Room> searchRoomByHotelId(long hotelId) {
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		return rooms;
	}

	@Override
	public List<RoomsHotel> searchRoomWithHotelById(long hotelId) {
		List<RoomsHotel> roomsHotels = new ArrayList<>();
	
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		
//		****************REST TEMPLATE ***********
//		Map<String, Long> pathVariable = new HashMap<String, Long>();
//		pathVariable.put("id", hotelId);
//		
//		Hotel hotel = clientRest.getForObject("http://localhost:8081/hotels-one/{id}", Hotel.class, pathVariable);
		
//		****************FEING***********
		Hotel hotel = hotelFeingClient.searchHotelRelatedHotel(hotelId);
		
		//ASIGNAR NUEVA
		rooms.stream().forEach((r) -> {
			RoomsHotel roomsHotel = new RoomsHotel();
			roomsHotel.setHotelId(r.getHotelId());
			roomsHotel.setRoomAvailable(r.getRoomAvailable());
			roomsHotel.setRoomId(r.getRoomId());
			roomsHotel.setRoomName(r.getRoomName());
			
			roomsHotel.setHotel(hotel);
			
			roomsHotels.add(roomsHotel);
		});
		
		
		return roomsHotels;
	}
	
	
	@Override
	public List<RoomsHotel> searchRoomWithHotelOutById(long hotelId) {
		List<RoomsHotel> roomsHotels = new ArrayList<>();
	
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		
		//ASIGNAR NUEVA
		rooms.stream().forEach((r) -> {
			RoomsHotel roomsHotel = new RoomsHotel();
			roomsHotel.setHotelId(r.getHotelId());
			roomsHotel.setRoomAvailable(r.getRoomAvailable());
			roomsHotel.setRoomId(r.getRoomId());
			roomsHotel.setRoomName(r.getRoomName());
			
			roomsHotel.setHotel(null);
			
			roomsHotels.add(roomsHotel);
		});
		
		
		return roomsHotels;
	}


}
