package com.mycinema.web.service;

import java.util.List;

import com.mycinema.web.dao.TheatreDAO;
import com.mycinema.web.model.Theatre;

public class TheatreServiceImpl implements TheatreService{
	
	private TheatreDAO theatreDAO;
	
	public List<Theatre> getAllTheatres() {
		return theatreDAO.getAllTheatres();
	}

	public void setTheatreDAO(TheatreDAO theatreDAO) {
		this.theatreDAO = theatreDAO;
	}

}
