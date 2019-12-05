package model;

import controller.ResourceManager;
import javafx.scene.image.Image;

public enum TileType {

	Grass("grass32", true), Dirt("dirt32", false), Water("water32", false), DEBUG("water32", false);
	
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
