package com.mycinema.web.form;

public class MovieBroadcastForm {
	
	private String mbId;
	private String movieId;
	private String theatreId;
	private String broadcastDate;
	private String broadcastHour;
	private String broadcastMinute;
	
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}
	public String getBroadcastDate() {
		return broadcastDate;
	}
	public void setBroadcastDate(String broadcastDate) {
		this.broadcastDate = broadcastDate;
	}
	public String getBroadcastHour() {
		return broadcastHour;
	}
	public void setBroadcastHour(String broadcastHour) {
		this.broadcastHour = broadcastHour;
	}
	public String getBroadcastMinute() {
		return broadcastMinute;
	}
	public void setBroadcastMinute(String broadcastMinute) {
		this.broadcastMinute = broadcastMinute;
	}

}
