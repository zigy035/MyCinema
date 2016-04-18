package com.mycinema.web.model;

import java.util.UUID;

public class Theatre {

	private String id;
	private String name;
	private Integer rowNumber;
	private Integer columnNumber;
	
	public Theatre() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	
}
