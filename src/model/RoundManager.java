package model;


import java.util.ArrayList;

import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RoundManager {

	private int amtToSpawn, waveNumber;
	private float intervalsBetween;
	private EnemySpawner es;
	private boolean startedRound;
	private int healthIncr;
	Enemy e;
	private ArrayList<Enemy> eList = new ArrayList<Enemy>();

	public RoundManager(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.e = e;
		this.es = null;
		this.waveNumber = 0;
		this.healthIncr = 5;
		this.startedRound = false;

		//newWave();

	}

	public void update() {
		timer.scheduleAtFixedRate(timerTask, 0, 500);
	}

	Timer timer = new Timer();

	// A task that will occur every so often that spawns an enemy if it can, and
	// checks if all the enemies are all dead.
	TimerTask timerTask = new TimerTask() {
		public void run() {
			if (es != null) {
				if (es.isDone() == true) {
					// TODO implement add money back after round ends
					// add money to player
					startedRound = false;

					// TODO this is why new enemies come up when the last wave reaches the end
					//newWave();

					System.out.println("Round " + waveNumber + " ended!");
					// newWave();

				} else {
					es.update();
					addEnemiesToList();
				}
			}
		}

	};

	public void newWave() {
		es = new EnemySpawner(amtToSpawn += 2, intervalsBetween -= .5f, healthIncr += 5, e);
		startedRound = true;
		waveNumber++;
	}

	
	public void addEnemiesToList() {
		for(Enemy e: es.getEnemys()) {
			eList.add(e);
		}
	}
	public ArrayList<Enemy> getEList(){
		return eList;
	}
	


	/**
	 * Event handler that will start a new round will turn the into a x2 button if
	 * clicked on, then paused button if pressed again it will cycle between those
	 * two until the round is over and return to a go button.
	 */
	public EventHandler<ActionEvent> startRound = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			newWave();
		}
	};

	public EnemySpawner getES() {
		return es;
	}



}
