package com.mycinema.web.service;

import java.util.List;

import javax.transaction.Transactional;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.dao.TicketDAO;
import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.model.Theatre;
import com.mycinema.web.model.Ticket;

public class TicketServiceImpl implements TicketService {

	private TicketDAO ticketDAO;
	private TheatreDAO theatreDAO;
	private MovieDAO movieDAO;
	
	public List<Ticket> getTicketsByBroadcast(String broadcastId) {
		return ticketDAO.getTicketsByBroadcast(broadcastId);
	}
	
	public Ticket getAvailableTicket(String broadcastId, String row, String column) {
		return ticketDAO.getAvailableTicket(broadcastId, row, column);
	}

	public void addTicketsForBroadcasts(List<MovieBroadcast> broadcasts) {
		for (MovieBroadcast broadcast : broadcasts) {
			Theatre theatre = theatreDAO.getTheatre(broadcast.getTheatre().getId());
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
	}
	
	@Transactional
	public void bookTickets(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			ticketDAO.bookTicket(ticket);
		}
	}

	public void addBroadcastTickets(MovieBroadcast broadcast) {
		movieDAO.addMovieBroadcast(broadcast);
		Theatre theatre = theatreDAO.getTheatre(broadcast.getTheatre().getId());
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
	
	// Inject TicketDAO
	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

	// Inject TheatreDAO
	public void setTheatreDAO(TheatreDAO theatreDAO) {
		this.theatreDAO = theatreDAO;
	}

	// Inject MovieDAO
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

}
