package Towers;

import controller.Drawer;
import controller.ResourceManager;
import javafx.scene.image.Image;
import model.Enemy;
import model.Upgrade;

/**
 * Tower super class for all the Tower
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class Tower {

	// Variables for this class
	protected Image img;
	protected int x;
	protected int y;
	private int width;
	private int height;

	protected int range = 100, projSpeed = 0;
	protected int currentTime = 0;
	protected int lastTimeAttacked = 0;
	protected int attackRate = 3;
	protected int towerCost = 0, towerSpent = 0;
	protected int upgradeCost = 0;
	protected int damage = 0;
	protected int sellCost = 0;
	private int enemiesDestroyed = 0;
	protected String towerName = "";
	protected boolean isSelected = false;
	protected int upgradeLevel = 0;
	protected boolean isActive = true;

	protected Upgrade[] towerUpgrades = new Upgrade[4];

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
	public Tower(String imgName, int x, int y, int width, int height) {
		if (this.img == null) {
			this.img = ResourceManager.getTowerImg(imgName);
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	/**
	 * Method that gets the Sell cost of the Tower
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public int sellCost() {
		return (int) ((this.towerCost * this.towerSpent) * 75);
	}

	// Getter
	public boolean isActive() {
		return isActive;
	}

	/**
	 * a Switch clss that checks if a tower has been sold
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imgName, int x, int y, int width, int height
	 * @return n/a
	 * @throws n/a
	 */
	public void changeActive() {
		isActive = !isActive;
	}

	/**
	 * Draws the tower image
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void Draw() {
		Drawer.DrawImage(img, x, y, width, height);
		if (isSelected)
			showRange();
	}

	/**
	 * debug method that shows the range of the cirlce
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void showRange() {
		Drawer.DrawCircle(range, x, y);
	}

	// Getter
	public int getX() {
		return x;
	}

	// Getter
	public int getY() {
		return y;
	}

	// Setter
	public void setX(int x) {
		this.x = x;
	}

	// Setter
	public void setY(int y) {
		this.y = y;
	}

	// Setter
	public void setIsSelected(boolean value) {
		this.isSelected = value;
	}

	// Getter
	public boolean isSelected() {
		return this.isSelected;
	}

	// Setter
	public void setTowerCost(int towerCost) {
		this.towerCost = towerCost;
	}

	// Setter
	public void setSellCost() {
		this.sellCost = (int) ((this.towerCost + this.towerSpent) * .75);
	}

	// Getter
	public int getSellCost() {
		return this.sellCost;
	}

	// Getter
	public int getUpgradeCost() {
		return this.upgradeCost;
	}

	// Getter
	public int getTowerCost() {
		return towerCost;
	}

	// Getter
	public int getEnemiesDestroyed() {
		return this.enemiesDestroyed;
	}

	// Getter
	public String getTowerName() {
		return this.towerName;
	}

	// Getter
	public int getRange() {
		return this.range;
	}

	// Getter
	public int getUpgradeLevel() {
		return upgradeLevel;
	}

	// Getter
	public Upgrade[] getTowerUpgrades() {
		return towerUpgrades;
	}

	// Setter
	public void setUpgradeLevel(int upgradeLevel) {
		this.upgradeLevel = upgradeLevel;
	}

	// Setter
	public void setTowerUpgrades(Upgrade[] towerUpgrades) {
		this.towerUpgrades = towerUpgrades;
	}

	// SetterSf
	public void setKillCount() {
		this.enemiesDestroyed++;
	}
}
