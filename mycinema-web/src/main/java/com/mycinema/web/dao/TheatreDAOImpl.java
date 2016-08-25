package com.mycinema.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Theatre;

public class TheatreDAOImpl extends SqlSessionDaoSupport implements TheatreDAO {
	
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
