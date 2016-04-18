package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.model.Booking;
import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public class MovieServiceImpl implements MovieService {
	
	private MovieDAO movieDAO;
	
	public List<Movie> getAllMovies() {
		return movieDAO.getAllMovies();
	}

	public Movie getMovie(String movieId) {
		return movieDAO.getMovie(movieId);
	}
	
	public List<MovieBroadcast> getBroadcastsByDate(String date) {
		return movieDAO.getBroadcastsByDate(date);
	}
	
	public List<MovieBroadcast> getMovieBroadcasts(String movieId, String broadcastDate) {
		return movieDAO.getMovieBroadcasts(movieId, broadcastDate);
	}
	
	public MovieBroadcast getMovieBroadcast(String broadcastId) {
		return movieDAO.getMovieBroadcast(broadcastId);
	}
	
	public void addMovieBroadcast(MovieBroadcast broadcast) {
		movieDAO.addMovieBroadcast(broadcast);
	}
	
	public List<Booking> getBookingsByAuthUser(String authUserId) {
		return movieDAO.getBookingsByAuthUser(authUserId);
	}

	public List<MovieBroadcast> getAvailableMovieBroadcasts() {
		return movieDAO.getAvailableMovieBroadcasts();
	}
	
	// Inject MovieDAO
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

}
