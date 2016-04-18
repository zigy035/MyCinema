package com.mycinema.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Ticket;

public class TicketDAOImpl extends SqlSessionDaoSupport implements TicketDAO {

	public List<Ticket> getTicketsByBroadcast(String broadcastId) {
		return getSqlSession().selectList("getTicketsByBroadcast", broadcastId);
	}

	public Ticket getAvailableTicket(String broadcastId, String seatRow, String seatColumn) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("broadcastId", broadcastId);
		params.put("seatRow", seatRow);
		params.put("seatColumn", seatColumn);
		return getSqlSession().selectOne("getAvailableTicket", params);
	}
	
	public void addTicket(Ticket ticket) {
		getSqlSession().insert("addTicket", ticket);
	}
	
	public void bookTicket(Ticket ticket) {
		getSqlSession().update("bookTicket", ticket);
	}

}
