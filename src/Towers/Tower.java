package Towers;

import controller.Drawer;
import controller.ResourceManager;
import javafx.scene.image.Image;
import model.Enemy;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class Tower {

	// Variables for this class
	protected Image img;
	protected int x;
	protected int y;
	private int width;
	private int height;

	protected int range = 100;
	protected int currentTime = 0;
	protected int lastTimeAttacked = 0;
	protected int attackRate = 3;
	protected int towerCost = 0, towerSpent = 0;
	protected int upgradeCost=0;
	protected int damage = 0;
	protected int upgrade;
	protected int sellCost=0;
	//protected boolean upgradeCount= false;
	private int enemiesDestroyed = 0;
	protected String towerName = "";
	protected boolean isSelected = false;

	/**
	 * The basic constructor for this class, takes in an img, x and y position,
	 * width and height.
	 * 
	 * @param img
	 * @param x
	 * @param y
	 * @param width
	 * @param height
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
	 * Draws the tower image
	 */
	public void Draw() {
		Drawer.DrawImage(img, x, y, width, height);
		if (isSelected)
			showRange();
	}
	
	public void showRange() {
		Drawer.DrawCircle(range, x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setIsSelected(boolean value) {
		this.isSelected = value;
	}
	
	public boolean isSelected() {
		return this.isSelected;
	}


	public void setTowerCost(int towerCost) {
		this.towerCost = towerCost;
	}

	
	public void setSellCost() {
		this.sellCost= (int) ((this.towerCost +this.towerSpent)*.75);
	}
	
	public int getSellCost() {
		return this.sellCost;
	}
	
	
	public int getUpgradeCost() {
		return this.upgradeCost;
	}
	
	
	
	
	public int getTowerCost() {
		return towerCost;
	}
	
	public int getEnemiesDestroyed() {
		return this.enemiesDestroyed;
	}
	
	public String getTowerName() {
		return this.towerName;
	}
	
	public int getRange() {
		return this.range;
	}

	public int getUpgrade() {
		return this.upgrade;
	}
}
