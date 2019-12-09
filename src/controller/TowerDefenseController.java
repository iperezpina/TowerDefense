
package controller;




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
import javafx.geometry.Pos;
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
	private Player player;
	
	
	public EventHandler<MouseEvent> resume = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tdv.play();
			tdv.drawExtraButtons();
		}
		
	};
	
	public EventHandler<MouseEvent> pause = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tdv.pause();
			tdv.drawGoButton();
		}
		
	};
	
	
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


	
	
	

	public TowerDefenseView getTdv() {
		return tdv;
	}

	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}

}

