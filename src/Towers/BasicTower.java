package Towers;

import Projectile.Projectile;
import Projectile.boneProjectile;
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

public class BasicTower extends Tower {


	// Tl is the animation timeline
	private Timeline tl;
	private boneProjectile projectile;
	

	public BasicTower(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		this.projectile= new boneProjectile("Bone", 6, x, y);
	}

	public void printTowerData() {
		
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
			//System.out.println("fdfddfdfdfdf");
			Draw();
			currentTime = TimerAll.getTimeInSeconds();
			if (Math.abs(currentTime - lastTimeAttacked) >= attackRate) {
				lastTimeAttacked = currentTime;

				drawRange();
				
				
			}

		}

	}

	
	public void TowerDamage(Enemy e) {
		
		e.setHealth(e.getHealth() - 1);
	}
	
	
	
	
	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX() - x2, getY() - y2);
			if (distance < range) {

				
				TowerDamage(e);
				System.out.println("enemy entered range");

			}

		}
	}

}

