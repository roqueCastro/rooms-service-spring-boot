package com.example.rooms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rooms.config.RoomServiceConfiguraction;
import com.example.rooms.model.PropertiesRoom;
import com.example.rooms.model.Room;
import com.example.rooms.services.IRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class RoomController  {

	@Autowired
	private IRoomService service;
	
	@Autowired
	private RoomServiceConfiguraction configRoom;

	
	@GetMapping("rooms")
	public List<Room> search() {
		return (List<Room>) service.search();
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
