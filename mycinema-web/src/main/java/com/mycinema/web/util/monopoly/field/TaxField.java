package com.mycinema.web.util.monopoly.field;

import com.mycinema.web.util.monopoly.Field;

public class TaxField extends Field {
	
	private Integer tax;
	
	public TaxField(String title, Integer tax) {
		super(title);
		this.tax = tax;
	}

	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}

}
