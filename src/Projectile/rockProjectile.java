package Projectile;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class rockProjectile extends Projectile {

	private Timeline tl;
	
	public rockProjectile(String imageName, int speed, int x, int y) {
		super(imageName, speed, x, y);
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
			if(count>20) {
				tl.stop();
			}
				x= x+speed;
				draw();
				count++;
		}

	}
}
