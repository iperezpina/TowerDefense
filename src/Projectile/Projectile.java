package Projectile;

import controller.Drawer;
import controller.ResourceManager;
import javafx.animation.Animation;
import javafx.animation.AnimationBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;



public class Projectile {

	private Image Amunition;
	int speed;
	int x;
	private int y;
	private Timeline tl;

	
	
	public Projectile( String imageName, int speed, int x, int y) {
		System.out.println(imageName);
		if(this.Amunition == null) {
			this.Amunition= ResourceManager.QuickLoad(imageName);
			System.out.println(this.Amunition);
					
		}
		this.x= x;
		this.y= y;
		this.speed= speed;
		//update();
		
	}
	
	
	public void draw() {
		Drawer.DrawImage(Amunition, x, y, Amunition.getWidth(), Amunition.getHeight());
		
	}
	
	
	
//	public void update() {
//		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
//		tl.setCycleCount(Animation.INDEFINITE);
//		tl.play();
//
//	}
//	
//	
//	private class AnimationHandler implements EventHandler<ActionEvent> {
//
//		int count = 0;
//		@Override
//		public void handle(ActionEvent arg0) {
//			if(count>20) {
//				tl.stop();
//			}
//				x= x+speed;
//				draw();
//				count++;
//		}
//
//	}
	
	
}
