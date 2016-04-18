package com.mycinema.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mycinema.web.dao.AuthUserDAO;
import com.mycinema.web.model.AuthUser;


public class AuthUserServiceImpl implements AuthUserService
{
	protected static Logger logger = Logger.getLogger(AuthUserServiceImpl.class);
	
	private AuthUserDAO authUserDAO;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser dbUser = authUserDAO.getAuthUserByUsername(username);
		
		if (dbUser == null)
		{
			throw new UsernameNotFoundException("User not found");			
		}
		
		int access = dbUser.getAccess();		
		UserDetails user = new org.springframework.security.core.userdetails.User(
				dbUser.getUsername(), dbUser.getPassword(), 
				true, true, true, true, getAuthorities(access));
		
		return user;
	}

	public Collection<GrantedAuthority> getAuthorities(Integer access)
	{
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// Check if this user has admin access
		if (access.compareTo(1) == 0) {
			// User has admin access
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			// User has an ordinary user access
			logger.debug("Grant ROLE_USER to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		return authList;
	}
	
	public AuthUser getAuthUserByUsername(String username) {
		return authUserDAO.getAuthUserByUsername(username);
	}

	public void addAuthUser(AuthUser authUser) {
		authUserDAO.addAuthUser(authUser);
	}

	// Inject AuthUserDAO
	public void setAuthUserDAO(AuthUserDAO authUserDAO)
	{
		this.authUserDAO = authUserDAO;
	}

}
