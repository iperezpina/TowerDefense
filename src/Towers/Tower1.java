package Towers;

import Projectile.Projectile;
import Projectile.fireProjectile;
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

public class Tower1 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;

	public Tower1(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		additionalInfo();
	}

	public void additionalInfo() {
		this.attackRate = 4;
		this.towerCost = 300;
		this.damage = 2;
		this.range = 150;
		this.towerName = "Fire Tower";
	}

	public void setURL(String str) {
		url = str;
	}

	public String getURL() {
		return url;
	}

	public void shoot() {
		ammo = new fireProjectile("Fireball", 10, x, y, currEnemy, damage);
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

	private int lockMech = 1;

	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX() - x2, getY() - y2);
			if (distance < range && !e.isDead() & lockMech == 0) {
				lockMech += 1;
				currEnemy = e;
				shoot();

			}

		}

		lockMech = 0;
	}

	public Enemy getcurrEnemy() {
		return currEnemy;
	}

}
