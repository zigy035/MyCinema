package com.mycinema.web.service.impl;

import java.util.List;

import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.model.Theatre;
import com.mycinema.web.service.TheatreService;

public class TheatreServiceImpl implements TheatreService {
	
	private TheatreDAO theatreDAO;
	
	public List<Theatre> getAllTheatres() {
		return theatreDAO.getAllTheatres();
	}

	@Override
	public Theatre getTheatre(String theatreId) {
		return theatreDAO.getTheatre(theatreId);
	}
	
	public void setTheatreDAO(TheatreDAO theatreDAO) {
		this.theatreDAO = theatreDAO;
	}

}
