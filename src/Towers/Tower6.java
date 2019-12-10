package Towers;

import Projectile.Projectile;
import Projectile.bloodProjectile;
import Projectile.rockProjectile;
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

public class Tower6 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;
	private boolean canSlow = false, canPoison = false;

	public Tower6(String imgName, int x, int y, int width, int height) {
		super(imgName, x, y, width, height);
		additionalInfo();
	}

	public void additionalInfo() {
		this.attackRate = 6;
		this.towerCost = 666;
		this.projSpeed = 4;
		this.damage = 1;
		this.range = 250;
		this.towerName = "Blood Tower";
		this.towerSpent=0;
		this.upgradeCost= 150;
		CreateUpgradeInfo();
	}

	public void CreateUpgradeInfo() {
		// Upgrade 1
		Upgrade up1 = new Upgrade("Bloody Clot", "Slows down ghosts", 100);
		towerUpgrades[0] = up1;

		// Upgrade2
		Upgrade up2 = new Upgrade("Powerful Blood", "Blood deals more damage", 200);
		towerUpgrades[1] = up2;

		// Upgrade3
		Upgrade up3 = new Upgrade("Blood Geyser", "Spews out more blood faster but deals less damage", 3);
		towerUpgrades[2] = up3;

		// Upgrade4
		Upgrade up4 = new Upgrade("Corrupted Blood", "Ghosts take poison damage for 5 seconds", 5);
		towerUpgrades[3] = up4;

	}
	
	public void upgrade1() {
		int upgradeCost = towerUpgrades[0].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			canSlow = true;
			upgradeLevel += 1;

		}
	}

	public void upgrade2() {
		int upgradeCost = towerUpgrades[1].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			canPoison = true;
			upgradeLevel += 1;

		}
	}

	public void upgrade3() {
		int upgradeCost = towerUpgrades[2].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			damage = 1;
			attackRate = 2;
			projSpeed = 8;
			upgradeLevel += 1;

		}
	}

	public void upgrade4() {
		int upgradeCost = towerUpgrades[3].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			canPoison = true;
			upgradeLevel += 1;

		}
	}
	
	public void setURL(String str) {
		url = str;
	}

	public String getURL() {
		return url;
	}

	public void shoot() {
		//TODO: Add canSlow into constructor for bloodProjectile and apply a slow method in enemy class
		//TODO: Add canPoison into constructor for bloodProjectile and apply a tick damage effect every second for a given duration (5)
		if (Player.getGameState().equals(GameState.gamex2))
			ammo = new bloodProjectile("blood", projSpeed * 2, x, y, currEnemy, damage, canSlow, canPoison);
		else
			ammo = new bloodProjectile("blood", projSpeed, x, y, currEnemy, damage, canSlow, canPoison);
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
