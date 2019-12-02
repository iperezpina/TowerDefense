package model;

import java.sql.Time;

public class EnemySpawner {
	
	private int amtToSpawn;
	private float intervalsBetween, timePassed;
	private Enemy[] enemies;
	private Enemy enemyToSpawn;
	
	
	public EnemySpawner(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.enemyToSpawn = e;
		this.enemies = new Enemy[amtToSpawn];
		this.timePassed = 0f;
	}
	
	public void update() {
		Time time = new Time(0);
		timePassed += time.getTime();
		System.out.println(timePassed);
		if (timePassed >= intervalsBetween) {
			System.out.println("Spawned an enemy");
			timePassed = 0f;
		}
	}
}
