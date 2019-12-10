
package model;

import controller.ResourceManager;
import javafx.scene.image.Image;

public enum TileType {

	Grass("lava", true), Dirt("road", false), Lava1("lava", false), Lava2("lava2", false),
	Lava3("lava3", false), HellGround1("hellground1", true), HellGround2("hellground2", true), 
	HellGround3("hellground3", true), DEBUG("lava", false);
	Image img;
	boolean canPlace;
	
	TileType(String imgName, boolean canPlace) {
		img = ResourceManager.QuickLoad(imgName);
		this.canPlace = canPlace;
	}

	public boolean isCanPlace() {
		return canPlace;
	}

	public void setCanPlace(boolean canPlace) {
		this.canPlace = canPlace;
	}
	
	
}

