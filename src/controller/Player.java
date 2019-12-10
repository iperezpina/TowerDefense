package controller;

import java.io.File;

import Towers.Tower;
import Towers.Tower1;
import Towers.Tower2;
import Towers.Tower3;
import Towers.Tower4;
import Towers.Tower5;
import Towers.Tower6;
import Towers.Tower7;
import Towers.Tower8;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import model.TimerAll;
import view.TowerDefenseView;

public class Player {
	private static int health;
	private static int coins;
	private static TowerDefenseView tdv;
	private ImageView currTowerImgView;
	private Image currTowerImg;
	private Tower currTower;
	private String currURL;
	private String id;
	private int selectedX, selectedY;

	public Player() {
		health = 100;
		coins = 1000;
	}

	public int getHP() {
		return health;
	}

	public int getCoins() {
		return coins;
	}

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public static void takeDmg() {
		health -= 1;
		updatePlayerGUI();
		gameOver();
	}

	private static void gameOver() {
		if (health <= 0) {
			System.out.println("GAME OVER!");
			TimerAll.pause();
		}
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}

	public void decreaseCoins(int shift) {
		coins -= shift;
		updatePlayerGUI();
	}

	public void increaseCoins(int shift) {
		coins += shift;
	}

	public static void addCash(int cash) {
		coins += cash;
		updatePlayerGUI();
	}

	public static void updatePlayerGUI() {
		// tdv.getRightLabel().setText("Money: " + coins + "\nHealth: " + health);
		tdv.updatePlayerInfo(coins, health);
	}

	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			currURL = currTowerImg.impl_getUrl();
			id = currURL.substring(currURL.length() - 10);
			if (id != null && id.length() > 6) {
				id = id.substring(0, id.length() - 4);
			}
			Tower temp = makeTempTower(id, 0, 0);
			String result = temp.getTowerName() + " costs $" + temp.getTowerCost();
			tdv.updateTowerLabel(result);

		}
	};

	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;

			//Checks if we can shorten the id to remove the ".png"
			if (id != null && id.length() > 6) {
				id = id.substring(0, id.length() - 4);
			}
			//If id is null we will clear the tower selected pane
			if (id == null) {
				tdv.setAllBlank();
            	System.out.println("Setting to blanks");
			}
			//If the tile we click on can have towers placed on it and the id isn't null we will create a temp Tower, 
			//then check if we have enough money to place the tower.  Then update our cash accordingly.
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace() && id != null) {

				currTower = makeTempTower(id, x, y);

				if (currTower != null && !tdv.getTowers().isThereATower(x, y)) {
					if (coins >= currTower.getTowerCost()) {
						AudioClip coin = new AudioClip(new File("src/Sounds/coin.wav").toURI().toString());
						coin.play();
						decreaseCoins(currTower.getTowerCost());
						tdv.getTowers().addTower2(currTower, x, y);
						id = null;
					}
				}

//				else {
//					Tower tower = tdv.getTowers().getTower(x, y);
//					
//					if (tower != null) {
//						String name= tower.getTowerName();
//						int Ucost= tower.getUpgradeCost();
//						int enemy= tower.getEnemiesDestroyed();
//						int range= tower.getRange();
//						tower.setSellCost();
//						int sell= tower.getSellCost();
//						tdv.setTowerSpecification(name, enemy, Ucost, range, sell);
//
//					}
//				
//
//				}
			}
			if (tdv.getTowers().isThereATower(x, y)){
				//Shows the tower info
				Platform.runLater( new Runnable() {
                    @Override
                    public void run() {
                    	Tower tower = tdv.getTowers().getTower(x, y);

        				if (tower != null) {
        					tower.setIsSelected(!tower.isSelected());
        					String name = tower.getTowerName();
        					int Ucost = tower.getUpgradeCost();
        					int enemy = tower.getEnemiesDestroyed();
        					int range = tower.getRange();
        					tower.setSellCost();
        					int sell = tower.getSellCost();
        					tdv.setTowerSpecification(name, enemy, Ucost, range, sell);

        				}
                    }
                });
				
				

			}

		}

	};

	public Tower makeTempTower(String towerId, int x, int y) {
		Tower currTower = null;

		if (towerId.equals("tower1")) {
			currTower = new Tower1(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower2")) {
			currTower = new Tower2(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower3")) {
			currTower = new Tower3(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower4")) {
			currTower = new Tower4(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower5")) {
			currTower = new Tower5(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower6")) {
			currTower = new Tower6(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower7")) {
			currTower = new Tower7(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower8")) {
			currTower = new Tower8(towerId, x * 32, y * 32, 32, 32);
		}
		return currTower;
	}

}
