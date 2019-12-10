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

		@SuppressWarnings( "deprecation" )
		@Override
		public void handle(MouseEvent event) {
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			currURL = currTowerImg.impl_getUrl();
			id = currURL.substring(currURL.length()-10);
			System.out.println("Tower chosen: " + id);
		}
	};
	
	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace() && currTowerImg != null ) {

				if (id.equals("tower1.png")) {
					currTower = new Tower1(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower2.png")) {
					currTower = new Tower2(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower3.png")) {
					currTower = new Tower3(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower4.png")) {
					currTower = new Tower4(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower5.png")) {
					currTower = new Tower5(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower6.png")) {
					currTower = new Tower6(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower7.png")) {
					currTower = new Tower7(currTowerImg, x*32, y*32, 32, 32);
				}
				if (id.equals("tower8.png")) {
					currTower = new Tower8(currTowerImg, x*32, y*32, 32, 32);
				}
				if (currTower != null && coins >= currTower.getTowerCost()) {
					if (tdv.getTowers().addTower2(currTower, x, y)) {
						AudioClip coin = new AudioClip(new File("src/Sounds/coin.wav").toURI().toString());
						coin.play();
						decreaseCoins(currTower.getTowerCost());
					}
					
					

				}
			}
		}
		
	};
	
}
