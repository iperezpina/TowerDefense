package model;

import javafx.scene.image.Image;

public enum TileType {

	Grass("lava", true), Dirt("road", false), Water("lava", false), DEBUG("water32", false);
	
	Image img;
	boolean canPlace;
	
	TileType(String imgName, boolean canPlace) {
		img = new Image("Images/" + imgName +".png");
		this.canPlace = canPlace;
	}
	
	
}
