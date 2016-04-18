package com.mycinema.web.model;

import java.util.UUID;

public class AuthUser
{
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Integer access;	// Access level for Spring security
	
	public AuthUser()
	{
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}
	
}