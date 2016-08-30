package com.mycinema.web.util.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mycinema.web.util.monopoly.field.CompanyField;
import com.mycinema.web.util.monopoly.field.PropertyField;
import com.mycinema.web.util.monopoly.field.TaxField;
import com.mycinema.web.util.monopoly.field.TrainStationField;

public class Game {
	
	private static final Integer NUMBER_OF_ITERATIONS_ALLOWED = 1000;
	
	List<Player> players;
	List<Field> fields;
//	Map<Player, List<Field>> playerFieldMap;
	
	public Game() {
		players = new ArrayList<>();
		players.add(new Player(0, "Igor", 250000, 0));
		players.add(new Player(1, "Goran", 250000, 0));
		players.add(new Player(2, "Sanja", 250000, 0));
		players.add(new Player(3, "Danijela", 250000, 0));
		
		fields = new ArrayList<>();
		fields.add(new PropertyField("Moscow", 		null, 6000, 5000, 5000, 0, 0, 4000, 50000));
		fields.add(new TrainStationField("South Station", null, 20000, 5000));
		fields.add(new TaxField("Revenue Tax", 10000));
		fields.add(new PropertyField("Rome", 		null, 10000, 5000, 5000, 0, 0, 5000, 60000));
		fields.add(new CompanyField("Water supply company", null, 15000, 2000, 15000));
		fields.add(new PropertyField("Paris", 		null, 13000, 10000, 10000, 0, 0, 9000, 70000));
		fields.add(new TrainStationField("West Station", null, 20000, 5000));
		fields.add(new PropertyField("Amsterdam", 	null, 17000, 10000, 10000, 0, 0, 9000, 85000));
		fields.add(new PropertyField("London", 		null, 22000, 15000, 15000, 0, 0, 11000, 100000));
		fields.add(new TrainStationField("North Station", null, 20000, 5000));
		fields.add(new PropertyField("Tokyo", 		null, 24000, 15000, 15000, 0, 0, 15000, 120000));
		fields.add(new CompanyField("Telecom company", null, 15000, 2000, 15000));
		fields.add(new PropertyField("Frankfurt", 	null, 35000, 20000, 20000, 0, 0, 32000, 150000));
		fields.add(new TrainStationField("East Station", null, 20000, 5000));
		fields.add(new TaxField("Luxury Tax", 20000));
		fields.add(new PropertyField("New York", 	null, 50000, 20000, 20000, 0, 0, 40000, 200000));
		// missing start, free parking, prison, chance, surprise fields
	}
	
