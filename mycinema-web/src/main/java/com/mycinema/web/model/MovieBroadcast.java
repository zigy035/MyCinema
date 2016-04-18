package com.mycinema.web.model;

import java.util.Date;
import java.util.UUID;

public class MovieBroadcast {
	
	private String id;
	private String movieId;
	private String movieTitle;
	private String movieDescription;
	private String theatreId;
	private String theatreName;
	private Date broadcastDate;
	
	public MovieBroadcast() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
	
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

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
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

	public Date getBroadcastDate() {
		return broadcastDate;
	}

	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}
	
}
