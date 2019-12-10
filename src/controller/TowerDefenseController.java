
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.RoundManager;
import model.TimerAll;
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
	private RoundManager rm;
	
	
	public EventHandler<MouseEvent> resume = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tdv.play();
			tdv.drawExtraButtons();
			rm.newWaveList();
		}
		
	};
	
	public EventHandler<ActionEvent> pause = new EventHandler<ActionEvent>() {

		private boolean isPaused = false;
		@Override
		public void handle(ActionEvent event) {
			isPaused = !isPaused;
			if (isPaused) {
				tdv.pause();
				//tdv.drawGoButton();
				Button temp = (Button) event.getSource();
				temp.setText("PLAY");
			}
			if (!isPaused) {
				tdv.play();
				Button temp = (Button) event.getSource();
				temp.setText("PAUSE");
			}
			
		}
		
	};
	
	public EventHandler<ActionEvent> timeTwo = new EventHandler<ActionEvent>() {

		private boolean isOn = false;
		@Override
		public void handle(ActionEvent event) {
			isOn = !isOn;
			if (isOn) {
				TimerAll.timesTwo();
				//tdv.drawGoButton();
				Button temp = (Button) event.getSource();
				temp.setText("x1");
			}
			if (!isOn) {
				TimerAll.play();
				Button temp = (Button) event.getSource();
				temp.setText("x2");
			}
			
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

	public RoundManager getRm() {
		return rm;
	}

	public void setRm(RoundManager rm) {
		this.rm = rm;
	}

}

