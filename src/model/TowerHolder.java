package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Towers.BasicTower;
import Towers.OneShotTower;
import Towers.Tower;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class TowerHolder {

	private TileMap map;
	private List<Tower> towers;
	private HashMap<String, Tower> towers2;
	private Timeline tl;
	
	public TowerHolder(TileMap map) {
		this.map = map;
		this.towers = new ArrayList<Tower>();
		this.towers2 = new HashMap<String, Tower>();
	}
	
	public void addTower(Tower newTower) {
		towers.add(newTower);
		System.out.println("Added a tower!");
	}
	
	public void addTower2(Tower newTower, int x, int y) {
		String newKey = x + "," + y;
		if (towers2.containsKey(newKey)) {
			System.out.println("A tower already exists there!");
			Tower temp = towers2.get(newKey);
			if (temp instanceof BasicTower) {
				BasicTower dogTemp = (BasicTower)temp;
				
			}
		}
		else {
			towers2.put(newKey, newTower);
		}
	}
	
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(10), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}
	
	private class AnimationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Collection<Tower> temp =  towers2.values();
			for (Tower t: temp) {
				BasicTower newtemp = (BasicTower) t;
				newtemp.update();
			}
		}

	}
	
}
