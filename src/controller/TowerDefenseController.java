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
			//System.out.print("Placing at X: " + x);
			//System.out.print("\tY: " + y + "\n");

			//Only places tower on placeable tiles
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace()) {
				//Tower tempTower = new Tower(towerImage, x * 32, y * 32, 32, 32);
				BasicTower dog = new BasicTower(towerImage, x* 32, y *32, 32, 32);
				tdv.getTowers().addTower2(dog, x, y);
				
			}
		}
	};
	
	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

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

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}
}
