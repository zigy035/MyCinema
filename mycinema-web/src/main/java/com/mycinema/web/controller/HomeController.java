package com.mycinema.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycinema.web.service.MovieService;

@Controller
public class HomeController extends AbstractController
{
	private static final Logger LOG = Logger.getLogger(HomeController.class);
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value={"/", "/homepage"})
	public String showMovies(Model model)
	{		
		model.addAttribute("movies", movieService.getAllMovies());
		LOG.debug("Movies Loaded!");
		
		return "homePage";
	}
	
}
