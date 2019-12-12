
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

/**
 * This calass takes care of the Rounds that take place
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
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

	/**
	 * Contructor that takes in amt to spawn and enemies and intervals inbetween
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int amtToSpawn, float intervalsBetween, Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public RoundManager(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.e = e;
		this.es = null;
		this.waveNumber = 0;
		this.healthIncr = 5;
		this.startedRound = false;
	}

	/**
	 * Update the method that updates the TimerTask
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void update() {
		timer.scheduleAtFixedRate(timerTask, 0, 500);
	}

	/**
	 * Another constructor that takes in a different set of parameters
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param List<List<Enemy>> enemyInfo, float intervalsBetween, TowerDefenseView
	 *        tdv
	 * @return n/a
	 * @throws n/a
	 */
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

	/**
	 * A task that will occur every so often that spawns an enemy if it can, and
	 * checks if all the enemies are all dead.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
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
							// This is if the Game is Won
							System.out.println("enemysize" + enemyInfo.size());
							if (waveNumber == enemyInfo.size()) {

								tdv.gameWon();
								TimerAll.pause();
								Player.setGameState(GameState.gamepaused);

							}

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

	/**
	 * This function calls a new Wave that comes in
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void newWave() {
		es = new EnemySpawner(amtToSpawn += 2, intervalsBetween -= .5f, healthIncr += 5, e);
		startedRound = true;
		waveNumber++;
	}

	/**
	 * this makes a new Wave list
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
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
	 * two until the round is over and return to a go button..
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<ActionEvent> startRound = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			newWaveList();
		}
	};

	// Getter
	public EnemySpawner getES() {
		return es;
	}

	// Setter
	public void setAnimation(Timeline animation) {
		this.animation = animation;
	}

}
