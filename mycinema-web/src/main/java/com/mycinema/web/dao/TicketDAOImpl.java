package com.mycinema.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Ticket;

public class TicketDAOImpl extends SqlSessionDaoSupport implements TicketDAO {
	
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
		return (Ticket) entityManager.createQuery("from Ticket t "
				+ "WHERE t.movieBroadcast.id = :broadcastId "
				+ "AND t.seatRow = :seatRow AND t.seatColumn = :seatColumn")
				.setParameter("broadcastId", broadcastId)
				.setParameter("seatRow", seatRow)
				.setParameter("seatColumn", seatColumn)
				.getSingleResult();
	}
	
	public void addTicket(Ticket ticket) {
		entityManager.persist(ticket);
	}
	
	public void bookTicket(Ticket ticket) {
		entityManager.merge(ticket);
		entityManager.flush();
	}

}
