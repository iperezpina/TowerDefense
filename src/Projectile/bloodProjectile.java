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

public class bloodProjectile extends Projectile {

	private Timeline tl;
	private AudioClip bloodSound = new AudioClip(new File("src/Sounds/splat.wav").toURI().toString());
	private boolean canSlow, canPoison;

	public bloodProjectile(String imageName, int speed, int x, int y, Enemy EtoShoot, int damage, boolean canSlow, boolean canPoison) {
		super(imageName, speed, x, y, EtoShoot, damage);
		this.canSlow = canSlow;
		this.canPoison = canPoison;
		update();

	}

	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
		bloodSound.setVolume(.5f);

	}

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
				TowerDamage(EtoShoot);
				if(canSlow) {
					EtoShoot.slowEnemy();	
					
				}
				if(canPoison) {
					EtoShoot.poisonEnemy();
				}
				
				tl.stop();
			}
			
			
			draw();
			count++;
		}

	}
}
