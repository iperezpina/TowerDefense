
package model;

import java.util.Collection;
import java.util.HashMap;

import Towers.Tower;
import Towers.Tower1;
import Towers.Tower2;
import Towers.Tower3;
import Towers.Tower4;
import Towers.Tower5;
import Towers.Tower6;
import Towers.Tower7;
import Towers.Tower8;
import controller.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import view.TowerDefenseView;

/**
 * Tower Holder class that holds all the Tower using a map
 * 
 * @author Ivan, Marisa, Laura, Albert
 * @param n/a
 * @return n/a
 * @throws n/a
 */
public class TowerHolder {

	private TileMap map;
	private static HashMap<String, Tower> towers2;
	private static Timeline tl;

	/**
	 * Constructor
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param TileMap map
	 * @return n/a
	 * @throws n/a
	 */
	public TowerHolder(TileMap map) {
		this.map = map;
		this.towers2 = new HashMap<String, Tower>();

	}

	/**
	 * Adds a tower to the TowerHolderMap that maps the location to the Tower
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower newTower, int x, int y
	 * @return boolean
	 * @throws n/a
	 */
	public static boolean addTower2(Tower newTower, int x, int y) {
		String newKey = x + "," + y;
		if (towers2.containsKey(newKey)) {
			return false;
		} else {
			towers2.put(newKey, newTower);
			return true;
		}
	}

	/**
	 * update method that makes the timeline and plays it
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}

	/**
	 * Event Handler that casts the Tower into their classes
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent arg0
	 * @return n/a
	 * @throws n/a
	 */
	private static class AnimationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Collection<Tower> temp = towers2.values();
			for (Tower t : temp) {
				if (t instanceof Tower1) {
					Tower1 newtemp = (Tower1) t;
					newtemp.update();
				}
				if (t instanceof Tower2) {
					Tower2 newtemp = (Tower2) t;
					newtemp.update();
				}
				if (t instanceof Tower3) {
					Tower3 newtemp = (Tower3) t;
					newtemp.update();
				}
				if (t instanceof Tower4) {
					Tower4 newtemp = (Tower4) t;
					newtemp.update();
				}
				if (t instanceof Tower5) {
					Tower5 newtemp = (Tower5) t;
					newtemp.update();
				}
				if (t instanceof Tower6) {
					Tower6 newtemp = (Tower6) t;
					newtemp.update();
				}
				if (t instanceof Tower7) {
					Tower7 newtemp = (Tower7) t;
					newtemp.update();
				}
				if (t instanceof Tower8) {
					Tower8 newtemp = (Tower8) t;
					newtemp.update();
				}

			}
		}

	}

	/**
	 * Function that return a boolean given a postion and if there is a tower there
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y
	 * @return boolean isThere
	 * @throws n/a
	 */
	public static boolean isThereATower(int x, int y) {
		boolean isThere = false;
		String newKey = x + "," + y;
		if (towers2.containsKey(newKey)) {
			return true;
		}
		return isThere;
	}

	/**
	 * Function that gets a Tower at a given postion
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y
	 * @return Tower temp
	 * @throws n/a
	 */
	public static Tower getTower(int x, int y) {
		Tower temp = null;
		String newKey = x + "," + y;
		if (towers2.containsKey(newKey)) {
			temp = towers2.get(newKey);
			temp.setIsSelected(!(temp.isSelected()));
		}

		return temp;
	}

	/**
	 * Removes a tower at a given postion
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y
	 * @return Tower temp
	 * @throws n/a
	 */
	public static Tower removeTower(int x, int y) {
		Tower temp = null;
		String newKey = x + "," + y;
		if (towers2.containsKey(newKey)) {
			temp = towers2.get(newKey);
			towers2.remove(newKey, temp);
			Player.addCash(temp.getSellCost());
			update();
		}
		return temp;
	}
}
