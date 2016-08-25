package com.mycinema.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "movie_broadcast", uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "theatre_id", "broadcast_date"})})
public class MovieBroadcast {
	
	private String id;
	private Movie movie;
	private Theatre theatre;
	private Date broadcastDate;

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
	
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(name = "movie_id", nullable = false)
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@ManyToOne(targetEntity = Theatre.class)
	@JoinColumn(name = "theatre_id", nullable = false)
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	@Column(name = "broadcast_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBroadcastDate() {
		return broadcastDate;
	}
	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}
	
}
