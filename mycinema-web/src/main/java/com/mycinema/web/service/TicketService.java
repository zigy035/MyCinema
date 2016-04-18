package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.model.Ticket;

public interface TicketService {
	
	List<Ticket> getTicketsByBroadcast(String broadcastId);
	
	Ticket getAvailableTicket(String broadcastId, String row, String column);
	
	void addTicketsForBroadcasts(List<MovieBroadcast> broadcasts);

	void bookTickets(List<Ticket> tickets);

	void addBroadcastTickets(MovieBroadcast broadcast);
}
