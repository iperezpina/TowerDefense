package Projectile;

import java.io.File;

import Towers.Tower;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.Enemy;

/**
 * This class handles the blood projectile class
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class bloodProjectile extends Projectile {

	private Timeline tl;
	private AudioClip bloodSound = new AudioClip(new File("src/Sounds/splat.wav").toURI().toString());
	private boolean canSlow, canPoison;
	private Tower tower;

	/**
	 * Constructor for blood Projectile
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imageName, int speed, int x, int y, Enemy EtoShoot, int damage,
	 *               boolean canSlow, boolean canPoison Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public bloodProjectile(String imageName, int speed, int x, int y, Enemy EtoShoot, int damage, boolean canSlow,
			boolean canPoison, Tower tower) {
		super(imageName, speed, x, y, EtoShoot, damage);
		this.canSlow = canSlow;
		this.canPoison = canPoison;
		this.tower = tower;
		;
		update();

	}

	/**
	 * Update Mehtod that starts the timeline for the animation
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
		bloodSound.setVolume(.5f);

	}

	/**
	 * Event Handler that shoots out a projectile and the tracking to get to the
	 * enemy It uses a algortihm to find it way to the enemy
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent arg0
	 * @return n/a
	 * @throws n/a
	 */
	private class AnimationHandler implements EventHandler<ActionEvent> {

		int count = 0;

		@Override
		public void handle(ActionEvent arg0) {
			if (count > 20) {
				tl.stop();
			}
			// System.out.println(EtoShoot);
			double length = Math.sqrt(
					(EtoShoot.getX() - x) * (EtoShoot.getX() - x) + (EtoShoot.getY() - y) * (EtoShoot.getY() - y));
			x = x + ((EtoShoot.getX() - x) / length * speed);

			y = y + ((EtoShoot.getY() - y) / length * speed);

			if (handleCol()) {
				bloodSound.play();
				if (EtoShoot.getHealth() - damage <= 0) {
					tower.setKillCount();
				}
				TowerDamage(EtoShoot);
				if (canSlow) {
					EtoShoot.slowEnemy();

				}
				if (canPoison) {
					EtoShoot.poisonEnemy();
				}

				tl.stop();
			}

			draw();
			count++;
		}

	}
}
