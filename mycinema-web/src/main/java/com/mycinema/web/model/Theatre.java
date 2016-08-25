package com.mycinema.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "theatre")
public class Theatre {

	private String id;
	private String name;
	private Integer rowNumber;
	private Integer columnNumber;
	
	
	@Id
    @GenericGenerator(name = "cinema_keygen", strategy = "com.mycinema.web.util.CinemaKeyGenerator")
    @GeneratedValue(generator = "cinema_keygen")
	@Column(name = "id", length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "row_number", nullable = false)
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	@Column(name = "column_number", nullable = false)
	public Integer getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	
}
