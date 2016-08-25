package com.mycinema.web.service;

import java.util.List;

import javax.transaction.Transactional;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.dao.TicketDAO;
import com.mycinema.web.model.Booking;
import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.model.Theatre;
import com.mycinema.web.model.Ticket;

public class MovieServiceImpl implements MovieService {
	
	private MovieDAO movieDAO;
	private TicketDAO ticketDAO;
	
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
	
	@Transactional
	public void addMovieBroadcast(MovieBroadcast broadcast) {
		movieDAO.addMovieBroadcast(broadcast);
		Theatre theatre = broadcast.getTheatre();
		for (int i = 0; i < theatre.getRowNumber(); i++)
		{
			char row = (char) ('A' + i);
			for (int j = 0; j < theatre.getColumnNumber(); j++) {
				Ticket ticket = new Ticket();
				ticket.setMovieBroadcast(broadcast);
				ticket.setAuthUser(null);
				ticket.setSeatRow(String.valueOf(row));
				ticket.setSeatColumn(String.valueOf(j+1));
				ticketDAO.addTicket(ticket);
			}
		}
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

	// Inject TicketDAO
	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

}
