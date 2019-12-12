package Towers;

import Projectile.Projectile;
import Projectile.boneProjectile;
import Projectile.lazerProjectile;
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

/**
 * Tower3 class that represents third Tower
 * 
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class Tower3 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;

	/**
	 * The basic constructor for this class, takes in an img, x and y position,
	 * width and height.
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imgName, int x, int y, int width, int height
	 * @return n/a
	 * @throws n/a
	 */
	public Tower3(String imgName, int x, int y, int width, int height) {
		super(imgName, x, y, width, height);
		additionalInfo();
	}

	/**
	 * The other constructor for this class, the stats for this tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void additionalInfo() {
		this.attackRate = 1;
		this.towerCost = 400;
		this.projSpeed = 10;
		this.damage = 1;
		this.range = 100;
		this.towerName = "Pyramid Tower";
		this.towerSpent = 0;
		this.upgradeCost = 120;
		CreateUpgradeInfo();
	}

	/**
	 * The upgrades that are listed for this tower ! WOW factor
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void CreateUpgradeInfo() {
		// Upgrade 1
		Upgrade up1 = new Upgrade("Damage Up", "Damage of lazers increased by 1", 150);
		towerUpgrades[0] = up1;

		// Upgrade2
		Upgrade up2 = new Upgrade("SuperSonic Lasers", "Lazers go twice as fast", 200);
		towerUpgrades[1] = up2;

		// Upgrade3
		Upgrade up3 = new Upgrade("Eye of Anubis", "The Almight Eye sees all, allowing it to hit more ghosts", 300);
		towerUpgrades[2] = up3;

		// Upgrade4
		Upgrade up4 = new Upgrade("Deadly Lazers", "Lazers do 4x regular damage", 800);
		towerUpgrades[3] = up4;

	}

	/**
	 * The first Upgrade for this Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void upgrade1() {
		int upgradeCost = towerUpgrades[0].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			damage += 1;
			upgradeLevel += 1;
			towerSpent += upgradeCost;
		}
	}

	/**
	 * The second Upgrade for this Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void upgrade2() {
		int upgradeCost = towerUpgrades[1].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			projSpeed *= 2;
			upgradeLevel += 1;
			towerSpent += upgradeCost;

		}
	}

	/**
	 * The third Upgrade for this Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void upgrade3() {
		int upgradeCost = towerUpgrades[2].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			range += 150;
			upgradeLevel += 1;
			towerSpent += upgradeCost;
		}
	}

	/**
	 * The foruth Upgrade for this Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void upgrade4() {
		int upgradeCost = towerUpgrades[3].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			damage = 4;
			upgradeLevel += 1;
			towerSpent += upgradeCost;
		}
	}

	/**
	 * This function sets the url
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void setURL(String str) {
		url = str;
	}

	// Getter
	public String getURL() {
		return url;
	}

	/**
	 * This is the shoot method that shoots out a porjectile class
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void shoot() {
		if (Player.getGameState().equals(GameState.gamex2))
			ammo = new lazerProjectile("lazer", projSpeed * 2, x, y, currEnemy, damage, this);
		else
			ammo = new lazerProjectile("lazer", projSpeed, x, y, currEnemy, damage, this);

	}

	/**
	 * Update method in charge of any movement (rotation) of the tower and drawing
	 * of the tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(500), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}

	/**
	 * A basic animation handler that just draws the tower at the moment *
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent arg0
	 * @return n/a
	 * @throws n/a
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

	/**
	 * This is the Range method for the Tower also the lock mechanism
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
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

	// Getter
	public Enemy getcurrEnemy() {
		return currEnemy;
	}
}
