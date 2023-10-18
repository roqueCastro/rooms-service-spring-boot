package com.example.rooms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.rooms.model.Room;

public interface IRoomDao extends CrudRepository<Room, Long>{
	public List<Room> findByHotelId(long hotelId); 
}
