package com.mycinema.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mycinema.web.model.Movie;
import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.model.Theatre;
import com.mycinema.web.service.MovieService;
import com.mycinema.web.service.TheatreService;
import com.mycinema.web.service.TicketService;

@Component
public class TicketGenerator {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:appDataContext.xml");
		TicketGenerator p = ctx.getBean(TicketGenerator.class);
        p.start(args);
	}

	@Autowired
    private TicketService ticketService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TheatreService theatreService;
	
	private void start(String[] args) {		
		List<Movie> movies = movieService.getAllMovies();
		List<Theatre> theatres = theatreService.getAllTheatres();
		int [] hourArray = {13, 15, 16, 18, 19, 21};
		int [] minuteArray = {0, 15, 30, 45};
		
		List<Date> availableDates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		for (int i=0; i<7; i++) {
			availableDates.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Date availableDate : availableDates) {
			String strDate = dateFormat.format(availableDate);
			for (Movie movie : movies) {
				int hourIndex = (int)(Math.random()*(hourArray.length-1));
				int minuteIndex = (int)(Math.random()*(minuteArray.length-1));
				
				int theatreIndex = (int)(Math.random()*(theatres.size()-1));
				Theatre theatre = theatres.get(theatreIndex);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(availableDate);
				calendar.add(Calendar.HOUR, hourArray[hourIndex]);
				calendar.add(Calendar.MINUTE, minuteArray[minuteIndex]);
				MovieBroadcast broadcast = new MovieBroadcast();
				broadcast.setTheatre(theatre);
				broadcast.setMovie(movie);
				broadcast.setBroadcastDate(calendar.getTime());
				movieService.addMovieBroadcast(broadcast);
			}
			
			List<MovieBroadcast> broadcasts = movieService.getBroadcastsByDate(strDate);
			ticketService.addTicketsForBroadcasts(broadcasts);
			
			System.out.println("Movies, Broadcasts and Tickets added for date: " + strDate);
		}
		
	}
		
}
