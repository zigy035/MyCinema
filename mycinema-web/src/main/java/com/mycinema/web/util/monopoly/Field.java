package com.mycinema.web.util.monopoly;

public abstract class Field {
	
	public Field(String title) {
		this.title = title;
	}
	
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
