package com.mycinema.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalExceptionController {

	@RequestMapping(value = "/error")
	public String handleError() {
		return "error";
	}
}
