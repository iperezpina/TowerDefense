
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import controller.Player;
import javafx.animation.Timeline;
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
	private ArrayList<List<Enemy>> eList = new ArrayList<List<Enemy>>();

	// For second roundManager implementation
	private List<List<Enemy>> enemyInfo;
	private TowerDefenseView tdv;
	private Timeline animation;

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

					// Add the cash at end of round
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Player.addCash(100);
						}
					});

					// Alerts the user that the round ended
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							tdv.getRoundLabel().setText("Round " + waveNumber + " ended!");
							tdv.drawGoButton();
						}
					});

					// newWaveList();
					// TODO this is why new enemies come up when the last wave reaches the end

				} else {
					es.update();
					// addEnemiesToList();
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

		es = new EnemySpawner(enemyInfo.get(waveNumber), intervalsBetween -= .10f, healthIncr++);
		eList.add(enemyInfo.get(waveNumber));
		startedRound = true;
		waveNumber++;
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

	public void pauseES() {
		//System.out.println(this.es.getTLArr());

		/*
		 * for(Timeline t: this.es.getTLArr()) { setAnimation(t); animation.pause(); }
		 */
//		for (Enemy e : this.es.getEnemys()) {
//			if (e != null) {
//
//				e.setisPaused();
//				System.out.println("enemy is not null");
//			}
//		}
//		System.out.println(this.es.getTLArr());

	}

	public void playES() {
//		for (List<Enemy> le : eList) {
//			for (Enemy e : le) {
//				System.out.println(e);
//				if (e != null) {
//					System.out.println(e);
//					// e.play();
//				}
//			}
//		}
	}

	/*
	 * public void startES() { for (List<Enemy> le: eList) { for(Enemy e: le) {
	 * System.out.println(e); if(e != null) { System.out.println(e);
	 * 
	 * //Timeline t = e.getTL(); //System.out.println(t); //t.play();
	 * //e.playFromStart(); } } } }
	 * 
	 * public void addEnemiesToList() { for(Enemy e: es.getEnemys()) { eList.add(e);
	 * } } public ArrayList<Enemy> getEList(){ return eList; }
	 */

	// TODO get timeline from each enemy to this animation
	public void setAnimation(Timeline animation) {
		this.animation = animation;
	}

}
