package model;

import java.util.ArrayList;

public class EnemyLocator {
	
	private static ArrayList<Enemy> listofE = new ArrayList<Enemy>();
	public static void addEnemy(Enemy e) {
		listofE.add(e);
	}
	
	public static ArrayList<Enemy> getEnemies() {
		return listofE;
	}


}
