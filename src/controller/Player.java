package controller;

import Towers.Tower;
import Towers.Tower1;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.TowerDefenseView;

public class Player {
	private int health;
	private int coins;
	private TowerDefenseView tdv;
	private ImageView currTowerImgView;
	private Image currTowerImg;
	private Tower currTower;
	
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

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}
	
	public void decreaseCoins(int shift) {
		coins -= shift;
	}
	
	public void increaseCoins(int shift) {
		coins += shift;
	}
	
	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			System.out.println("You are choosing a tower!");
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			System.out.println("You got a tower!" + currTowerImg);
			
			
		}
	};
	
	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			System.out.println("can get x");
			int y = (int) event.getY() / 32;
			System.out.println("can get y");
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace()) {
				System.out.println("Can place");
				currTower = new Tower(currTowerImg, x*32, y*32, 32, 32);
				
				
				
				
				System.out.println("Can create currTower in place");
				tdv.getTowers().addTower2(currTower, x, y);
				//tdv.getTm().GetTile(x, y).getType().setCanPlace(false);
				
			}
		}
		
	};
}
