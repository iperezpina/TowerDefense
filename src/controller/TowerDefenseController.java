package controller;

import Towers.DogTower;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.Tower;
import view.TowerDefenseView;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class TowerDefenseController {

	// Variables for this class
	private Image towerImage = new Image("Images/temptower.png");
	private TowerDefenseView tdv;

	/**
	 * Add the onclick for the canvas to spawn a tower, NOTE: this is a test will be
	 * restructured to put the tower in a collection.
	 */
	public EventHandler<MouseEvent> debug = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;
			//System.out.print("Placing at X: " + x);
			//System.out.print("\tY: " + y + "\n");

			//Only places tower on placeable tiles
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace()) {
				//Tower tempTower = new Tower(towerImage, x * 32, y * 32, 32, 32);
				DogTower dog = new DogTower(towerImage, x* 32, y *32, 32, 32);
				tdv.getTowers().addTower2(dog, x, y);
				
			}
		}
	};
	
	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			System.out.println("You are choosing a tower!");


		}
	};

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}
}
