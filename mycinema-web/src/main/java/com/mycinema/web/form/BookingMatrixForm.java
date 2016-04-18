package com.mycinema.web.form;

import java.util.List;

public class BookingMatrixForm {
	
	private List<String> seats;
	private String movieBroadcastId;
	
	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public String getMovieBroadcastId() {
		return movieBroadcastId;
	}

	public void setMovieBroadcastId(String movieBroadcastId) {
		this.movieBroadcastId = movieBroadcastId;
	}
	
}
