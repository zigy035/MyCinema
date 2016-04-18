package com.mycinema.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mycinema.web.model.AuthUser;

public interface AuthUserService extends UserDetailsService {

	AuthUser getAuthUserByUsername(String username);
	
	void addAuthUser(AuthUser authUser);
}
