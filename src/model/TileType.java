
package model;

import controller.ResourceManager;
import javafx.scene.image.Image;

/**
 * TileType enum class
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public enum TileType {

	Grass("lava", true), Dirt("road", false), Lava1("lava", false), Lava2("lava2", false), Lava3("lava3", false),
	HellGround1("hellground1", true), HellGround2("hellground2", true), HellGround3("hellground3", true),
	DEBUG("lava", false);
	Image img;
	boolean canPlace;

	/**
	 * Constructor
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imgName, boolean canPlace
	 * @return n/a
	 * @throws n/a
	 */
	TileType(String imgName, boolean canPlace) {
		this.img = ResourceManager.QuickLoad(imgName);
		this.canPlace = canPlace;
	}

	// Getter
	public boolean isCanPlace() {
		return canPlace;
	}

	// Setter
	public void setCanPlace(boolean canPlace) {
		this.canPlace = canPlace;
		
	}

}
