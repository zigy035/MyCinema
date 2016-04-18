package com.mycinema.web.dao;

import java.util.List;

import com.mycinema.web.model.Theatre;

public interface TheatreDAO {
	
	List<Theatre> getAllTheatres();

	Theatre getTheatre(String theatreId);
}
