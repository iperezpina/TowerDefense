package model;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controller.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.TowerDefenseView;

public class RoundManager {

	private int amtToSpawn, waveNumber;
	private float intervalsBetween;
	private EnemySpawner es;
	private boolean startedRound;
	private int healthIncr;
	Enemy e;
	
	//For second roundManager implementation
	private List<List<Enemy>> enemyInfo;
	private TowerDefenseView tdv;

	public RoundManager(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.e = e;
		this.es = null;
		this.waveNumber = 0;
		this.healthIncr = 5;
		this.startedRound = false;
	}

	public void update() {
		timer.scheduleAtFixedRate(timerTask, 0, 500);
	}
	
	public RoundManager(List<List<Enemy>> enemyInfo, float intervalsBetween, TowerDefenseView tdv) {
		this.amtToSpawn = 0;
		this.intervalsBetween = intervalsBetween;
		this.e = null;
		this.es = null;
		this.waveNumber = 0;
		this.healthIncr = 5;
		this.startedRound = false;
		this.enemyInfo = enemyInfo;
		this.tdv = tdv;
	}

	Timer timer = new Timer();

	// A task that will occur every so often that spawns an enemy if it can, and
	// checks if all the enemies are all dead.
	TimerTask timerTask = new TimerTask() {
		public void run() {
			if (es != null) {
				if (es.isDone() == true && startedRound) {
					startedRound = false;
					System.out.println("Round " + waveNumber + " ended!");
					
					//Add the cash at end of round
					Platform.runLater( new Runnable() {
	                    @Override
	                    public void run() {
	                        Player.addCash(100);
	                    }
	                });
					
					//Alerts the user that the round ended
					Platform.runLater( new Runnable() {
	                    @Override
	                    public void run() {
	                    	tdv.getRoundLabel().setText("Round " + waveNumber + " ended!");
	                    	tdv.drawGoButton();
	                    }
	                });
						

				} else {
					es.update();
				}
			}
		}

	};

	public void newWave() {
		
		es = new EnemySpawner(amtToSpawn += 2, intervalsBetween -= .5f, healthIncr += 5, e);
		startedRound = true;
		waveNumber++;
	}
	
	public void newWaveList() {
		es = new EnemySpawner(enemyInfo.get(waveNumber), intervalsBetween -= .10f, 0);
		startedRound = true;
		waveNumber++;
		System.out.println("Round " + waveNumber + " started!");
		tdv.getRoundLabel().setText("Round " + waveNumber);
	}

	/**
	 * Event handler that will start a new round will turn the into a x2 button if
	 * clicked on, then paused button if pressed again it will cycle between those
	 * two until the round is over and return to a go button.
	 */
	public EventHandler<ActionEvent> startRound = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			newWaveList();
		}
	};

	public EnemySpawner getES() {
		return es;
	}


}
