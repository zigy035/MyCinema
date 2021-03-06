package com.mycinema.web.model;

import java.util.Date;

public class Booking {
	
	private String movieId;
	private String movieTitle;
	private String theatreId;
	private String theatreName;
	private String tickets;
	private Date broadcastDate;
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTickets() {
		return tickets;
	}
	public void setTickets(String tickets) {
		this.tickets = tickets;
	}
	public Date getBroadcastDate() {
		return broadcastDate;
	}
	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}
	
}
