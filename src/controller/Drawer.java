package controller;

import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import view.TowerDefenseView;

public class Drawer {

	public static void DrawImage(Image img, int x, int y, int width, int height) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y, width, height);
	}
	
	public static void DrawImage2(Image img, int x, int y) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y);
	}
	
	public static void DrawCircle(Circle c, int x, int y) {
		float newX = (float) x;
		float newY = (float) y;
		float newW = (float) c.getRadius();
		float newH = (float) c.getRadius();	
	}
}
