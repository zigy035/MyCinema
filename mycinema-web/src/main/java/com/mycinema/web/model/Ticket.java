package com.mycinema.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ticket", uniqueConstraints={@UniqueConstraint(columnNames = {"movie_broadcast_id", "seat_row", "seat_column"})})
public class Ticket {
	
	private String id;
	private MovieBroadcast movieBroadcast;
	private AuthUser authUser;
	private String seatRow;
	private String seatColumn;

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

	@ManyToOne(targetEntity = MovieBroadcast.class)
	@JoinColumn(name = "movie_broadcast_id", nullable = false)
	public MovieBroadcast getMovieBroadcast() {
		return movieBroadcast;
	}
	public void setMovieBroadcast(MovieBroadcast movieBroadcast) {
		this.movieBroadcast = movieBroadcast;
	}

	@ManyToOne(targetEntity = AuthUser.class)
	@JoinColumn(name = "auth_user_id")
	public AuthUser getAuthUser() {
		return authUser;
	}
	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

	@Column(name = "seat_row", nullable = false)
	public String getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	@Column(name = "seat_column", nullable = false)
	public String getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(String seatColumn) {
		this.seatColumn = seatColumn;
	}
	
}
