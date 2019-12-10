package main;

import model.FileReader;
import model.Tile;
import model.TileMap;

public class fileReaderRunner {
	
	public static void main(String[] args) {
		
		FileReader fr = new FileReader(null, null);
		fr.read("src/level1.txt");
	}
}
