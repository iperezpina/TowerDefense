package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	private List<List<Enemy>> enemyInfo;
	private TileMap tm;
	private Tile start;
	
	public FileReader(TileMap tm, Tile start) {
		this.tm = tm;
		this.start = start;
		this.enemyInfo = new ArrayList<List<Enemy>>();
	}
	
	public void read(String filename) {
		try {
			Scanner newScanner = new Scanner(new File(filename));
			String line = "";
			List<Enemy> temp = new ArrayList<Enemy>();
			String[] enemyExtracted;
			int count = 0;
			
			while(newScanner.hasNext()) {
				line = newScanner.next().trim();
				if (line.length() > 0) {
					if (line.substring(0,1).equals("*")) {
						if (count > 0 && temp != null) {
							enemyInfo.add(temp);
							temp = new ArrayList<Enemy>();
						}
						count++;
					}
					else {
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
	
	private Enemy parseEnemyInfo(String[] enemyInfo) {
		String imgName = enemyInfo[0];
		int speed = Integer.parseInt(enemyInfo[1]);
		int health = Integer.parseInt(enemyInfo[2]);
		
		Enemy temp = new Enemy(imgName, start, 32, 32, speed, health, tm);
		return temp;
		
	}
	
	private String printList(String [] array) {
		String result = "[ ";
		for (String s: array) {
			result += "\"" + s + "\"" + ", ";
		}
		result = result.substring(0, result.length() -2);
		result += " ]";
		return result;
	}
	
	private String printListEnemy(List<Enemy> enemies) {
		String result = "[ ";
		for (Enemy e: enemies) {
			result += e + ", ";
		}
		result = result.substring(0, result.length() -2);
		result += " ]";
		return result;
	}
	
	public List<List<Enemy>> getEnemies() {
		return enemyInfo;
	}
	
}
