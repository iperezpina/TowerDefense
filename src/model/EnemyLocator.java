package model;

import java.util.ArrayList;

/**
 * This class is a static that return a list of the enemeies
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class EnemyLocator {
	// The list of enemies
	private static ArrayList<Enemy> listofE = new ArrayList<Enemy>();

	/**
	 * Adds a enemy to the List
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public static void addEnemy(Enemy e) {
		listofE.add(e);
	}

	// Getter
	public static ArrayList<Enemy> getEnemies() {
		return listofE;
	}

	/**
	 * Removes an Enemy from the list
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public static void killEnemy(Enemy e) {
		listofE.remove(e);
	}

}
