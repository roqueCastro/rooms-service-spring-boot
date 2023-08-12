package com.example.rooms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rooms.dao.IRoomDao;
import com.example.rooms.model.Room;

@Service
public class IRoomServiceImpl implements IRoomService {
	
	@Autowired
	private IRoomDao roomDao;

	@Override
	public List<Room> search() {
		return (List<Room>) roomDao.findAll();
	}
}
