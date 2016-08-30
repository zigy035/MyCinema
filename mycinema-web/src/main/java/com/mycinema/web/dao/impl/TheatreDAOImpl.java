package com.mycinema.web.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.model.Theatre;

public class TheatreDAOImpl implements TheatreDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "cinemaPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Theatre> getAllTheatres() {
		return (List<Theatre>) entityManager.createQuery("from Theatre").getResultList();
	}

	public Theatre getTheatre(String theatreId) {
		return entityManager.find(Theatre.class, theatreId);
	}
	
}
