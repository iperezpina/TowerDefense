package model;

import controller.Drawer;
import javafx.scene.image.Image;

public class Tile {

	private int x, y, width, height;
	private Image img;
	private TileType type;
	
	public Tile(int x, int y, int width, int height, TileType type) {
		this.img = type.img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
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

	public Image getImg() {
		return img;
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

	public void setImg(Image img) {
		this.img = img;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
}
