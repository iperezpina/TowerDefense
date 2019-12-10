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
import javafx.event.EventHandler;
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
		tdv.getRightLabel().setText("Money: " + coins + "\nHealth: " + health);
	}

	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			currURL = currTowerImg.impl_getUrl();
			id = currURL.substring(currURL.length() - 10);
			System.out.println("Tower chosen: " + id);
		}
	};

	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;
			if (id != null && id.length() > 6 ) {
				id = id.substring(0, id.length() - 4);
			}
			if(id==null) {
				System.out.println("hello");
				tdv.setAllBlank();
				
			}

			
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace() && id != null) {

				if (id.equals("tower1")) {
					currTower = new Tower1(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower2")) {
					currTower = new Tower2(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower3")) {
					currTower = new Tower3(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower4")) {
					currTower = new Tower4(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower5")) {
					currTower = new Tower5(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower6")) {
					currTower = new Tower6(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower7")) {
					currTower = new Tower7(id, x * 32, y * 32, 32, 32);
				}
				if (id.equals("tower8")) {
					currTower = new Tower8(id, x * 32, y * 32, 32, 32);
				}

				
				
	

				if (currTower != null && !tdv.getTowers().isThereATower(x, y)) {
					if (coins >= currTower.getTowerCost()) {

						
	
				AudioClip coin = new AudioClip(new File("src/Sounds/coin.wav").toURI().toString());
						coin.play();
						decreaseCoins(currTower.getTowerCost());
						tdv.getTowers().addTower2(currTower, x, y);
						id=null;
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
			
			else {
				Tower tower = tdv.getTowers().getTower(x, y);
				
				if (tower != null) {
					String name= tower.getTowerName();
					int Ucost= tower.getUpgradeCost();
					int enemy= tower.getEnemiesDestroyed();
					int range= tower.getRange();
					tower.setSellCost();
					int sell= tower.getSellCost();
					tdv.setTowerSpecification(name, enemy, Ucost, range, sell);

				}
			

			}			
			
		}

	};

}
