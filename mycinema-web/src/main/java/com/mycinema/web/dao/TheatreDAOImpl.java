package com.mycinema.web.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.Theatre;

public class TheatreDAOImpl extends SqlSessionDaoSupport implements TheatreDAO {

	public List<Theatre> getAllTheatres() {
		return getSqlSession().selectList("getTheatres");
	}

	public Theatre getTheatre(String theatreId) {
		return getSqlSession().selectOne("getTheatre", theatreId);
	}
	
}
