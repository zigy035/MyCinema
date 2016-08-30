package com.mycinema.web.dao;

import java.util.List;

import com.mycinema.web.model.Ticket;

public interface TicketDAO {

	List<Ticket> getTicketsByBroadcast(String broadcastId);
	
	List<Ticket> getTicketsByAuthUser(String authUserId);

	Ticket getAvailableTicket(String broadcastId, String row, String column);
		
	void addTicket(Ticket ticket);

	void bookTicket(Ticket ticket);
}
