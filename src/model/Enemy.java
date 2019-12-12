
package model;

import controller.Drawer;
import controller.Player;
import controller.ResourceManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * This class represent the Enemy
 * 
 * @author Ivan, Marisa, Laura, Albert
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
	private boolean isExited = false, isDead = false, isPaused = false;
	private String imgPath;
	private int cashBack;

	private boolean isSlowed = false;
	private int poisonCounter = 10;

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
			img = ResourceManager.getEnemyImg(imgPath);
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
		this.cashBack = calculateCashBack();
	}

	/**
	 * This function calculates the cash to get back
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return int temp (cash)
	 * @throws n/a
	 */
	private int calculateCashBack() {
		int temp = 0;
		temp += (health / 4) + speed;
		return temp;
	}

	/**
	 * Halves an enemy speed permanently, only works once
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void slowEnemy() {
		if (!isSlowed) {
			if (speed / 2 <= 0) {
				speed = 1;
			} else {
				speed /= 2;
			}
			isSlowed = true;
		}

	}

	/**
	 * Sets the Poison for the enemy
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void poisonEnemy() {
		poisonCounter = 0;
	}

	/**
	 * This is if the Tower applies the posion tick
	 *
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	private void poisonTick() {
		health--;
		poisonCounter++;
	}

	/**
	 * Creates an animation of the enemy that moves it along the path
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(250), new AnimationHandler()));
		tl.setCycleCount(Animation.INDEFINITE);

		// TODO this causes ghosts to run always
		tl.play();
	}

	/**
	 * Pause the game
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void pause() {
		tl.pause();
	}

	/**
	 * Play at the start of the game
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void playFromStart() {
		tl.playFromStart();
	}

	/**
	 * Play button
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void play() {
		tl.play();
	}

	/**
	 * An animation handler that moves the enemy along various directions along a
	 * path, and draws a newer version of itself
	 *
	 * Draw method that draws the enemy
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent arg0
	 * @return n/a
	 * @throws n/a
	 */
	private class AnimationHandler implements EventHandler<ActionEvent> {

		int counter = 1;

		@Override
		public void handle(ActionEvent arg0) {
			if (health <= 0) {
				tl.stop();
				isDead = true;
				Player.addCash(cashBack);
			}

			if (isExited) {
				tl.stop();
			}
			Draw();
			if (!isPaused) {

				moveTo();
				// If the game should be paused set movement to zero essentially
				if (Player.getGameState().equals(GameState.gamepaused)) {
					x += (int) md.dx * speed * 0;
					y += (int) md.dy * speed * 0;
				}
				// If the game should be going at x2 speed double enemy speed
				if (Player.getGameState().equals(GameState.gamex2)) {
					float tempSpeed = speed * 2;
					if (tempSpeed > 32) {
						tempSpeed = 32;
					}
					x += (int) md.dx * tempSpeed;
					y += (int) md.dy * tempSpeed;
				}
				// If the game is playing normally run at regular enemy speed
				if (Player.getGameState().equals(GameState.gameplay)) {
					x += (int) md.dx * speed;
					y += (int) md.dy * speed;
				}

			}
			if (!Player.getGameState().equals(GameState.gamepaused)) {
				if (poisonCounter < 5 && counter % 4 == 0) {
					poisonTick();
				}
				counter++;
			}

		}

	}

	/**
	 * Checks what direction the enemy needs to move in depending on the tilemap. It
	 * will move along the tiles that are the same tile as its start tile. Goes up,
	 * down, left, or right.
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
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
			Player.takeDmg();
		}
	}

	/**
	 * This function reads the Next spot to go to
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int bound, Tile nextTile
	 * @return boolean reached
	 * @throws n/a
	 */
	private boolean reachNext(int bound, Tile nextTile) {
		boolean reached = false;

		if (x > nextTile.getX() - bound && x < nextTile.getX() + bound && y > nextTile.getY() - bound
				&& y < nextTile.getY() + bound)
			reached = true;

		return reached;
	}

	/**
	 * Draw method that draws the enemy
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void Draw() {
		// tm.Draw();
		Drawer.DrawImage(img, x, y, width, height);
		DrawHealth();

	}

	/**
	 * This function draws the health bar on the enemies
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void DrawHealth() {
		double newWidth = (double) health / (double) maxHealth;
		if (newWidth < 0) {
			newWidth = 0;
		}
		Drawer.DrawImage(ResourceManager.QuickLoad("redbar"), x, y + 16, 32, 32.0);
		Drawer.DrawImage(ResourceManager.QuickLoad("greenbar"), x, y + 16, (double) newWidth * 32.0, 32.0);

	}

//	The getters and setters are below=================================================================
	// Getter
	public int getPosX() {
		posX = (int) Math.floor(x / 32);
		return posX;
	}

	// Getter
	public int getPosY() {
		posY = (int) Math.floor(y / 32);
		return posY;
	}

	// Getter
	public int getX() {
		return x;
	}

	// Getter
	public int getY() {
		return y;
	}

	// Getter
	public int getWidth() {
		return width;
	}

	// Getter
	public int getHeight() {
		return height;
	}

	// Getter
	public int getHealth() {
		return health;
	}

	// Getter
	public float getSpeed() {
		return speed;
	}

	// Getter
	public Image getImg() {
		return img;
	}

	// Getter
	public Tile getStartLocation() {
		return startLocation;
	}

	// Setter
	public void setX(int x) {
		this.x = x;
	}

	// Setter
	public void setY(int y) {
		this.y = y;
	}

	// Setter
	public void setWidth(int width) {
		this.width = width;
	}

	// Setter
	public void setHeight(int height) {
		this.height = height;
	}

	// Setter
	public void setHealth(int health) {
		this.health = health;
	}

	// Setter
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	// Setter
	public void setImg(Image img) {
		this.img = img;
	}

	// Setter
	public void setStartLocation(Tile startLocation) {
		this.startLocation = startLocation;
	}

	// Getter
	public TileMap getTm() {
		return tm;
	}

	// Setter
	public void setTm(TileMap tm) {
		this.tm = tm;
	}

	// Getter
	public boolean isDead() {
		return isDead;
	}

	// Setter
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	// Getter
	public String getImgPath() {
		return imgPath;
	}

	// Setter
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * A string representation of the enemies
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return String result
	 * @throws n/a
	 */
	public String toString() {
		String result = "";
		result += imgPath + " spd: " + speed + " health: " + maxHealth;
		return result;

	}

	// Getter
	public Timeline getTL() {
		return tl;
	}

	// Setter
	public void setisPaused() {
		this.isPaused = true;
	}
}
