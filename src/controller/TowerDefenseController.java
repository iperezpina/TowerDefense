package controller;

import Towers.BasicTower;
import Towers.Tower;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.TowerDefenseView;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marisa
 *         [Last Name] 
 * 
 *         Summary: [Summary goes here]
 *
 */
public class TowerDefenseController {

	// Variables for this class
	private Image towerImage = new Image("Images/temptower.png");
	private TowerDefenseView tdv;
	private ImageView currTowerImgView;
	private Image currTowerImg;
	private Tower currTower;


	/**
	 * Add the onclick for the canvas to spawn a tower, NOTE: this is a test will be
	 * restructured to put the tower in a collection.
	 */
	public EventHandler<MouseEvent> debug = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;

			// Only places tower on placeable tiles
//			if (tdv.getTm().GetTile(x, y).getType().isCanPlace()) {
//				BasicTower temp = new BasicTower(currTowerImg, x, y, 32, 32);
//				tdv.getTowers().addTower2(temp, x, y);
//
//			}
		}
	};

	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			//System.out.println("You are choosing a tower!");
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			
			

		}
	};
	
	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace()) {
				currTower = new BasicTower(currTowerImg, x * 32, y * 32, 32, 32);
				tdv.getTowers().addTower2(currTower, x, y);
				
			}
		}
		
	};

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}
}
