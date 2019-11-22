package controller;

import javafx.scene.image.Image;
import view.TowerDefenseView;

public class Drawer {

	public static void DrawImage(Image img, int x, int y, int width, int height) {
		TowerDefenseView.canvas.getGraphicsContext2D().drawImage(img, x, y, width, height);
	}
}
