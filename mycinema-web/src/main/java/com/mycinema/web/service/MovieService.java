package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public interface MovieService {
	
	List<Movie> getAllMovies();
	
	Movie getMovie(String movieId);
	
	List<MovieBroadcast> getBroadcastsByDate(String date);
	
	List<MovieBroadcast> getMovieBroadcasts(String movieId, String broadcastDate);
	
	MovieBroadcast getMovieBroadcast(String broadcastId);

	void addMovieBroadcast(MovieBroadcast broadcast);

	List<MovieBroadcast> getAvailableMovieBroadcasts();
}
