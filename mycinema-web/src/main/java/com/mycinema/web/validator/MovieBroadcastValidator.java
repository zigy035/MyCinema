package com.mycinema.web.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.form.MovieBroadcastForm;
import com.mycinema.web.model.MovieBroadcast;

public class MovieBroadcastValidator implements Validator {
	
	private MovieDAO movieDAO;
	private TheatreDAO theatreDAO;
	
	public boolean supports(Class<?> clazz) {
		return MovieBroadcastForm.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		MovieBroadcastForm form = (MovieBroadcastForm) object;
		
		if (StringUtils.isBlank(form.getMovieId())) {
			errors.rejectValue("movieId", "movie.required");
		} else if (movieDAO.getMovie(form.getMovieId()) == null) {
			errors.rejectValue("movieId", "movie.not.exist");
		}
		
		if (StringUtils.isBlank(form.getTheatreId())) {
			errors.rejectValue("theatreId", "theatre.required");
		} else if (theatreDAO.getTheatre(form.getTheatreId()) == null) {
			errors.rejectValue("theatreId", "theatre.not.exist");
		}
		
		if (StringUtils.isBlank(form.getBroadcastDate())) {
			errors.rejectValue("broadcastDate", "broadcast.date.required");
		} else if (invalidDate(form.getBroadcastDate())) {
			errors.rejectValue("broadcastDate", "broadcast.date.invalid");
		}
		
		if (StringUtils.isBlank(form.getBroadcastHour())) {
			errors.rejectValue("broadcastHour", "broadcast.hour.required");
		} else if (invalidHour(form.getBroadcastHour())) {
			errors.rejectValue("broadcastHour", "broadcast.hour.invalid");
		}
		
		if (StringUtils.isBlank(form.getBroadcastMinute())) {
			errors.rejectValue("broadcastMinute", "broadcast.minute.required");
		} else if (invalidMinute(form.getBroadcastMinute())) {
			errors.rejectValue("broadcastMinute", "broadcast.minute.invalid");
		}
		
		if (errors.getErrorCount() == 0) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.add(Calendar.HOUR, Integer.valueOf(form.getBroadcastHour()));
			cal.add(Calendar.MINUTE, Integer.valueOf(form.getBroadcastMinute()));
			String fmtDate = dateFormat.format(cal.getTime());
			
			MovieBroadcast broadcast = movieDAO.getMovieBroadcast(form.getMovieId(), form.getTheatreId(), fmtDate);
			if (broadcast != null) {
				errors.rejectValue("mbId", "movie.broadcast.exist");
			}
		}
	}
	
	private boolean invalidDate(String strDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateFormat.parse(strDate);
		} catch (ParseException e) {
			return true;
		}
		return false;
	}
	
	private boolean invalidHour(String strHour) {
		int hour = 0;
		try {
			hour = Integer.parseInt(strHour);
		} catch (NumberFormatException e) {
			return true;
		}
		if (hour < 12 || hour > 23) {
			return true;
		}
		
		return false;
	}
	
	private boolean invalidMinute(String strMinute) {
		int minute = 0;
		try {
			minute = Integer.parseInt(strMinute);
		} catch (NumberFormatException e) {
			return true;
		}
		if (minute < 0 || minute > 59) {
			return true;
		}
		
		return false;
	}

	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	public void setTheatreDAO(TheatreDAO theatreDAO) {
		this.theatreDAO = theatreDAO;
	}

}
