package model;

public class POS {

	private int x;
	private int y;
	
	public POS(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isThere(POS end) {
		if (this.x == end.x && this.y == end.y)
			return true;
		return false;
		
	}

	
	//Getters and Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move(POS end) {
		//start x is less than end x
		if (this.x < end.x)
			this.x += 1;
		
		//start x is greater than end x
		if (this.x > end.x)
			this.x -= 1;
		
		//start y is less than end y
		if (this.y < end.y)
			this.y += 1;
		
		//start y is greater than end y
		if (this.y > end.y)
			this.y -= 1;
	}
	
	public String toString() {
		String result = "";
		result += "X:\t" + x + "\tY:\t" + y;
		return result;
	}
	
	
}
