package com.mycinema.web.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mycinema.web.model.AuthUser;

public class AuthUserDAOImpl extends SqlSessionDaoSupport implements AuthUserDAO {

	public AuthUser getAuthUserByUsername(String username) {
		AuthUser authUser = (AuthUser) getSqlSession().selectOne("getAuthUserByUsername", username);
		return authUser;
	}

	public void addAuthUser(AuthUser authUser) {
		getSqlSession().insert("addAuthUser", authUser);
	}

}
