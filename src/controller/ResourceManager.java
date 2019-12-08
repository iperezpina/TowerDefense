package controller;

import javafx.scene.image.Image;

public class ResourceManager {
	
	//All the enemies
	public static Image enemyImg = QuickLoad("enemy");
	public static Image enemy2Img = QuickLoad("enemy2");
	
	//Extra Images
	public static Image fireballImg = QuickLoad("Fireball");
	public static Image optionsimg = QuickLoad("Options");
	public static Image startScreenimg = QuickLoad("startscreen");
	
	//All the towers
	public static Image dogTowerImg = QuickLoad("temptower");
	public static Image Tower1img = QuickLoad("tower1");
	public static Image Tower2img = QuickLoad("tower2");
	public static Image Tower3img = QuickLoad("tower3");
	public static Image Tower4img = QuickLoad("tower4");
	public static Image Tower5img = QuickLoad("tower5");
	public static Image Tower6img = QuickLoad("tower6");
	public static Image Tower7img = QuickLoad("tower7");
	public static Image Tower8img = QuickLoad("tower8");
	
	//All the projectiles
	public static Image bloodProjimg = QuickLoad("blood");
	
	//All the tiles
	public static Image grass32Img = QuickLoad("grass32");
	public static Image water32TileImg = QuickLoad("water32");
	public static Image dirt32Tile = QuickLoad("dirt32");
	
	
	public static Image QuickLoad(String imgPath) {
		Image img = new Image("Images/" + imgPath + ".png");
		return img;
	}
	
}
