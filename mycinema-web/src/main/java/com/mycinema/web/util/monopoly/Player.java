package com.mycinema.web.util.monopoly;

import java.util.List;

public class Player {
	private Integer id;
	private String name;
	private Integer money;
	private Integer position; //field position
	private List<Field> fields;
	
	public Player(Integer id, String name, Integer money, Integer position) {
		this.id = id;
		this.name = name;
		this.money = money;
		this.position = position;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	
	
}
