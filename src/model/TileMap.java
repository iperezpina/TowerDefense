package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Tile Map class that represnts a grid of tiles classes
 * 
 * @author Ivan, Marisa, Laura, Albert
 */
public class TileMap {

	// Variables for this class
	private List<List<Tile>> tileGrid;
	private static Timeline tl;

	/**
	 * The default constructor class that would make a map of only grass tiles.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public TileMap() {
		this.tileGrid = new ArrayList<List<Tile>>();

		for (int i = 0; i < 20; i++) {
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < 15; j++) {
				temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.Dirt));
			}
			this.tileGrid.add(temp);

		}
	}

	/**
	 * A more detailed constructor that takes in a 2d list of integers to specify
	 * what tiles should be used in creating a map. Makes creating a level easier to
	 * do.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int[][] tileMap
	 * @return n/a
	 * @throws n/a
	 */
	public TileMap(int[][] tileMap) {
		Random rnd = new Random();
		int rndNumGround = -1;
		int rndNumLava = -1;
		this.tileGrid = new ArrayList<List<Tile>>();

		for (int i = 0; i < 20; i++) {
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < 15; j++) {
				int value = tileMap[j][i];
				rndNumGround = rnd.nextInt(5);
				rndNumLava = rnd.nextInt(20);
				switch (value) {
				case 0:
					if (rndNumGround == 0 || rndNumGround == 1 || rndNumGround == 2)
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.HellGround1));
					if (rndNumGround == 3)
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.HellGround2));
					if (rndNumGround == 4)
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.HellGround3));
					break;

				case 1:
					temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.Dirt));
					break;

				case 2:
					if (rndNumLava != 0 && rndNumLava != 1) {
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.Lava1));
					} else if (rndNumLava == 0) {
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.Lava2));
					} else if (rndNumLava == 1) {
						temp.add(new Tile(i * 32, j * 32, 32, 32, TileType.Lava3));
					}

					break;
				}
			}
			this.tileGrid.add(temp);
		}
	}

	/**
	 * Sets a certain tile within the Tilemap
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y, TileType type
	 * @return n/a
	 * @throws n/a
	 */
	public void SetTile(int x, int y, TileType type) {
		Tile newTile = new Tile(x * 32, y * 32, 32, 32, type);
		tileGrid.get(x).set(y, newTile);
	}

	/**
	 * Gets the tile at a certain indexed value
	 *
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y
	 * @return n/a
	 * @throws n/a
	 */
	public Tile GetTile(int x, int y) {
		if (x < 0 || y < 0 || x >= 20 || y >= 15)
			return new Tile(0, 0, 0, 0, TileType.DEBUG);
		else
			return tileGrid.get(x).get(y);
	}

	/**
	 * Draws all the tiles in the Tilemap
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void Draw() {
		for (List<Tile> list : tileGrid) {
			for (Tile tile : list) {
				tile.Draw();
			}
		}
	}

	/**
	 * Creates an animation that draws the level, NOTE: This update should be used
	 * before any other update function in the view
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}

	/**
	 * A basic animation that draws the level.
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent arg0
	 * @return n/a
	 * @throws n/a
	 */
	private class AnimationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Draw();
		}

	}
}
