package com.mycinema.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "movie")
public class Movie {
	
	private String id;
	private String title;
	private String description;
	private String genre;
	
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

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", nullable = false, columnDefinition = "text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "genre", nullable = false)
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
