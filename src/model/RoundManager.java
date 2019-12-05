package model;

public class RoundManager {

	private int amtToSpawn, waveNumber;
	private float intervalsBetween;
	private EnemySpawner es;
	private boolean startedRound;
	Enemy e;

	public RoundManager(int amtToSpawn, float intervalsBetween, Enemy e) {
		this.amtToSpawn = amtToSpawn;
		this.intervalsBetween = intervalsBetween;
		this.e = e;
		this.es = null;
		this.waveNumber = 0;
		this.startedRound = false;
		newWave();
	}

	public void update() {
		if (es != null) {
			if (es.isDone() == true) {
				System.out.println("ok boomer");
				startedRound = false;
				newWave();
			} else {
				if (startedRound)
					es.update();
			}
		}	
	}

	public void newWave() {
		es = new EnemySpawner(amtToSpawn, intervalsBetween, e);
		startedRound = true;
		waveNumber++;
		System.out.println("Beginning wave: " + waveNumber);
	}
	

}
