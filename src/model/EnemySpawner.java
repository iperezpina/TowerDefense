package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Timeline;

/**
 * This class spawns the enemies
 * 
 * @author Ivan, Marisa, Laura, Albert
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

	private int lastTimeSpawned = 0;
	private int currentTime = 0;
	private int healthIncr = 0;
	private List<Enemy> enemyList;
	private ArrayList<Timeline> tlArr = new ArrayList<Timeline>();

	/**
	 * A constructor for this class that takes in how many to spawn for this
	 * EnemySpawner, the time between each enemy spawned, and the enemy to spawn
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int amtToSpawn, float intervalsBetween, int healthIncr, Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public EnemySpawner(int amtToSpawn, float intervalsBetween, int healthIncr, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.enemyToSpawn = e;
		this.enemies = new Enemy[amtToSpawn];
		this.index = 0;
		this.isDone = false;
		this.healthIncr = healthIncr;
	}

	/**
	 * A constructor for this class that takes in how many to spawn for this
	 * EnemySpawner, in this case takes in the list of enemies
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param List<Enemy> enemyList, float intervalsBetween, int healthIncr
	 * @return n/a
	 * @throws n/a
	 */
	public EnemySpawner(List<Enemy> enemyList, float intervalsBetween, int healthIncr) {
		this.amtToSpawn = enemyList.size();
		this.intervalsBetween = intervalsBetween;
		this.enemyToSpawn = null;
		this.enemies = new Enemy[amtToSpawn];
		this.index = 0;
		this.isDone = false;
		this.healthIncr = healthIncr;
		this.enemyList = enemyList;
	}

	// A timer that will be used to spawn an enemy every so often
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
			int counter = 0;
			spawnEnemy();
			for (Enemy e : enemies) {
				if (e != null) {
					if (e.isDead()) {
						EnemyLocator.killEnemy(e);
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
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void start() {
		long start = (long) (1000 * intervalsBetween);
		timer.scheduleAtFixedRate(timerTask, 0, start);
	}

	/**
	 * The basic update method for the enemy spawner, will eventually spawn the
	 * enemies after a certain time.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void update() {
		int counter = 0;
		if (intervalsBetween <= 0) {
			intervalsBetween = .5f;
		}
		currentTime = TimerAll.getTimeInSeconds();
		if (lastTimeSpawned > currentTime) {
			lastTimeSpawned = 0;
		}
		// System.out.println(currentTime);
		if (index < amtToSpawn) {
			if (Math.abs(currentTime - lastTimeSpawned) >= intervalsBetween) {
				lastTimeSpawned = currentTime;
				spawnEnemyList();
			}
		}
		for (Enemy e : enemies) {
			if (e != null) {
				if (e.isDead()) {
					counter += 1;
				}
			}
		}
		if (counter == amtToSpawn) {
			isDone = true;
		}
	}

	/**
	 * Spawns an enemy if the number of spawned enemies has not gone over the limit.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void spawnEnemy() {
		if (index < amtToSpawn) {
			enemies[index] = new Enemy(enemyToSpawn.getImgPath(), enemyToSpawn.getStartLocation(),
					enemyToSpawn.getWidth(), enemyToSpawn.getHeight(), enemyToSpawn.getSpeed(),
					enemyToSpawn.getHealth() + healthIncr, enemyToSpawn.getTm());
			EnemyLocator.addEnemy(enemies[index]);
			enemies[index].update();
			index++;
		}
	}

	/**
	 * Spawns an enemy if the number of spawned enemies has not gone over the limit.
	 * NOTE: This uses the up-to-date file reader code
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void spawnEnemyList() {
		if (index < amtToSpawn) {
			Enemy temp = enemyList.get(index);
			enemies[index] = new Enemy(temp.getImgPath(), temp.getStartLocation(), temp.getWidth(), temp.getHeight(),
					temp.getSpeed(), temp.getHealth() + healthIncr, temp.getTm());
			EnemyLocator.addEnemy(enemies[index]);
			enemies[index].update();
			System.out.println(enemies[index].getTL());
			tlArr.add(enemies[index].getTL());
			index++;
		}
	}

	/**
	 * This is a debug method that is used to print out the enemies in the enemy
	 * list
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return String result
	 * @throws n/a
	 */
	public String printSpawner() {
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

	// Getter
	public Enemy[] getEnemys() {
		return enemies;

	}

	// Getter
	public boolean isDone() {
		return isDone;
	}

	// Setter
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	// Getter
	public ArrayList<Timeline> getTLArr() {
		return tlArr;
	}

}
