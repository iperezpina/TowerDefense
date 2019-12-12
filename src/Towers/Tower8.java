package Towers;

import Projectile.Projectile;
import Projectile.fireProjectile;
import Projectile.lavaShotProjectile;
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
 * Tower8 class that represents eigth Tower
 * 
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class Tower8 extends Tower {

	private String url;
	private Projectile ammo;
	private Timeline tl;
	private boolean canAltDamage = false;
	private int altAttackRate = 2, altCurrentTime = 0, altLastTimeAttacked = 0;

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
	public Tower8(String imgName, int x, int y, int width, int height) {
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
		this.attackRate = 10;
		this.towerCost = 1000;
		this.projSpeed = 10;
		this.damage = 25;
		this.range = 150;
		this.towerName = "Volcano Tower";
		this.towerSpent = 0;
		this.upgradeCost = 170;
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
		Upgrade up1 = new Upgrade("Faster Eruption Time", "Increases Volcano's attack speed", 1);
		towerUpgrades[0] = up1;

		// Upgrade2
		Upgrade up2 = new Upgrade("Deadly Magma", "The magma projectiles deal x2 more damage", 2);
		towerUpgrades[1] = up2;

		// Upgrade3
		Upgrade up3 = new Upgrade("Increased range", "Increases range of the Volcano", 3);
		towerUpgrades[2] = up3;

		// Upgrade4
		Upgrade up4 = new Upgrade("Firey Aura", "Ghost near the tower will take damage", 5);
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
			attackRate -= 2;
			projSpeed += 2;
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
			damage *= 2;
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
			range += 50;
			upgradeLevel += 1;
			towerSpent += upgradeCost;

		}
	}

	/**
	 * The fourth Upgrade for this Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void upgrade4() {
		int upgradeCost = towerUpgrades[0].getUpgradeCost();
		if (Player.getCurrentCash() >= upgradeCost) {
			Player.decreaseCoins(upgradeCost);
			canAltDamage = true;
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
			ammo = new lavaShotProjectile("lavaShot", projSpeed * 2, x, y, currEnemy, damage, this);
		else
			ammo = new lavaShotProjectile("lavaShot", projSpeed, x, y, currEnemy, damage, this);
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

			if (canAltDamage) {
				altCurrentTime = TimerAll.getTimeInSeconds();
				if (altLastTimeAttacked > altCurrentTime) {
					altLastTimeAttacked = 0;
				}
				if (Math.abs(altCurrentTime - altLastTimeAttacked) >= altAttackRate) {
					altLastTimeAttacked = altCurrentTime;
					altAttack();
				}
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

		// Checks if it can do regular attack
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

	/**
	 * This is an alternate attack pattern when this tower is ugradeds
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void altAttack() {
		// Checks if it can do alt attack, deals damage to enemies near it.
		// (1 dmg every 2 seconds)
		for (Enemy e : EnemyLocator.getEnemies()) {
			if (e != null) {
				int x2 = e.getX();
				int y2 = e.getY();
				double distance = Math.hypot(getX() - x2, getY() - y2);

				if (distance + 30 < range && !e.isDead()) {
					e.setHealth(e.getHealth() - 1);
				}

			}

		}
	}

	// Getter
	public Enemy getcurrEnemy() {
		return currEnemy;
	}
}
