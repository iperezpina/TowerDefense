package main;

import model.FileReader;
import model.Tile;
import model.TileMap;

/**
 * This main function calls the FileReader class and reads the file and inputs
 * it
 * 
 * @author Ivan, Marisa, Laura, Albert
 * @param String[] args
 * @return n/a
 * @throws n/a
 */
public class fileReaderRunner {
	public static void main(String[] args) {
		FileReader fr = new FileReader(null, null);
		fr.read("src/level1.txt");
	}
}
