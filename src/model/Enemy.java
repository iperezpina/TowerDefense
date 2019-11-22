package model;

import controller.Drawer;
import controller.Clock;
import javafx.scene.image.Image;

public class Enemy {

	private int x, y, width, height, health;
	private float speed;
	private Image img;
	private Tile startLocation;
	private boolean first = true;
	
	public Enemy(Image img, Tile start, int width, int height, float speed) {
		this.img = img;
		this.x = start.getX();
		this.y = start.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		
	}
	
	public void update() {
		if (first)
			first = false;
		else {
			x += Clock.Delta() * speed;
			System.out.println("Moving by: " + x);
		}
	}
	
	public void Draw() {
		Drawer.DrawImage(img, x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getHealth() {
		return health;
	}

	public float getSpeed() {
		return speed;
	}

	public Image getImg() {
		return img;
	}

	public Tile getStartLocation() {
		return startLocation;
	}

	public boolean isFirst() {
		return first;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setStartLocation(Tile startLocation) {
		this.startLocation = startLocation;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
}
