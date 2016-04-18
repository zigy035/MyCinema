package com.mycinema.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycinema.web.form.MovieBroadcastForm;
import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.service.MovieService;
import com.mycinema.web.service.TheatreService;
import com.mycinema.web.service.TicketService;
import com.mycinema.web.validator.MovieBroadcastValidator;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {
	
	private static final Logger LOG = Logger.getLogger(AdminController.class);
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private MovieBroadcastValidator movieBroadcastValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showMyAdminAccount(Model model) {
		
		MovieBroadcastForm movieBroadcastForm = new MovieBroadcastForm();
		model.addAttribute("movieBroadcastForm", movieBroadcastForm);

		model.addAttribute("broadcastDates", getBroadcastDates());
		model.addAttribute("broadcastHours", getBroadcastHours());
		model.addAttribute("broadcastMinutes", getBroadcastMinutes());
		
		model.addAttribute("movies", movieService.getAllMovies());
		model.addAttribute("theatres", theatreService.getAllTheatres());
		model.addAttribute("broadcasts", movieService.getAvailableMovieBroadcasts());
		
		return "admin";
	}
	
	@RequestMapping(value = "/addbroadcast", method = RequestMethod.POST)
	public String createBroadcast(Model model, MovieBroadcastForm movieBroadcastForm, BindingResult result) {
		
		movieBroadcastValidator.validate(movieBroadcastForm, result);
		if (result.hasErrors())
		{
			boolean errorFlag = false;
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				if (error.getCode().equals("movie.broadcast.exist")) {
					errorFlag = true;
				}
			}
			model.addAttribute("error", errorFlag);
			
			model.addAttribute("movieBroadcastForm", movieBroadcastForm);
			
			model.addAttribute("broadcastDates", getBroadcastDates());
			model.addAttribute("broadcastHours", getBroadcastHours());
			model.addAttribute("broadcastMinutes", getBroadcastMinutes());
			
			model.addAttribute("movies", movieService.getAllMovies());
			model.addAttribute("theatres", theatreService.getAllTheatres());
			model.addAttribute("broadcasts", movieService.getAvailableMovieBroadcasts());
			
			return "admin";
		}
		
		MovieBroadcast broadcast = new MovieBroadcast();
		broadcast.setMovieId(movieBroadcastForm.getMovieId());
		broadcast.setTheatreId(movieBroadcastForm.getTheatreId());
		broadcast.setBroadcastDate(createDateTimeObject(movieBroadcastForm));
		ticketService.addBroadcastTickets(broadcast);
		
		return "redirect:/admin";
	}
	
	private Date createDateTimeObject(MovieBroadcastForm form) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		try {
			cal.setTime(dateFormat.parse(form.getBroadcastDate()));
		} catch (ParseException e) {
			LOG.info("Date could not be parsed!");
		}
		cal.add(Calendar.HOUR, Integer.valueOf(form.getBroadcastHour()));
		cal.add(Calendar.MINUTE, Integer.valueOf(form.getBroadcastMinute()));
		
		return cal.getTime();
	}
	
}
