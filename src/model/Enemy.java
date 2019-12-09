
package model;

import controller.Drawer;
import controller.ResourceManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * 
 * @author Alberto Villareal, Laura Bolanos, Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class Enemy {

	// Variables for the enemy class most are self explanatory, some might be
	// removed
	private int x, y, width, height, health, maxHealth, posX, posY;
	private float speed;
	private Image img;
	private Tile startLocation;
	private TileMap tm;
	private Timeline tl;
	private MoveDir md;
	private boolean isExited = false, isDead = false;
	private String imgPath;

	/**
	 * The constructor method of the enemy class, takes in an img, a tile where the
	 * enemy spawns from, a width and height, speed of the enemy (must be a 1, 2, 4,
	 * 8, 16, or 32 atm), and the tilemap of the current level
	 * 
	 * @param img
	 * @param start
	 * @param width
	 * @param height
	 * @param speed
	 * @param tm
	 */
	public Enemy(String imgPath, Tile start, int width, int height, float speed, int health, TileMap tm) {
		if (img == null) {
			img = new Image(imgPath);
		}
		this.imgPath = imgPath;
		this.startLocation = start;
		this.x = start.getX();
		this.y = start.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.tm = tm;
		this.md = MoveDir.RIGHT;
		this.health = health;
		this.maxHealth = this.health;
	}

	/**
	 * Creates an animation of the enemy that moves it along the path
	 */
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();

	}
	//TODO remove below two methods
	public void pause() {
		tl.pause();
	}
	
	public void play() {
		tl.play();
	}

	/**
	 * An animation handler that moves the enemy along various directions along a
	 * path, and draws a newer version of itself
	 *
	 */
	private class AnimationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (health <= 0) {
				tl.stop();
				isDead = true;
			}
			
			if (isExited) {
				tl.stop();
			}
			Draw();
			moveTo();
			x += (int) md.dx * speed;
			y += (int) md.dy * speed;
		}

	}

	/**
	 * Checks what direction the enemy needs to move in depending on the tilemap. It
	 * will move along the tiles that are the same tile as its start tile. Goes up,
	 * down, left, or right.
	 */
	public void moveTo() {
		getPosX();
		getPosY();
		boolean canGoX = x % 32 == 0;
		boolean canGoY = y % 32 == 0;

		// Goes up
		if (tm.GetTile(posX, posY - 1).getType() == startLocation.getType() && !md.state.equals("down") && canGoX
				&& canGoY) {
			md = MoveDir.UP;
		}
		// Goes down
		else if (tm.GetTile(posX, posY + 1).getType() == startLocation.getType() && !md.state.equals("up") && canGoX
				&& canGoY) {
			md = MoveDir.DOWN;
		}
		// Goes right
		else if (tm.GetTile(posX + 1, posY).getType() == startLocation.getType() && !md.state.equals("left") && canGoX
				&& canGoY) {
			md = MoveDir.RIGHT;
		}
		// Goes left
		else if (tm.GetTile(posX - 1, posY).getType() == startLocation.getType() && !md.state.equals("right") && canGoX
				&& canGoY) {
			md = MoveDir.LEFT;
		}
		// Means the enemy has made it off the screen, thus it will be destroyed and
		// die. Add more stuff here to deal damage to player
		else if (tm.GetTile(posX, posY).getType() == TileType.DEBUG && canGoX && canGoY) {
			isExited = true;
			isDead = true;
		}
	}

	/**
	 * Draws the enemy
	 */
	public void Draw() {
		// tm.Draw();
		Drawer.DrawImage(img, x, y, width, height);
		DrawHealth();

	}
	
	public void DrawHealth() {
		int newWidth = (width / maxHealth);
		int healthWidth = newWidth * health;
		Drawer.DrawImage(ResourceManager.QuickLoad("redbar"), x, y + 16, newWidth * maxHealth, 32);
		Drawer.DrawImage(ResourceManager.QuickLoad("greenbar"), x, y + 16, healthWidth, 32);
		
	}

//	The getters and setters are below=================================================================

	public int getPosX() {
		posX = (int) Math.floor(x / 32);
		return posX;
	}

	public int getPosY() {
		posY = (int) Math.floor(y / 32);
		return posY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getHealth() {
		return health;
	}

	public float getSpeed() {
		return speed;
	}

	public Image getImg() {
		return img;
	}

	public Tile getStartLocation() {
		return startLocation;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setStartLocation(Tile startLocation) {
		this.startLocation = startLocation;
	}

	

	public TileMap getTm() {
		return tm;
	}

	public void setTm(TileMap tm) {
		this.tm = tm;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}

	

