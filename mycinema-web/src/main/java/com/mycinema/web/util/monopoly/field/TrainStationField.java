package com.mycinema.web.util.monopoly.field;

public class TrainStationField extends BuyableField {
	
	private Integer stationPrice;
	private Integer revenuePerStation;
	
	public TrainStationField(String title, Integer ownerId, Integer stationPrice, Integer revenuePerStation) {
		super(title, ownerId);
		this.stationPrice = stationPrice;
		this.revenuePerStation = revenuePerStation;
	}

	public Integer getStationPrice() {
		return stationPrice;
	}

	public void setStationPrice(Integer stationPrice) {
		this.stationPrice = stationPrice;
	}

	public Integer getRevenuePerStation() {
		return revenuePerStation;
	}

	public void setRevenuePerStation(Integer revenuePerStation) {
		this.revenuePerStation = revenuePerStation;
	}

}
