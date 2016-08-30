package com.mycinema.web.util.monopoly.field;

import com.mycinema.web.util.monopoly.Field;

public abstract class BuyableField extends Field {
	
	private Integer ownerId;
	
	public BuyableField(String title, Integer ownerId) {
		super(title);
		this.ownerId = ownerId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	
}
