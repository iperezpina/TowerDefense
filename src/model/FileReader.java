package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads the file for the game and then makes the rounds
 * 
 * @author Ivan, Marisa, Laura, Albert
 */
public class FileReader {

	private List<List<Enemy>> enemyInfo;
	private TileMap tm;
	private Tile start;

	/**
	 * Contrucor that reads in the map and the start of the tile
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param TileMap tm, Tile start
	 * @return n/a
	 * @throws n/a
	 */
	public FileReader(TileMap tm, Tile start) {
		this.tm = tm;
		this.start = start;
		this.enemyInfo = new ArrayList<List<Enemy>>();
	}

	/**
	 * Reads the file and breaks it into enemies
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String filename
	 * @return n/a
	 * @throws n/a
	 */
	public void read(String filename) {
		try {
			Scanner newScanner = new Scanner(new File(filename));
			String line = "";
			List<Enemy> temp = new ArrayList<Enemy>();
			String[] enemyExtracted;
			int count = 0;

			while (newScanner.hasNext()) {
				line = newScanner.next().trim();
				if (line.length() > 0) {
					if (line.substring(0, 1).equals("*")) {
						if (count > 0 && temp != null) {
							enemyInfo.add(temp);
							temp = new ArrayList<Enemy>();
						}
						count++;
					} else {
						enemyExtracted = line.split("_");
						for (int i = 0; i < Integer.parseInt(enemyExtracted[3]); i++) {
							temp.add(parseEnemyInfo(enemyExtracted));
						}
					}
				}
			}
			newScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function parses the enemy info and returns it
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String[] enemyInfo
	 * @return Enemy temp
	 * @throws n/a
	 */
	private Enemy parseEnemyInfo(String[] enemyInfo) {
		String imgName = enemyInfo[0];
		int speed = Integer.parseInt(enemyInfo[1]);
		int health = Integer.parseInt(enemyInfo[2]);

		Enemy temp = new Enemy(imgName, start, 32, 32, speed, health, tm);
		return temp;

	}

	/**
	 * This is a debug method that prints out the enemies
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String[] array
	 * @return String result
	 * @throws n/a
	 */
	private String printList(String[] array) {
		String result = "[ ";
		for (String s : array) {
			result += "\"" + s + "\"" + ", ";
		}
		result = result.substring(0, result.length() - 2);
		result += " ]";
		return result;
	}

	/**
	 * This mehtod prints the enemy list
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param List<Enemy> enemies
	 * @return String result
	 * @throws n/a
	 */
	private String printListEnemy(List<Enemy> enemies) {
		String result = "[ ";
		for (Enemy e : enemies) {
			result += e + ", ";
		}
		result = result.substring(0, result.length() - 2);
		result += " ]";
		return result;
	}
	//Getter
	public List<List<Enemy>> getEnemies() {
		return enemyInfo;
	}

}
