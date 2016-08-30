package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.model.Ticket;

public interface TicketService {
	
	List<Ticket> getTicketsByBroadcast(String broadcastId);
	
	List<Ticket> getTicketsByAuthUser(String authUserId);
	
	Ticket getAvailableTicket(String broadcastId, String row, String column);
	
	void bookTickets(List<Ticket> tickets);
}
