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

/**
 * This class is a Super class for all the projectiles
 * 
 * @author Ivan, Marisa, Laura, Albert
 *
 */
public class Projectile {

	Image Amunition;
	int speed;
	double x;
	double y;
	Enemy EtoShoot;
	private Timeline tl;
	int damage;

	/**
	 * Contructor for superClass
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imgName, int speed, int x, int y, Enemy EtoShoot, int damage
	 * @return n/a
	 * @throws n/a
	 */
	public Projectile(String imgName, int speed, int x, int y, Enemy EtoShoot, int damage) {
		if (this.Amunition == null) {
			this.Amunition = ResourceManager.getProjectileImg(imgName);
		}
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.EtoShoot = EtoShoot;
		this.damage = damage;

	}

	/**
	 * Tower damage that takes a enmy and damages
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public void TowerDamage(Enemy e) {
		e.setHealth(e.getHealth() - damage);
	}

	/**
	 * Collision chekcer that checks to see if a projectile collides with a enemy
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param boolean true or false
	 * @return n/a
	 * @throws n/a
	 */
	public boolean handleCol() {
		double ProjectileX1 = x;
		double ProjectileX2 = x + 32;

		double ProjectileY1 = y;
		double ProjectileY2 = y + 32;

		double EnemyPosX1 = EtoShoot.getX();
		double EnemyPosX2 = EnemyPosX1 + 32;

		double EnemyPosY1 = EtoShoot.getY();
		double EnemyPosY2 = EnemyPosY1 + 32;

		boolean cond1 = (EnemyPosX1 >= ProjectileX1) && (EnemyPosX1 < ProjectileX2);
		boolean cond2 = (ProjectileX1 >= EnemyPosX1) && (ProjectileX1 < EnemyPosX2);

		boolean cond3 = (EnemyPosY1 >= ProjectileY1) && (EnemyPosY1 < ProjectileY2);
		boolean cond4 = (ProjectileY1 >= EnemyPosY1) && (ProjectileY1 < EnemyPosY2);

		if (cond1 || cond2) {
			if (cond3 || cond4) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Another draw method that draws on the gui
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Enemy e
	 * @return n/a
	 * @throws n/a
	 */
	public void draw() {
		Drawer.DrawImageDouble(Amunition, x, y, Amunition.getWidth(), Amunition.getHeight());

	}

}
