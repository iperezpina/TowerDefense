package controller;

public class Player {
	private int health;
	private int coins;
	
	public Player(){
		health = 100;
		coins = 1000;
	}
	
	
	public int getHP() {
		return health;
	}
	
	public int getCoins() {
		return coins;
	}
}
