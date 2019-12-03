package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.Tower;

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

	/**
	 * Add the onclick for the canvas to spawn a tower, NOTE: this is a test will be
	 * restructured to put the tower in a collection.
	 */
	public EventHandler<MouseEvent> debug = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;
			System.out.print("Placing at X: " + x);
			System.out.print("\tY: " + y + "\n");

			Tower tempTower = new Tower(towerImage, x * 32, y * 32, 32, 32);

		}
	};
}
