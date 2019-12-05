package Towers;

import java.util.concurrent.TimeUnit;

import controller.Drawer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Enemy;
import model.EnemyLocator;
import model.TimerAll;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class Tower {

	// Variables for this class
	private Image img;
	private int x, y, width, height;
	private Timeline tl;
	private int range = 100;
	
	private int lastTimeAttacked = 0;
	private int currentTime = 0;
	private int attackRate = 3;

	/**
	 * The basic constructor for this class, takes in an img, x and y position,
	 * width and height.
	 * 
	 * @param img
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Tower(Image img, int x, int y, int width, int height) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//update();
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
			//if (timer.getCheck()) {
			//	drawRange();
			//}
			
		}

	}
	
	//private TimerAll timer = new TimerAll();
	
	public void drawRange() {
		
		
		for(Enemy e: EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX()-x2, getY()-y2);
			
			if (distance < range) {
				//minus hp
				
				System.out.println("enemy entered range");
				
			}
			
			
		}
	}
	
	

	/**
	 * Draws the tower image
	 */
	public void Draw() {
		Drawer.DrawImage(img, x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
