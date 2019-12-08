package Towers;

import controller.Drawer;
import javafx.scene.image.Image;

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
	private Image img;
	protected int x;
	protected int y;
	private int width;
	private int height;

	protected int range = 100;
	protected int currentTime = 0;
	protected int lastTimeAttacked = 0;
	protected int attackRate = 3;
	protected int towerCost = 0, towerSpent = 0;
	protected int damage = 0;
	private int towersDestroyed = 0;
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
	public Tower(Image img, int x, int y, int width, int height) {
		this.img = img;
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

	public int getTowerCost() {
		return towerCost;
	}

	public void setTowerCost(int towerCost) {
		this.towerCost = towerCost;
	}
	
	

}
