package Projectile;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.Enemy;

public class lavaShotProjectile extends Projectile {

	private Timeline tl;
	private AudioClip lavaSound = new AudioClip(new File("src/Sounds/lavasiz.wav").toURI().toString());

	
	public lavaShotProjectile(String imageName, int speed, int x, int y, Enemy EtoShoot, int damage) {
		super(imageName, speed, x, y, EtoShoot, damage);
		update();

	}

	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();

	}
	


	private class AnimationHandler implements EventHandler<ActionEvent> {

		int count = 0;

		@Override
		public void handle(ActionEvent arg0) {
			if (count > 6) {
				tl.stop();
			}
			// System.out.println(EtoShoot);
			double length = Math.sqrt(
					(EtoShoot.getX() - x) * (EtoShoot.getX() - x) + (EtoShoot.getY() - y) * (EtoShoot.getY() - y));
			x = x + ((EtoShoot.getX() - x) / length * speed);

			y = y + ((EtoShoot.getY() - y) / length * speed);

			if (handleCol()) {
				lavaSound.play();
				TowerDamage(EtoShoot);
				tl.stop();
			}
			
			draw();
			count++;
		}

	}
}
