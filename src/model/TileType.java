
package model;

import javafx.scene.image.Image;

public enum TileType {

	Grass("lava", true), Dirt("road", false), Water("lava", false), DEBUG("lava", false);
	Image img;
	boolean canPlace;
	
	TileType(String imgName, boolean canPlace) {
		img = new Image("Images/" + imgName +".png");
		this.canPlace = canPlace;
	}

	public boolean isCanPlace() {
		return canPlace;
	}

	public void setCanPlace(boolean canPlace) {
		this.canPlace = canPlace;
	}
	
	
}

