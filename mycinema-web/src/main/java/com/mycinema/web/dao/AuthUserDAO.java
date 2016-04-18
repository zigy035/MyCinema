package com.mycinema.web.dao;

import com.mycinema.web.model.AuthUser;

public interface AuthUserDAO 
{
	public AuthUser getAuthUserByUsername(String username);
	
	public void addAuthUser(AuthUser authUser);
	
}
