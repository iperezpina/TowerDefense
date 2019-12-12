package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import view.TowerDefenseView;

/**
 * Drawer class that draws the GUI
 * 
 * @author Ivan, Marisa, Laura, Albert
 */
public class Drawer {
	private static Timeline circleTL;

	/**
	 * This function serves as all purpose function that draws on the GUI the image,
	 * the location, and the postion
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image img, int x, int y, double d, double e
	 * @return n/a
	 * @throws n/a
	 */
	public static void DrawImage(Image img, int x, int y, double d, double e) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y, d, e);
	}

	/**
	 * This function serves as all purpose function that draws on the GUI the image,
	 * the location.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image img, int x, int y
	 * @return n/a
	 * @throws n/a
	 */
	public static void DrawImage2(Image img, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
	}

	/**
	 * This function serves as all purpose function that draws a circle with a
	 * radius and a postion
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int r, int x, int y
	 * @return n/a
	 * @throws n/a
	 */
	public static void DrawCircle(int r, int x, int y) {
		circleTL = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler(r, x, y)));
		circleTL.setCycleCount(1);
		circleTL.play();

	}

	/**
	 * This function Rotates the image, and acts as a re-drawer that changes it
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image img, float angle, int x, int y
	 * @return n/a
	 * @throws n/a
	 */
	public static void RotateImage(Image img, float angle, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().rotate(angle);
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
		TowerDefenseView.canvas.getGraphicsContext2D().restore();
	}

	/**
	 * This function serves as an Animation Handler that controls the drawing an
	 * image on screen
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int r, int x, int y
	 * @return n/a
	 * @throws n/a
	 */
	private static class AnimationHandler implements EventHandler<ActionEvent> {

		int x, y, r;

		/**
		 * Constructor for this class
		 * 
		 * @author Ivan, Marisa, Laura, Albert
		 * @param int r, int x, int y
		 * @return n/a
		 * @throws n/a
		 */
		public AnimationHandler(int r, int x, int y) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		/**
		 * This function draws when the event is called upon, it draws the oval at the
		 * location
		 * 
		 * @author Ivan, Marisa, Laura, Albert
		 * @param Action Event ar0
		 * @return n/a
		 * @throws n/a
		 */
		@Override
		public void handle(ActionEvent arg0) {
			TowerDefenseView.canvas.getGraphicsContext2D().strokeOval(x - ((r - 32) / 2), y - ((r - 32) / 2), r, r);
		}

	}

	/**
	 * This function serves as all purpose function that draws on the GUI the image,
	 * the location, and the postion, but is different as it takes in a double
	 * instead of a int
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image ammunition, double x, double y, double d, double e
	 * @return n/a
	 * @throws n/a
	 */
	public static void DrawImageDouble(Image amunition, double x, double y, double d, double e) {
		// TODO Auto-generated method stub
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(amunition, x, y, d, e);

	}

}
