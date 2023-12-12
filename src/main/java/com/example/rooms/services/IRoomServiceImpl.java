package com.example.rooms.services;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.rooms.dao.IRoomDao;
import com.example.rooms.model.Hotel;
import com.example.rooms.model.Reservation;
import com.example.rooms.model.Room;
import com.example.rooms.model.RoomsHotel;
import com.example.rooms.model.RoomsReservation;
import com.example.rooms.services.client.HotelFeingClient;
import com.example.rooms.services.client.ReservationFeingClient;

@Service
public class IRoomServiceImpl implements IRoomService {
	
	@Autowired
	private IRoomDao roomDao;
	
//	@Autowired
//	private RestTemplate clientRest;
	
	@Autowired
	HotelFeingClient hotelFeingClient;
	
	@Autowired
	ReservationFeingClient reservationFeingClient;

	public List<Room> search() {
		return (List<Room>) roomDao.findAll();
	}
	
	
	//------------------------- HOTEL -----------------------------------

	@Override
	public List<Room> searchRoomByHotelId(long hotelId) {
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		return rooms;
	}



	@Override
	public List<RoomsReservation> searchByIdHotelWithReservations(long hotelId) {
		
		List<RoomsReservation> roomsReservations = new ArrayList<>();
		
		
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		
		
//		****************FEING***********
		// List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(roomId);

		//ASIGNAR NUEVA
		rooms.stream().forEach((room) -> {
			RoomsReservation romReservation = new RoomsReservation();
			romReservation.setHotelId(room.getHotelId());
			romReservation.setRoomAvailable(room.getRoomAvailable());
			romReservation.setRoomId(room.getRoomId());
			romReservation.setRoomName(room.getRoomName());


			//CONSULTA LIST RESERVATIONS
			List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(room.getRoomId());
			
			romReservation.setReservations(reservations);


			roomsReservations.add(romReservation);
		});
	
		
		return roomsReservations;
	}



	@Override
	public List<RoomsReservation> searchByIdHotelOutReservations(long hotelId) {
		
		List<RoomsReservation> roomsReservations = new ArrayList<>();
		
		
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
		
		
//		****************FEING***********
		// List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(roomId);

		//ASIGNAR NUEVA
		rooms.stream().forEach((room) -> {
			RoomsReservation romReservation = new RoomsReservation();
			romReservation.setHotelId(room.getHotelId());
			romReservation.setRoomAvailable(room.getRoomAvailable());
			romReservation.setRoomId(room.getRoomId());
			romReservation.setRoomName(room.getRoomName());
			roomsReservations.add(romReservation);
		});
	
		
		return roomsReservations;
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

	
	//------------------------- RESERVATION -----------------------------------

	@Override
	public RoomsReservation searchRoomByIdWithReservation(long roomId) {
		
		Optional<Room> room = this.roomDao.findById(roomId);
		
		
//		****************FEING***********
		List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(roomId);
		
		RoomsReservation romReservation = new RoomsReservation();
		romReservation.setHotelId(room.get().getHotelId());
		romReservation.setRoomAvailable(room.get().getRoomAvailable());
		romReservation.setRoomId(room.get().getRoomId());
		romReservation.setRoomName(room.get().getRoomName());
		
		romReservation.setReservations(reservations);
	
		
		return romReservation;
	}
	
	
	@Override
	public RoomsReservation searchRoomByIdOutReservation(long roomId) {
		
		Optional<Room> room = this.roomDao.findById(roomId);
		
		
//		****************FEING***********
//		List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(roomId);
		
		RoomsReservation romReservation = new RoomsReservation();
		romReservation.setHotelId(room.get().getHotelId());
		romReservation.setRoomAvailable(room.get().getRoomAvailable());
		romReservation.setRoomId(room.get().getRoomId());
		romReservation.setRoomName(room.get().getRoomName());
		
//		romReservation.setReservations(reservations);
	
		
		return romReservation;
	}
	
	
//	@Override
//	public RoomsReservation searchRoomByIdOutReservation(long roomId) {
//		RoomsReservation roomsReservation = new RoomsReservation();
//		
//		Optional<Room> room = this.roomDao.findById(roomId);
//		
//		
////		****************FEING***********
//		List<Reservation> reservations = reservationFeingClient.searchReservationsRoomId(roomId);
//		
//		RoomsReservation romReservation = new RoomsReservation();
//		romReservation.setHotelId(room.get().getHotelId());
//		romReservation.setRoomAvailable(room.get().getRoomAvailable());
//		romReservation.setRoomId(room.get().getRoomId());
//		romReservation.setRoomName(room.get().getRoomName());
//		
//		roomsReservation.setReservations(reservations);
//	
//		
//		return roomsReservation;
//	}


}
