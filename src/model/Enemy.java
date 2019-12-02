package model;


import controller.Drawer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Enemy {

	private static int x;
	private static int y;
	private int width;
	private int height;
	private int health;
	private float speed;
	private Image img;
	private Tile startLocation;
	private boolean first = true;
	private TileMap tm;
	private static Timeline tl;
	private static MoveDir md;
	
	private static int posX, posY;
	
	public static int getPosX() {
		posX =(int) Math.floor(x / 32);
		return posX;
	}

	public static int getPosY() {
		posY = (int) Math.floor(y / 32);
		return posY;
	}

	public Enemy(Image img, Tile start, int width, int height, float speed, TileMap tm) {
		this.img = img;
		this.startLocation = start;
		this.x = start.getX();
		this.y = start.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.tm = tm;
		this.md = MoveDir.RIGHT;
	}
	
	public void update() {
		tl = new Timeline(new KeyFrame(Duration.millis(100), new AnimationHandler() ));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
		
	}
	
	private class AnimationHandler implements EventHandler<ActionEvent>{

		int tick = 0;
		@Override
		public void handle(ActionEvent arg0) {
			tick++;
			Draw();
			//System.out.printf("Current x: %d\tCurrent y: %d\nCurrent posX: %d\tCurrent posY: %d\n", x, y, getPosX(), getPosY());
			moveTo();
			x += (int) md.dx * speed;
			y += (int) md.dy * speed;
			
			
			if (tick >= 1000)
				tl.stop();
		}
		
	}
	
	public void moveTo() {
		getPosX();
		getPosY();
		//System.out.println(startLocation.getType());
		boolean canGoX = x % 32 == 0;
		boolean canGoY = y % 32 == 0;
		
		
		
		//Goes up
		if (tm.GetTile(posX, posY - 1).getType() == startLocation.getType() && !md.state.equals("down") && canGoX && canGoY) {
			//System.out.println("go up");
			md = MoveDir.UP;
		}
		//Goes down
		else if (tm.GetTile(posX, posY + 1).getType() == startLocation.getType() && !md.state.equals("up")  && canGoX && canGoY ) {
			//System.out.println("go down");
			md = MoveDir.DOWN;
		}
		//Goes right
		else if (tm.GetTile(posX + 1, posY).getType() == startLocation.getType() && !md.state.equals("left")  && canGoX && canGoY) {
			//System.out.println("go right");
			md = MoveDir.RIGHT;
		}
		//Goes left
		else if (tm.GetTile(posX - 1, posY).getType() == startLocation.getType() && !md.state.equals("right") && canGoX && canGoY) {
			//System.out.println("go left");
			md = MoveDir.LEFT;
		}
		
		
	}
	
	public void Draw() {
		//tm.Draw();
		Drawer.DrawImage(img, x, y, width, height);
		
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

	public boolean isFirst() {
		return first;
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

	public void setFirst(boolean first) {
		this.first = first;
	}
}
