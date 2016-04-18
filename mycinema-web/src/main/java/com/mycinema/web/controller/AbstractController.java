package com.mycinema.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mycinema.web.model.AuthUser;
import com.mycinema.web.service.AuthUserService;

public abstract class AbstractController {
	
	@Autowired
	protected AuthUserService authUserService;
		
	protected List<String> getBroadcastDateStrings() {
		List<String> availableDates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i=0; i<7; i++) {
			availableDates.add(dateFormat.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		return availableDates;
	}
	
	protected List<Date> getBroadcastDates() {
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
		return availableDates;
	}
	
	protected List<String> getBroadcastHours() {
		List<String> hours = new ArrayList<String>();
		for (int i=12; i<24; i++) {
			String strHour = (i<10) ? String.valueOf("0" + i) : String.valueOf(i);
			hours.add(strHour);
		}
		return hours;
	}
	
	protected List<String> getBroadcastMinutes() {
		String [] minutes = {"00", "15", "30", "45"};
		return Arrays.asList(minutes);
	}
	
	protected AuthUser getAuthUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); //get logged in username
	    AuthUser user = authUserService.getAuthUserByUsername(username);
		return user;
	}
	
}
