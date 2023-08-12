package com.example.rooms.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.rooms.model.Room;

public interface IRoomDao extends CrudRepository<Room, Long>{

}
