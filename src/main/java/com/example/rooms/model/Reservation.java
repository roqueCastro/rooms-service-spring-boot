package com.example.rooms.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Reservation {
	
	private long reservationId;
	private long rommId;
	private Date startDt;
	private Date finishDt;
}
