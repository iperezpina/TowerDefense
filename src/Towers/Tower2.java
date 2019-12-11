package Towers;

import Projectile.Projectile;
import Projectile.boneProjectile;
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

public class Tower2 extends Tower {

	private String url;
	private Projectile ammo, ammo2;
	private Timeline tl;
	private boolean doubleShoot = false;

	public Tower2(String imgName, int x, int y, int width, int height) {
		super(imgName, x, y, width, height);
		additionalInfo();
	}

	public void additionalInfo() {
		this.attackRate = 5;
		this.towerCost = 350;
		this.projSpeed = 4;
		this.damage = 3;
		this.range = 200;
		this.towerName = "Bone Tower";
		this.towerSpent=0;
		this.upgradeCost= 110;
		CreateUpgradeInfo();
	}
	
	public void CreateUpgradeInfo() {
		// Upgrade 1
		Upgrade up1 = new Upgrade("Better Bones", "Bones pack more of a punch", 175);
		towerUpgrades[0] = up1;

		// Upgrade2
		Upgrade up2 = new Upgrade("Faster Bones", "Speed of bones are much faster", 215);
		towerUpgrades[1] = up2;

		// Upgrade3
		Upgrade up3 = new Upgrade("Bone Zone", "Range of tower increases", 300);
		towerUpgrades[2] = up3;

		// Upgrade4
		Upgrade up4 = new Upgrade("Spooky Scary Skeletons", "Spawns two bones with each shot", 500);
		towerUpgrades[3] = up4;

	}
	
	public void upgrade1() {
		int upgradeCost = towerUpgrades[0].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			damage += 3;
			upgradeLevel += 1;
			towerSpent += upgradeCost;
		}
	}

	public void upgrade2() {
		int upgradeCost = towerUpgrades[1].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			projSpeed *= 1.5;
			upgradeLevel += 1;
			towerSpent += upgradeCost;

		}
	}

	public void upgrade3() {
		int upgradeCost = towerUpgrades[2].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			range *= 2;
			upgradeLevel += 1;
			towerSpent += upgradeCost;

		}
	}

	public void upgrade4() {
		int upgradeCost = towerUpgrades[3].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			doubleShoot = true;
			upgradeLevel += 1;
			towerSpent += upgradeCost;

		}
	}

	public void setURL(String str) {
		url = str;
	}

	public String getURL() {
		return url;
	}

	public void shoot() {
		if (doubleShoot) {
			if (Player.getGameState().equals(GameState.gamex2)) {
				ammo = new boneProjectile("Bone", projSpeed * 2, x, y, currEnemy, damage);
				ammo2 = new boneProjectile("Bone", projSpeed * 2, x+32, y, currEnemy, damage);	
			}
			else {
				ammo = new boneProjectile("Bone", projSpeed, x, y, currEnemy, damage);
				ammo2 = new boneProjectile("Bone", projSpeed, x + 32, y, currEnemy, damage);
			}
		}
		else {
			if (Player.getGameState().equals(GameState.gamex2))
				ammo = new boneProjectile("Bone", projSpeed * 2, x, y, currEnemy, damage);
			else
				ammo = new boneProjectile("Bone", projSpeed, x, y, currEnemy, damage);
		}
		
		
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
			if (!isActive) {
				tl.stop();
				return;
			}
			
			
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
