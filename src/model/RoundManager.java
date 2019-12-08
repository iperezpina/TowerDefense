package model;

import java.util.Timer;
import java.util.TimerTask;

public class RoundManager {

	private int amtToSpawn, waveNumber;
	private float intervalsBetween;
	private EnemySpawner es;
	private boolean startedRound;
	private int healthIncr;
	Enemy e;

	public RoundManager(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.e = e;
		this.es = null;
		this.waveNumber = 0;
		this.healthIncr = 5;
		this.startedRound = false;
		newWave();
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
					startedRound = false;
					newWave();
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
		//System.out.println("Beginning wave: " + waveNumber);
	}
	

}
