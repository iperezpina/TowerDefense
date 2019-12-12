package controller;

import javafx.scene.image.Image;



/**
 * Resource Manager class
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class ResourceManager {

	// All the enemies
	public static Image ghostImg = QuickLoad("ghost");
	public static Image redGhostImg = QuickLoad("redghost");
	public static Image blueGhostImg = QuickLoad("blueghost");
	public static Image yellowGhostImg = QuickLoad("yellowghost");
	public static Image greenGhostImg = QuickLoad("greenghost");
	public static Image purpleGhostImg = QuickLoad("purpleghost");
	public static Image metalGhostImg = QuickLoad("metalghost");
	public static Image goldGhostImg = QuickLoad("goldghost");
	public static Image bossImg = QuickLoad("ghoully");

	// Extra Images
	public static Image fireballImg = QuickLoad("Fireball");
	public static Image startScreenImg = QuickLoad("hellbgd");
	// public static Image startScreenImg = QuickLoad("startscreen");

	// All the towers
	public static Image Tower1Img = QuickLoad("tower1");
	public static Image Tower2Img = QuickLoad("tower2");
	public static Image Tower3Img = QuickLoad("tower3");
	public static Image Tower4Img = QuickLoad("tower4");
	public static Image Tower5Img = QuickLoad("tower5");
	public static Image Tower6Img = QuickLoad("tower6");
	public static Image Tower7Img = QuickLoad("tower7");
	public static Image Tower8Img = QuickLoad("tower8");

	// All the projectiles
	public static Image bloodProjImg = QuickLoad("blood");
	public static Image arrowProjImg = QuickLoad("arrow");
	public static Image boneProjImg = QuickLoad("Bone");
	public static Image rockProjImg = QuickLoad("rock");
	public static Image lazerProjImg = QuickLoad("lazer");
	public static Image lightningProjImg = QuickLoad("lightning");
	public static Image fireProjImg = QuickLoad("Fireball");
	public static Image lavaProjImg = QuickLoad("lavaShot");
	public static Image holyWaterProjImg = QuickLoad("holyWater");

	// All the tiles
	public static Image grass32Img = QuickLoad("grass32");
	public static Image water32TileImg = QuickLoad("water32");
	public static Image dirt32Tile = QuickLoad("dirt32");
	public static Image hellGround1Img = QuickLoad("hellground1");
	public static Image hellGround2Img = QuickLoad("hellground2");
	public static Image hellGround3Img = QuickLoad("hellground3");
	public static Image lava1Img = QuickLoad("lava");
	public static Image lava23Img = QuickLoad("lava2");
	public static Image lava3Img = QuickLoad("lava3");

	/**
	 * This function creates a Image using a strin path
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String ImgPath
	 * @return Image img
	 * @throws n/a
	 */
	public static Image QuickLoad(String ImgPath) {
		Image Img = new Image("Images/" + ImgPath + ".png");
		return Img;
	}

	/**
	 * This function get the Enemy Img and calls the Enum
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String imgName
	 * @return Image temp
	 * @throws n/a
	 */
	public static Image getEnemyImg(String ImgName) {
		Image temp = null;
		switch (ImgName) {
		case "ghost":
			temp = ghostImg;
			break;
		case "redghost":
			temp = redGhostImg;
			break;
		case "blueghost":
			temp = blueGhostImg;
			break;
		case "yellowghost":
			temp = yellowGhostImg;
			break;
		case "greenghost":
			temp = greenGhostImg;
			break;
		case "purpleghost":
			temp = purpleGhostImg;
			break;
		case "metalghost":
			temp = metalGhostImg;
			break;
		case "goldghost":
			temp = goldGhostImg;
			break;
		case "ghoully":
			temp = bossImg;
			break;
		}
		return temp;
	}

	/**
	 * This function calls the ImgName and creates the projectile
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String ImgName
	 * @return Image temp
	 * @throws n/a
	 */
	public static Image getProjectileImg(String ImgName) {
		Image temp = null;
		switch (ImgName) {
		case "blood":
			temp = bloodProjImg;
			break;
		case "arrow":
			temp = arrowProjImg;
			break;
		case "Bone":
			temp = boneProjImg;
			break;
		case "rock":
			temp = rockProjImg;
			break;
		case "lazer":
			temp = lazerProjImg;
			break;
		case "lightning":
			temp = lightningProjImg;
			break;
		case "Fireball":
			temp = fireProjImg;
			break;
		case "lavaShot":
			temp = lavaProjImg;
			break;
		case "holyWater":
			temp = holyWaterProjImg;
			break;
		}
		return temp;
	}

	/**
	 * This function get the Tower Image from the string
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String ImgName
	 * @return Image temp
	 * @throws n/a
	 */
	public static Image getTowerImg(String ImgName) {
		Image temp = null;
		switch (ImgName) {
		case "tower1":
			temp = Tower1Img;
			break;
		case "tower2":
			temp = Tower2Img;
			break;
		case "tower3":
			temp = Tower3Img;
			break;
		case "tower4":
			temp = Tower4Img;
			break;
		case "tower5":
			temp = Tower5Img;
			break;
		case "tower6":
			temp = Tower6Img;
			break;
		case "tower7":
			temp = Tower7Img;
			break;
		case "tower8":
			temp = Tower8Img;
			break;
		}
		return temp;
	}

}
