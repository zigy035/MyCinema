package com.mycinema.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycinema.web.dao.MovieDAO;
import com.mycinema.web.form.BookingMatrixForm;

public class BookingMatrixValidator implements Validator {
	
	private MovieDAO movieDAO;
	
	public boolean supports(Class<?> clazz) {
		return BookingMatrixForm.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		BookingMatrixForm formBean = (BookingMatrixForm) object;
		
		if (StringUtils.isBlank(formBean.getMovieBroadcastId())) {
			errors.rejectValue("movieBroadcastId", "no.movie.broadcast");
		} else if (movieDAO.getMovieBroadcast(formBean.getMovieBroadcastId()) == null) {
			errors.rejectValue("movieBroadcastId", "movie.broadcast.not.exist");
		}
		
		if (CollectionUtils.isEmpty(formBean.getSeats())) {
			errors.rejectValue("seats", "please.select.seats");
		} else if (formBean.getSeats().size() > 4) {
			errors.rejectValue("seats", "max.seats.allowed");
		}
	}

	// Inject MovieDAO
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}
	
}
