package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import view.TowerDefenseView;

public class Drawer {
	private static Timeline circleTL;

	public static void DrawImage(Image img, int x, int y, double d, double e) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y, d, e);
	}
	

	public static void DrawImage2(Image img, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
	}

	public static void DrawCircle(int r, int x, int y) {
		circleTL = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler(r, x, y)));
		circleTL.setCycleCount(1);
		circleTL.play();

	}

	public static void RotateImage(Image img, float angle, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().rotate(angle);
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
		TowerDefenseView.canvas.getGraphicsContext2D().restore();
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
			TowerDefenseView.canvas.getGraphicsContext2D().strokeOval(x - ((r - 32) / 2), y - ((r - 32) / 2), r, r);
		}

	}

	public static void DrawImageDouble(Image amunition, double x, double y, double d, double e) {
		// TODO Auto-generated method stub
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(amunition, x, y, d, e);

	}

}
