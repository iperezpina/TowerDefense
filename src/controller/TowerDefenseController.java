
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
import model.GameState;
import model.RoundManager;
import model.TimerAll;
import view.TowerDefenseView;

/**
 * The Controller
 * 
 * @author Ivan, Marisa, Laura, Albert
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

	/**
	 * Event Handler that deals with the GO button functionality
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param MouseEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<MouseEvent> go = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			rm.newWaveList();
			tdv.startPlay();
			tdv.drawExtraButtons();
			Player.setGameState(GameState.gameplay);
			// rm.newWaveList();
			// tdv.play();
		}

	};

	/**
	 * Event Handler that deals with the Resume button that resumes the game
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param MouseEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<MouseEvent> resume = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tdv.play();
			tdv.drawExtraButtons();
			// rm.newWaveList();
		}

	};

	/**
	 * Event Handler that deals with the pause button that pauses the game
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<ActionEvent> pause = new EventHandler<ActionEvent>() {
		private boolean isPaused = false;

		@Override
		public void handle(ActionEvent event) {
			isPaused = !isPaused;
			if (isPaused) {
				tdv.pause();
				// tdv.drawPlayButton();
				Button temp = (Button) event.getSource();
				temp.setText("PLAY");
				Player.setGameState(GameState.gamepaused);
			}
			if (!isPaused) {
				tdv.play();
				Button temp = (Button) event.getSource();
				temp.setText("PAUSE");
				Player.setGameState(GameState.gameplay);
			}

		}

	};

	/**
	 * Deals times two button that makes the game double the speed
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<ActionEvent> timeTwo = new EventHandler<ActionEvent>() {

		private boolean isOn = false;

		@Override
		public void handle(ActionEvent event) {
			isOn = !isOn;
			if (isOn) {
				TimerAll.timesTwo();
				// tdv.drawGoButton();
				Button temp = (Button) event.getSource();
				temp.setText("x1");
				Player.setGameState(GameState.gamex2);
			}
			if (!isOn) {
				TimerAll.play();
				Button temp = (Button) event.getSource();
				temp.setText("x2");
				Player.setGameState(GameState.gameplay);
			}

		}
	};

	/**
	 * Event Handler that chooseTower and makes the class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param MouseEvent event
	 * @return n/a
	 * @throws n/a
	 */
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

	// Getter
	public TowerDefenseView getTdv() {
		return tdv;
	}

	// Setter
	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}

	// Getter
	public RoundManager getRm() {
		return rm;
	}

	// Getter
	public void setRm(RoundManager rm) {
		this.rm = rm;
	}

}
