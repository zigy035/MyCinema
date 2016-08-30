package com.mycinema.web.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.mycinema.web.dao.TicketDAO;
import com.mycinema.web.model.Ticket;

public class TicketDAOImpl implements TicketDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "cinemaPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsByBroadcast(String broadcastId) {
		return (List<Ticket>) entityManager.createQuery("from Ticket t "
				+ "WHERE t.movieBroadcast.id = :broadcastId")
				.setParameter("broadcastId", broadcastId)
				.getResultList();
	}

	public Ticket getAvailableTicket(String broadcastId, String seatRow, String seatColumn) {
		try {
			return (Ticket) entityManager.createQuery("from Ticket t "
					+ "WHERE t.movieBroadcast.id = :broadcastId "
					+ "AND t.seatRow = :seatRow AND t.seatColumn = :seatColumn")
					.setParameter("broadcastId", broadcastId)
					.setParameter("seatRow", seatRow)
					.setParameter("seatColumn", seatColumn)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsByAuthUser(String authUserId) {
		return (List<Ticket>) entityManager.createQuery("FROM Ticket ti " +
				"WHERE ti.authUser.id = :authUserId")
				.setParameter("authUserId", authUserId)
				.getResultList();
	}
	
	public void addTicket(Ticket ticket) {
		entityManager.persist(ticket);
	}
	
	public void bookTicket(Ticket ticket) {
		entityManager.merge(ticket);
	}

}
