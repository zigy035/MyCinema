package com.mycinema.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycinema.web.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController extends AbstractController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showMovieDetail(Model model, 
			@RequestParam(value="movieId", required = true) String movieId,
			@RequestParam(value="broadcastDate", required = false) String broadcastDate) {
		
		if (StringUtils.isBlank(broadcastDate)) {
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			broadcastDate = dateFormat.format(today);
		}
		
		model.addAttribute("broadcastDates", getBroadcastDates());
		model.addAttribute("movie", movieService.getMovie(movieId));
		model.addAttribute("broadcasts", movieService.getMovieBroadcasts(movieId, broadcastDate));
		
		return "movie";
	}
	
}
