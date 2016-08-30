package com.mycinema.web.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;

public class MovieDAOImpl implements MovieDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "cinemaPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		return (List<Movie>) entityManager.createQuery("FROM Movie").getResultList();
	}
	
	public Movie getMovie(String movieId) {
		return entityManager.find(Movie.class, movieId);
	}

	@SuppressWarnings("unchecked")
	public List<MovieBroadcast> getBroadcastsByDate(String date) {
		return (List<MovieBroadcast>) entityManager.createQuery("FROM MovieBroadcast mb "
				+ "WHERE mb.movie.id = :movieId AND DATE(mb.broadcastDate) = :broadcastDate "
				+ "ORDER BY mb.broadcastDate")
				.setParameter("broadcastDate", date)
				.getResultList();
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
		
		return (List<MovieBroadcast>) entityManager.createQuery("FROM MovieBroadcast mb "
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

	
	
	@SuppressWarnings("unchecked")
	public List<MovieBroadcast> getAvailableMovieBroadcasts() {
		return (List<MovieBroadcast>) entityManager.createQuery("FROM MovieBroadcast mb WHERE mb.broadcastDate > CURDATE()").getResultList();
	}

	public MovieBroadcast getMovieBroadcast(String movieId, String theatreId, String broadcastDate) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = dateFormat.parse(broadcastDate);
		} catch (ParseException e) {
			return null;
		}
		
		try {
			return (MovieBroadcast) entityManager.createQuery("FROM MovieBroadcast mb "
					+ "WHERE mb.movie.id = :movieId AND mb.theatre.id = :theatreId AND mb.broadcastDate = :broadcastDate")
					.setParameter("movieId", movieId)
					.setParameter("theatreId", theatreId)
					.setParameter("broadcastDate", date, TemporalType.TIMESTAMP)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
