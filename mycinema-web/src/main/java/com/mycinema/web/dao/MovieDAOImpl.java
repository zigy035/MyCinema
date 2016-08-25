package com.mycinema.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Booking;
import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public class MovieDAOImpl extends SqlSessionDaoSupport implements MovieDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "cinemaPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		return (List<Movie>) entityManager.createQuery("from Movie").getResultList();
	}
	
	public Movie getMovie(String movieId) {
		return entityManager.find(Movie.class, movieId);
	}

	public List<MovieBroadcast> getBroadcastsByDate(String date) {
		return getSqlSession().selectList("getMovieBroadcastsByDate", date);
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieBroadcast> getMovieBroadcasts(String movieId, String broadcastDate) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(broadcastDate);
		} catch (ParseException e) {
			return null;
		}
		
		return (List<MovieBroadcast>) entityManager.createQuery("from MovieBroadcast mb "
				+ "WHERE mb.movie.id = :movieId AND DATE(mb.broadcastDate) = :broadcastDate "
				+ "ORDER BY mb.broadcastDate")
				.setParameter("movieId", movieId)
				.setParameter("broadcastDate", date)
				.getResultList();
	}

	public MovieBroadcast getMovieBroadcast(String broadcastId) {
		return entityManager.find(MovieBroadcast.class, broadcastId);
	}

	public void addMovieBroadcast(MovieBroadcast broadcast) {
		entityManager.persist(broadcast);
	}

	public List<Booking> getBookingsByAuthUser(String authUserId) {
		return getSqlSession().selectList("getBookingsByAuthUser", authUserId);
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieBroadcast> getAvailableMovieBroadcasts() {
		return (List<MovieBroadcast>) entityManager.createQuery("FROM MovieBroadcast mb WHERE mb.broadcastDate > CURDATE()").getResultList();
	}

	public MovieBroadcast getMovieBroadcast(String movieId, String theatreId, String broadcastDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("movieId", movieId);
		params.put("theatreId", theatreId);
		params.put("broadcastDate", broadcastDate);
		return getSqlSession().selectOne("getMovieBroadcastMultiParam", params);
	}

}
