package model;

import controller.Drawer;
import javafx.scene.image.Image;

/**
 * This class represents a tile in the Tile Map
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class Tile {

	private int x, y, width, height;
	private Image img;
	private TileType type;

	/**
	 * Constructor
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int x, int y, int width, int height, TileType type
	 * @return n/a
	 * @throws n/a
	 */
	public Tile(int x, int y, int width, int height, TileType type) {
		this.img = type.img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	/**
	 * Draw mehtod that calss the drawer class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void Draw() {
		Drawer.DrawImage(img, x, y, width, height);
	}
	//Getter
	public int getX() {
		return x;
	}
	//Getter
	public int getY() {
		return y;
	}
	//Getter
	public int getWidth() {
		return width;
	}
	//Getter
	public int getHeight() {
		return height;
	}
	//Getter
	public Image getImg() {
		return img;
	}
	//Setter
	public void setX(int x) {
		this.x = x;
	}
	//Setter
	public void setY(int y) {
		this.y = y;
	}
	//Getter
	public void setWidth(int width) {
		this.width = width;
	}
	//Setter
	public void setHeight(int height) {
		this.height = height;
	}
	//Setter
	public void setImg(Image img) {
		this.img = img;
	}
	//Getter
	public TileType getType() {
		return type;
	}
	//Setter
	public void setType(TileType type) {
		this.type = type;
	}
}
