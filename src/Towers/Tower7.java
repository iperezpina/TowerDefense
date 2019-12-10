package Towers;

import Projectile.Projectile;
import Projectile.arrowProjectile;
import Projectile.fireProjectile;
import controller.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Enemy;
import model.EnemyLocator;
import model.GameState;
import model.TimerAll;
import model.Upgrade;

public class Tower7 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;

	public Tower7(String imgName, int x, int y, int width, int height) {
		super(imgName, x, y, width, height);
		additionalInfo();
	}

	public void additionalInfo() {
		this.attackRate = 3;
		this.towerCost = 800;
		this.projSpeed = 7;
		this.damage = 2;
		this.range = 500;
		this.towerName = "Arrow Tower";
		this.towerSpent=0;
		this.upgradeCost= 160;
		CreateUpgradeInfo();
	}
	
	public void CreateUpgradeInfo() {
		// Upgrade 1
		Upgrade up1 = new Upgrade("Aerodynamic Arrows", "Arrows fly faster towards ghosts.", 100);
		towerUpgrades[0] = up1;

		// Upgrade2
		Upgrade up2 = new Upgrade("Sharper Arrows", "Arrows deal more damage", 200);
		towerUpgrades[1] = up2;

		// Upgrade3
		Upgrade up3 = new Upgrade("Double Arrow Shot", "Shoots 2 arrows at a time", 300);
		towerUpgrades[2] = up3;

		// Upgrade4
		Upgrade up4 = new Upgrade("Triple shot", "One more arrow can't hurt, right?", 500);
		towerUpgrades[3] = up4;

	}

	public void upgrade1() {
		System.out.println("you upgraded 1");
		upgradeLevel += 1;
	}

	public void upgrade2() {
		System.out.println("you upgraded 2");
		upgradeLevel += 1;
	}

	public void upgrade3() {
		System.out.println("you upgraded 3");
		upgradeLevel += 1;
	}

	public void upgrade4() {
		System.out.println("you upgraded 4");
		upgradeLevel += 1;
	}
	
	public void setURL(String str) {
		url = str;
	}

	public String getURL() {
		return url;
	}

	public void shoot() {
		if (Player.getGameState().equals(GameState.gamex2))
			ammo = new arrowProjectile("arrow", projSpeed * 2, x, y, currEnemy, damage);
		else
			ammo = new arrowProjectile("arrow", projSpeed, x, y, currEnemy, damage);
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
			if (lastTimeAttacked > currentTime) {
				lastTimeAttacked = 0;
			}
			if (Math.abs(currentTime - lastTimeAttacked) >= attackRate) {
				lastTimeAttacked = currentTime;
				drawRange();
			}
		}

	}


	private Enemy currEnemy = null;

	private int lockMech = 1;

	public void drawRange() {

		for (Enemy e : EnemyLocator.getEnemies()) {
			if (e != null) {
				int x2 = e.getX();
				int y2 = e.getY();
				double distance = Math.hypot(getX() - x2, getY() - y2);
				if (distance < range && !e.isDead() & lockMech == 0) {
					lockMech += 1;
					currEnemy = e;
					shoot();
				}
			}
			
		}

		lockMech = 0;
	}

	public Enemy getcurrEnemy() {
		return currEnemy;
	}
}
