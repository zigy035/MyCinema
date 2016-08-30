package com.mycinema.web.util.monopoly.field;

public class PropertyField extends BuyableField {
	
	private Integer propertyPrice;
	private Integer housePrice;
	private Integer hotelPrice;
	private Integer noHousesBuilt;
	private Integer revenuePerHouse;
	private Boolean hasHotel;
	private Integer hotelRevenue;
	
	public PropertyField(String title, Integer ownerId, Integer propertyPrice, Integer housePrice, Integer hotelPrice,
			Integer noHousesPurchased, Integer noHousesBuilt, Integer revenuePerHouse, Integer hotelRevenue) {
		
		super(title, ownerId);
		this.propertyPrice = propertyPrice;
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		this.noHousesBuilt = noHousesBuilt;
		this.revenuePerHouse = revenuePerHouse;
		this.hasHotel = false;
		this.hotelRevenue = hotelRevenue;
	}

	public Integer getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(Integer propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public Integer getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(Integer housePrice) {
		this.housePrice = housePrice;
	}
	

	public Integer getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(Integer hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public Integer getNoHousesBuilt() {
		return noHousesBuilt;
	}

	public void setNoHousesBuilt(Integer noHousesBuilt) {
		this.noHousesBuilt = noHousesBuilt;
	}

	public Integer getRevenuePerHouse() {
		return revenuePerHouse;
	}

	public void setRevenuePerHouse(Integer revenuePerHouse) {
		this.revenuePerHouse = revenuePerHouse;
	}

	public Boolean getHasHotel() {
		return hasHotel;
	}

	public void setHasHotel(Boolean hasHotel) {
		this.hasHotel = hasHotel;
	}

	public Integer getHotelRevenue() {
		return hotelRevenue;
	}

	public void setHotelRevenue(Integer hotelRevenue) {
		this.hotelRevenue = hotelRevenue;
	}
	
}
