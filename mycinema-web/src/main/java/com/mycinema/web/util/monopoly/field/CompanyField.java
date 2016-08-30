package com.mycinema.web.util.monopoly.field;

public class CompanyField extends BuyableField {
	
	private Integer companyPrice;
	private Integer oneCompanyRevenue;
	private Integer twoCompanyRevenue;
	
	public CompanyField(String title, Integer ownerId, Integer companyPrice, Integer oneCompanyRevenue, Integer twoCompanyRevenue) {
		super(title, ownerId);
		this.companyPrice = companyPrice;
		this.oneCompanyRevenue = oneCompanyRevenue;
		this.twoCompanyRevenue = twoCompanyRevenue;
	}

	public Integer getCompanyPrice() {
		return companyPrice;
	}

	public void setCompanyPrice(Integer companyPrice) {
		this.companyPrice = companyPrice;
	}

	public Integer getOneCompanyRevenue() {
		return oneCompanyRevenue;
	}

	public void setOneCompanyRevenue(Integer oneCompanyRevenue) {
		this.oneCompanyRevenue = oneCompanyRevenue;
	}

	public Integer getTwoCompanyRevenue() {
		return twoCompanyRevenue;
	}

	public void setTwoCompanyRevenue(Integer twoCompanyRevenue) {
		this.twoCompanyRevenue = twoCompanyRevenue;
	}

}
