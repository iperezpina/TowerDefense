package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import view.TowerDefenseView;

public class Drawer {
	private static Timeline circleTL;

	public static void DrawImage(Image img, int x, int y, int width, int height) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y, width, height);
	}
	
	public static void DrawImage2(Image img, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
	}
	
	public static void DrawCircle(int r, int x, int y) {
		circleTL = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler(r, x, y)));
		circleTL.setCycleCount(5);
		circleTL.play();
		
	}
	
	private static class AnimationHandler implements EventHandler<ActionEvent> {

		int x, y, r;
		public AnimationHandler(int r, int x, int y) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		@Override
		public void handle(ActionEvent arg0) {
			TowerDefenseView.canvas.getGraphicsContext2D().strokeOval(x-32, y-32, r, r);
		}

	}
	
	
}

