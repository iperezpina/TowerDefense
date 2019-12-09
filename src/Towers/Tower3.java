package Towers;

import Projectile.Projectile;
import Projectile.lazerProjectile;
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

public class Tower3 extends Tower{
	
	private String url;
	private Projectile ammo;
	private Timeline tl;
	
	public Tower3(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		additionalInfo();
	}
	
	public void additionalInfo() {
		this.attackRate = 1;
		this.towerCost = 400;
		this.damage = 1;
		this.range = 200;
		this.towerName = "Pyramid Tower";
	}
	
	public void setURL(String str) {
		url = str;
	}
	public String getURL() {
		return url;
	}

	public void shoot() {
		ammo = new lazerProjectile("lazer", 10, x, y);
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

	public void TowerDamage(Enemy e) {

		e.setHealth(e.getHealth() - damage);
	}

	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX() - x2, getY() - y2);
			if (distance < range && !e.isDead()) {
				shoot();
				TowerDamage(e);

			}

		}
	}
}
