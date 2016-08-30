package com.mycinema.web.dao;

import java.util.List;

import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public interface MovieDAO {
	
	List<Movie> getAllMovies();
	
	List<MovieBroadcast> getBroadcastsByDate(String date);
	
	List<MovieBroadcast> getMovieBroadcasts(String movieId, String broadcastDate);

	Movie getMovie(String movieId);
	
	MovieBroadcast getMovieBroadcast(String broadcastId);

	void addMovieBroadcast(MovieBroadcast broadcast);

	List<MovieBroadcast> getAvailableMovieBroadcasts();

	MovieBroadcast getMovieBroadcast(String movieId, String theatreId, String broadcastDate);
}
