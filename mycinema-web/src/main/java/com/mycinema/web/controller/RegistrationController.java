package com.mycinema.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycinema.web.form.RegisterFormBean;
import com.mycinema.web.model.AuthUser;
import com.mycinema.web.service.AuthUserService;
import com.mycinema.web.validator.AuthUserValidator;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController 
{
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private AuthUserValidator authUserValidator;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showRegisterForm(Model model)
	{
		model.addAttribute("registerFormBean", new RegisterFormBean());
		return "registrationForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(Model model, @Validated RegisterFormBean registerFormBean, BindingResult result)
	{
		authUserValidator.validate(registerFormBean, result);
		if (result.hasErrors())
		{
			model.addAttribute("registerFormBean", registerFormBean);
			return "registrationForm";
		}
		
		final AuthUser authUser = populateAuthUser(registerFormBean); 
		
		authUserService.addAuthUser(authUser);
		model.addAttribute("authUser", authUser);
		
		return "confirmRegistration";
	}
	
	private AuthUser populateAuthUser(RegisterFormBean form) {
		AuthUser authUser = new AuthUser();
		authUser.setFirstName(form.getFirstName());
		authUser.setLastName(form.getLastName());
		authUser.setUsername(form.getUsername());
		authUser.setPassword(passwordEncoder.encodePassword(form.getPassword(), null));
		authUser.setAccess(0);
		return authUser;
	}
	
}
