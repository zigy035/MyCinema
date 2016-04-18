package com.mycinema.web.model;

import java.util.UUID;

public class Ticket {
	
	private String id;
	private String movieBroadcastId;
	private String authUserId;
	private String seatRow;
	private String seatColumn;
	
	public Ticket() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieBroadcastId() {
		return movieBroadcastId;
	}

	public void setMovieBroadcastId(String movieBroadcastId) {
		this.movieBroadcastId = movieBroadcastId;
	}

	public String getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}

	public String getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	public String getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(String seatColumn) {
		this.seatColumn = seatColumn;
	}
	
}
