package com.mycinema.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Booking;
import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public class MovieDAOImpl extends SqlSessionDaoSupport implements MovieDAO {

	public List<Movie> getAllMovies() {
		return getSqlSession().selectList("getMovies");
	}
	
	public Movie getMovie(String movieId) {
		return getSqlSession().selectOne("getMovie", movieId);
	}

	public List<MovieBroadcast> getBroadcastsByDate(String date) {
		return getSqlSession().selectList("getMovieBroadcastsByDate", date);
	}
	
	public List<MovieBroadcast> getMovieBroadcasts(String movieId, String broadcastDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("movieId", movieId);
		params.put("broadcastDate", broadcastDate);
		return getSqlSession().selectList("getMovieBroadcasts", params);
	}

	public MovieBroadcast getMovieBroadcast(String broadcastId) {
		return getSqlSession().selectOne("getMovieBroadcast", broadcastId);
	}

	public void addMovieBroadcast(MovieBroadcast broadcast) {
		getSqlSession().insert("addMovieBroadcast", broadcast);
	}

	public List<Booking> getBookingsByAuthUser(String authUserId) {
		return getSqlSession().selectList("getBookingsByAuthUser", authUserId);
	}

	public List<MovieBroadcast> getAvailableMovieBroadcasts() {
		return getSqlSession().selectList("getAvailableMovieBroadcasts");
	}

	public MovieBroadcast getMovieBroadcast(String movieId, String theatreId, String broadcastDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("movieId", movieId);
		params.put("theatreId", theatreId);
		params.put("broadcastDate", broadcastDate);
		return getSqlSession().selectOne("getMovieBroadcastMultiParam", params);
	}

}
