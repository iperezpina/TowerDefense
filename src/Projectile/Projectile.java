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
import model.Enemy;



public class Projectile {

	Image Amunition;
	int speed;
	double x;
	double y;
	Enemy EtoShoot;
	private Timeline tl;
	
	
	
	public Projectile( String imageName, int speed, int x, int y, Enemy EtoShoot) {
		if(this.Amunition == null) {
			this.Amunition= ResourceManager.QuickLoad(imageName);
		}
		this.x= x;
		this.y= y;
		this.speed= speed;
		this.EtoShoot = EtoShoot;
		//update();
		
	}
	
	
	public void draw() {
		
		Drawer.DrawImageDouble(Amunition, x, y, Amunition.getWidth(), Amunition.getHeight());
		
	}
	
	

	
	
}
