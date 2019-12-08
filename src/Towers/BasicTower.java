package Towers;

import controller.Drawer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Enemy;
import model.EnemyLocator;
import model.TimerAll;

public class BasicTower extends Tower {

	private String towerName = "Dog";
	private float towerSpeed = 2f;
	private int towersDestroyed = 0;

	// This functions control this Towers Basic Ideas
	// Range is how it goes to hit
	// AttackRate is fast it can attack
	// Tl is the animation timeline
	private final int range = 100;
	private int attackRate = 3;
	private Timeline tl;

	private int lastTimeAttacked = 0;
	private int currentTime = 0;

	public BasicTower(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
	}

	public void printTowerData() {
		System.out.printf("Tower: %s\nEnemies killed: %d\nRange: %f\nSpeed: %f\n", towerName, towersDestroyed, range,
				towerSpeed);
	}

	/**
	 * Update method in charge of any movement (rotation) of the tower and drawing
	 * of the tower
	 */
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(500), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}

	/**
	 * A basic animation handler that just draws the tower at the moment
	 *
	 */
	private class AnimationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Draw();
			currentTime = TimerAll.getTimeInSeconds();
			if (Math.abs(currentTime - lastTimeAttacked) >= attackRate) {
				lastTimeAttacked = currentTime;

				drawRange();
			}

		}

	}

	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX() - x2, getY() - y2);
			if (distance < range) {
				// minus hp

				System.out.println("enemy entered range");

			}

		}
	}

}
