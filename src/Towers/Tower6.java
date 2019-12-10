package Towers;

import Projectile.Projectile;
import Projectile.bloodProjectile;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Enemy;
import model.EnemyLocator;
import model.TimerAll;

public class Tower6 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;

	public Tower6(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		additionalInfo();
	}

	public void additionalInfo() {
		this.attackRate = 6;
		this.towerCost = 666;
		this.damage = 1;
		this.range = 250;
		this.towerName = "Blood Tower";
	}

	public void setURL(String str) {
		url = str;
	}

	public String getURL() {
		return url;
	}

	public void shoot() {
		ammo = new bloodProjectile("blood", 4, x, y, currEnemy, damage);
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



	private Enemy currEnemy = null;

	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX() - x2, getY() - y2);

			if (distance + 20 < range && !e.isDead()) {
				currEnemy = e;
				shoot();

			}

		}
	}

	public Enemy getcurrEnemy() {
		return currEnemy;
	}
}
