package com.mycinema.web.model;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/*
@Entity
@Table(name = "movie_broadcast", uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "theatre_id", "broadcast_date"})})
@SqlResultSetMapping(
	name = "BookingMap",
	entities = {
		@EntityResult(
			entityClass = MovieBroadcast.class, 
			fields = {
                @FieldResult(name = "id", column = "id"),
                @FieldResult(name = "broadcastDate", column = "broadcast_date"),
            }),
        @EntityResult(
        	entityClass = Movie.class,
        	fields = {
                @FieldResult(name = "movieId", column = "id"),
                @FieldResult(name = "movieTitle", column = "title"),
            }),
        @EntityResult(
        	entityClass = Theatre.class,
        	fields = {
    			@FieldResult(name = "theatreId", column = "id"),
                @FieldResult(name = "theatreName", column = "name"),
            })
	},
	columns = @ColumnResult(name = "seats", type = String.class))
*/
/*
@SqlResultSetMapping(
        name = "AuthorValueMapping",
        classes = @ConstructorResult(
                targetClass = AuthorValue.class,
                columns = {
                    @ColumnResult(name = "id", type = Long.class),
                    @ColumnResult(name = "firstname"),
                    @ColumnResult(name = "lastname"),
                    @ColumnResult(name = "numBooks", type = Long.class)}))
 */
public class Booking {
	
	private String movieId;
	private String movieTitle;
	private String theatreId;
	private String theatreName;
	private String seats;
	private Date broadcastDate;
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public Date getBroadcastDate() {
		return broadcastDate;
	}
	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}
	
}
