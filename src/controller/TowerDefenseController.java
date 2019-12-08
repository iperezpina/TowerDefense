
package controller;



import Towers.BasicTower;
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
	private TowerDefenseView tdv;
	private ImageView currTowerImgView;
	private Image currTowerImg;
	private Tower currTower;
	private String currURL;
	private String id;

	
	
	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			System.out.println("You are choosing a tower!");
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			System.out.println("You got a tower!");
			currURL = currTowerImg.impl_getUrl();
			id = currURL.substring(currURL.length()-10);





	public EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			System.out.println("can get x");
			int y = (int) event.getY() / 32;
			System.out.println("can get y");
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace() && currTowerImg !- ) {
				System.out.println("Can place");
				currTower = new BasicTower(currTowerImg, x*32, y*32, 32, 32);
				System.out.println("Can create currTower in place");
				/*if(id.equals("tower1.png")) {
					currTower = (Tower1) currTower;
				}
				if(id.equals("tower2.png")) {
					currTower = (Tower2) currTower;
				}
				if(id.equals("tower3.png")) {
					currTower = (Tower3) currTower;
				}
				if(id.equals("tower4.png")) {
					currTower = (Tower4) currTower;
				}
				if(id.equals("tower5.png")) {
					currTower = (Tower5) currTower;
				}
				if(id.equals("tower6.png")) {
					currTower = (Tower6) currTower;
				}
				if(id.equals("tower7.png")) {
					currTower = (Tower7) currTower;
				}
				if(id.equals("tower8.png")) {
					currTower = (Tower8) currTower;
				}*/
				tdv.getTowers().addTower2(currTower, x, y);
				//tdv.getTm().GetTile(x, y).getType().setCanPlace(false);
				
			}
		}
		
	};
	
	public EventHandler<MouseEvent> hurtEnemy = new EventHandler<MouseEvent>() {
		// to debug health issues only

		@Override
		public void handle(MouseEvent event) {
			
		}
		
	};

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}
}

