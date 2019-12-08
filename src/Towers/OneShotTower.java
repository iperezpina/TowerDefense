package Towers;


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

public class OneShotTower extends Tower {

	public OneShotTower(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	private final int range = 100;
	private int lastTimeAttacked = 0;
	private int currentTime = 0;
	private int attackRate = 3;
	private Timeline tl;
	
	
	

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
	
	public void TowerDamage(Enemy e) {
		
		e.setHealth(e.getHealth() - 10000000);
	}
	//private TimerAll timer = new TimerAll();
	
	public void drawRange() {
		
		
		for(Enemy e: EnemyLocator.getEnemies()) {
			int x2 = e.getX();
			int y2 = e.getY();
			double distance = Math.hypot(getX()-x2, getY()-y2);
			if (distance < range) {
				//destroy everything in immidieate path
				TowerDamage(e);
				System.out.println("enemy entered range");
				
			}
			
			
		}
	}
	
	

}
