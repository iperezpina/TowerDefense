package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class EnemySpawner {

	// Variables for this class
	private int amtToSpawn;
	private int index;
	private float intervalsBetween;
	private Enemy[] enemies;
	private Enemy enemyToSpawn;
	private boolean isDone;

	/**
	 * A constructor for this class that takes in how many to spawn for this
	 * EnemySpawner, the time between each enemy spawned, and the enemy to spawn
	 * 
	 * @param amtToSpawn
	 * @param intervalsBetween
	 * @param e
	 */
	public EnemySpawner(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.enemyToSpawn = e;
		this.enemies = new Enemy[amtToSpawn];
		this.index = 0;
		this.isDone = false;
	}

	// A timer that will be used to spawn an enemy every so often
	Timer timer = new Timer();

	// A task that will occur every so often that spawns an enemy if it can, and
	// checks if all the enemies are all dead.
	TimerTask timerTask = new TimerTask() {
		public void run() {
			int counter = 0;
			spawnEnemy();
			for (Enemy e : enemies) {
				if (e != null) {
					if (e.isDead()) {
						counter += 1;
					}
				}
			}
			if (counter == amtToSpawn) {
				System.out.println("All enemies in EnemySpawner are dead");
				isDone = true;
			}
		}

	};

	/**
	 * Calls the timertask above to occur at a given rate.
	 */
	public void start() {
		long start = (long) (1000 * intervalsBetween);
		timer.scheduleAtFixedRate(timerTask, 0, start);
	}

	/**
	 * The basic update method for the enemy spawner, will eventually spawn the
	 * enemies after a certain time.
	 */
	public void update() {
		start();
	}

	/**
	 * Spawns an enemy if the number of spawned enemies has not gone over the limit.
	 */
	public void spawnEnemy() {
		if (index < amtToSpawn) {
			enemies[index] = new Enemy(enemyToSpawn.getImgPath(), enemyToSpawn.getStartLocation(), enemyToSpawn.getWidth(),
					enemyToSpawn.getHeight(), enemyToSpawn.getSpeed(), enemyToSpawn.getTm());
			enemies[index].update();
			index++;
		}

	}

	/**
	 * This is a debug method that is used to print out the enemies in the enemy
	 * list
	 * 
	 * @return
	 */
	private String printSpawner() {
		String result = "[";
		for (Enemy e : enemies) {
			if (e == null) {
				result += " empty ";
			} else {
				result += " " + e.toString() + " ";
			}
		}
		result += "]";

		return result;

	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}
