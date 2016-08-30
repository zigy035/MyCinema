package com.mycinema.web.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.mycinema.web.dao.AuthUserDAO;
import com.mycinema.web.model.AuthUser;

public class AuthUserDAOImpl implements AuthUserDAO {
	
	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "cinemaPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public AuthUser getAuthUserByUsername(String username) {
		try {
			return (AuthUser) entityManager.createQuery("FROM AuthUser u "
					+ "WHERE u.username = :username")
					.setParameter("username", username)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void addAuthUser(AuthUser authUser) {
		entityManager.persist(authUser);
	}

}
