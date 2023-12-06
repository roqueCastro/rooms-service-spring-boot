package com.example.rooms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.rooms.config.RoomServiceConfiguraction;
import com.example.rooms.model.PropertiesRoom;
import com.example.rooms.model.Room;
import com.example.rooms.model.RoomsHotel;
import com.example.rooms.model.RoomsReservation;
import com.example.rooms.services.IRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class RoomController  {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private IRoomService service;
	
	@Autowired
	private RoomServiceConfiguraction configRoom;

	
	@GetMapping("rooms")
	public List<Room> search() {
		logger.info("Inicio del método search");
		return (List<Room>) service.search();
	}
	
	
	@GetMapping("rooms/{id}")
	public List<Room> searchRoomsByHotelId(@PathVariable long id) {
		logger.info("Inicio del método searchRoomsByHotelId");
		return (List<Room>) service.searchRoomByHotelId(id);
	}
	
	
	//--------------------- HOTEL ----------------------
	
	
	@GetMapping("rooms-with-hotel/{id}")
	@Retry(name="searchRoomsWithHotelByIdSupportRetry", fallbackMethod = "searchRoomsWithHotelByIdAlternative")
	public List<RoomsHotel> searchRoomsWithHotelById(@PathVariable long id) {
		logger.info("Inicio del método searchRoomsWithHotelById");
		return (List<RoomsHotel>) service.searchRoomWithHotelById(id);
	}
	

	@GetMapping("rooms-one/{id}")
	public List<RoomsHotel> searchRoomsWithHotelByIdAlternative(@PathVariable long id) {
		logger.info("Inicio del método searchRoomsWithHotelByIdAlternative");
		return (List<RoomsHotel>) service.searchRoomWithHotelOutById(id);
	}
	
	
	
	//--------------------- RESERVATION ----------------------
	
	@GetMapping("room-by-id-with-reservations/{id}")
	public RoomsReservation searchRoomByIdWithReservations(@PathVariable long id) {
		return service.searchRoomByIdWithReservation(id);
	}
	
	
	
	
	
	@GetMapping("rooms/read/properties")
	private String getPropertiesRooms() throws JsonProcessingException {
		ObjectWriter obj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		PropertiesRoom propertiesRoom = new PropertiesRoom(configRoom.getMsg(), configRoom.getBuildVersion(),
				                         configRoom.getMailDetails());
		
		String jsonString = obj.writeValueAsString(propertiesRoom);
		
		return jsonString;

	}
	
	
}
