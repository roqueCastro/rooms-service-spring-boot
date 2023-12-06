package com.example.rooms.services.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rooms.model.Reservation;

@FeignClient("reservations")
public interface ReservationFeingClient {
	@RequestMapping(method = RequestMethod.GET, value = "reservations-room-by-id/{roomId}", consumes = "application/json")
	public List<Reservation> searchReservationsRoomId(@PathVariable long roomId);
}
