package com.example.rooms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rooms.model.Room;

@FeignClient("rooms")
public interface RoomFeingClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "rooms-one/{id}")
	public Room searchRoomRelatedRoom(@PathVariable long id);

}
