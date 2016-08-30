package com.mycinema.web.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.dao.TicketDAO;
import com.mycinema.web.model.Ticket;
import com.mycinema.web.service.TicketService;

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
	
	@Transactional
	public void bookTickets(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			ticketDAO.bookTicket(ticket);
		}
	}
	
	@Override
	public List<Ticket> getTicketsByAuthUser(String authUserId) {
		return ticketDAO.getTicketsByAuthUser(authUserId);
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