	public void play() {
		Random random = new Random();
		
		int pIndex = 0;
		int iterNum = 0;
		while (players.size() > 1 && iterNum < NUMBER_OF_ITERATIONS_ALLOWED) {
			int moveFor = random.nextInt(6);
			Player currentPlayer = players.get(pIndex);
			int position = currentPlayer.getPosition() + moveFor;
			if (position >= fields.size()) {
				// player has completed a circle (+ $20000)
				position -= fields.size();
				currentPlayer.setMoney(currentPlayer.getMoney() + 20000);
			}
			
			currentPlayer.setPosition(position);
			Field currentField = fields.get(position);

			// handle TaxField
			if (currentField instanceof TaxField) {
				TaxField currentTaxField = (TaxField) currentField;
				currentPlayer.setMoney(currentPlayer.getMoney() - currentTaxField.getTax());
				System.out.println("Player " + currentPlayer.getName() + " paid $" + 
						currentTaxField.getTax() + " for " + currentTaxField.getTitle());
			}
			
			// handle CompanyField
			else if (currentField instanceof CompanyField) {
				CompanyField currentCompanyField = (CompanyField) currentField;
				Integer ownerId = currentCompanyField.getOwnerId();
				
				// non purchased
				if (ownerId == null) {
					// if player has enough money
					if (currentPlayer.getMoney() >= currentCompanyField.getCompanyPrice()) {
						currentPlayer.setMoney(currentPlayer.getMoney() - currentCompanyField.getCompanyPrice());
						currentCompanyField.setOwnerId(currentPlayer.getId());
						System.out.println(currentPlayer.getName() + " bought " + currentCompanyField.getTitle());
					}
				// you must pay to the owner
				} else if (ownerId != null && !currentPlayer.getId().equals(ownerId)) {
					for (Player player : players) {
						if (ownerId.equals(player.getId())) {
							int noCompaniesOwned=0;
							for (Field field : fields) {
								if (field instanceof CompanyField) {
									CompanyField cf = (CompanyField) field;
									if (player.getId().equals(cf.getOwnerId())) {
										noCompaniesOwned++;
									}
								}
							}
							int price;
							if (noCompaniesOwned > 1) {
								price = currentCompanyField.getTwoCompanyRevenue();
							} else {
								price = currentCompanyField.getOneCompanyRevenue();
							}
							
							player.setMoney(player.getMoney() + price);
							currentPlayer.setMoney(currentPlayer.getMoney() - price);
							System.out.println(currentPlayer.getName() + " paid $" + price + 
									" to " + player.getName() + " for " + currentCompanyField.getTitle());
						}
					}
				// player on it's own property
				} else {
					// do nothing
				}
			}
			
			// handle TrainStationField
			else if (currentField instanceof TrainStationField) {
				
				TrainStationField currentTrainStationField = (TrainStationField) currentField;
				Integer ownerId = currentTrainStationField.getOwnerId();
				
				// non purchased
				if (ownerId == null) {
					// if player has enough money
					if (currentPlayer.getMoney() >= currentTrainStationField.getStationPrice()) {
						currentPlayer.setMoney(currentPlayer.getMoney() - currentTrainStationField.getStationPrice());
						currentTrainStationField.setOwnerId(currentPlayer.getId());
						System.out.println(currentPlayer.getName() + " bought " + currentTrainStationField.getTitle());
					}
				// you must pay to the owner
				} else if (ownerId != null && !currentPlayer.getId().equals(ownerId)) {
					for (Player player : players) {
						if (ownerId.equals(player.getId())) {
							int noStationsOwned=0;
							for (Field field : fields) {
								if (field instanceof TrainStationField) {
									TrainStationField cf = (TrainStationField) field;
									if (player.getId().equals(cf.getOwnerId())) {
										noStationsOwned++;
									}
								}
							}
							int price = currentTrainStationField.getRevenuePerStation() * noStationsOwned;
							
							player.setMoney(player.getMoney() + price);
							currentPlayer.setMoney(currentPlayer.getMoney() - price);
							System.out.println(currentPlayer.getName() + " paid $" + price + 
									" to " + player.getName() + " for " + currentTrainStationField.getTitle());
						}
					}
				// player on it's own property
				} else {
					// do nothing
				}
			}
			
			// handle PropertyField
			else if (currentField instanceof PropertyField) {
				
				PropertyField currentPropertyField = (PropertyField) currentField;
				Integer ownerId = currentPropertyField.getOwnerId();
				
				// non purchased
				if (ownerId == null) {
					// if player has enough money
					if (currentPlayer.getMoney() >= currentPropertyField.getPropertyPrice()) {
						currentPlayer.setMoney(currentPlayer.getMoney() - currentPropertyField.getPropertyPrice());
						currentPropertyField.setOwnerId(currentPlayer.getId());
						System.out.println(currentPlayer.getName() + " bought " + currentField.getTitle() + "'s property");
					}
				// you must pay to the owner
				} else if (ownerId != null && !currentPlayer.getId().equals(ownerId)) {
					for (Player player : players) {
						if (ownerId.equals(player.getId())) {
							int price;
							if (currentPropertyField.getHasHotel()) {
								//pay for hotel
								price = currentPropertyField.getHotelRevenue();
								System.out.println(currentPlayer.getName() + " paid $" + price + " to " 
										+ player.getName() + " for " + currentField.getTitle() 
										+ " (hotel built)");
							} else if (currentPropertyField.getNoHousesBuilt() > 0) {
								//pay for (x) house(s)
								price = currentPropertyField.getRevenuePerHouse() * currentPropertyField.getNoHousesBuilt();
								System.out.println(currentPlayer.getName() + " paid $" + price + " to " 
										+ player.getName() + " for " + currentField.getTitle() 
										+ " (" + currentPropertyField.getNoHousesBuilt() + " houses built)");
							} else {
								//pay for property only
								price = currentPropertyField.getPropertyPrice()/10;
								System.out.println(currentPlayer.getName() + " paid $" + price + " to " 
										+ player.getName() + " for " + currentField.getTitle() 
										+ " (property only)");

							}
							player.setMoney(player.getMoney() + price);
							currentPlayer.setMoney(currentPlayer.getMoney() - price);
						}
					}
				// player on it's own property
				} else {
					// if player has enough money -> builds a house/hotel
					// TODO: implement logic for building a hotel...
					if (currentPlayer.getMoney() >= currentPropertyField.getHousePrice()) {
						if (currentPropertyField.getNoHousesBuilt() == 4) {
							//build a hotel
							currentPlayer.setMoney(currentPlayer.getMoney() - currentPropertyField.getHotelPrice());
							currentPropertyField.setHasHotel(true);
							currentPropertyField.setNoHousesBuilt(0);
							System.out.println("Player " + currentPlayer.getName() + " built hotel in " + currentField.getTitle());
						} else {
							//build additional house
							currentPlayer.setMoney(currentPlayer.getMoney() - currentPropertyField.getHousePrice());
							currentPropertyField.setNoHousesBuilt(currentPropertyField.getNoHousesBuilt() + 1);
							System.out.println("Player " + currentPlayer.getName() + " built " + 
									currentPropertyField.getNoHousesBuilt() + 1 + ". house in " + currentField.getTitle());
						}
					}
				}
			}
			
			
			if (currentPlayer.getMoney() <= 0) {
				players.remove(currentPlayer);
				System.out.println("Player " + currentPlayer.getName() + " removed");
			}
			
			if (pIndex+1 < players.size()) {
				pIndex++;
			} else {
				pIndex = 0;
			}
			iterNum++;
			
			for (Player player : players) {
				System.out.println("Player " + player.getName() + "(position " + 
						player.getPosition() + ") has $" + player.getMoney());
			}
			System.out.println();
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (players.size() == 1) {
			System.out.println("The winner is " + players.get(0).getName() + " after " + iterNum + " iteration(s).");
		} else {
			System.out.println("No winner after " + NUMBER_OF_ITERATIONS_ALLOWED + " attempts");
		}
	}
	
	public static void main(String[] args) {		
		Game game = new Game();
		game.play();
	}
	 
}
