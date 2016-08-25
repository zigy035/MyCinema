package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.model.Theatre;

public interface TheatreService {
	
	List<Theatre> getAllTheatres();

	Theatre getTheatre(String theatreId);
}
