package com.mycinema.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycinema.web.service.MovieService;

@Controller
@RequestMapping("/myaccount")
public class MyAccountController extends AbstractController {
		
	@Autowired
	private MovieService movieService;
		
	@RequestMapping(method = RequestMethod.GET)
	public String showMyAccount(Model model) {
		
		model.addAttribute("bookings", movieService.getBookingsByAuthUser(getAuthUser().getId()));
		return "myAccount";
	}
	
}
