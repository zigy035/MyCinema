package com.mycinema.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "authuser")
public class AuthUser
{
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Integer access;	// Access level for Spring security
	
	@Id
    @GenericGenerator(name = "cinema_keygen", strategy = "com.mycinema.web.util.CinemaKeyGenerator")
    @GeneratedValue(generator = "cinema_keygen")
	@Column(name = "id", length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "access", nullable = false)
	public Integer getAccess() {
		return access;
	}
	public void setAccess(Integer access) {
		this.access = access;
	}
	
}