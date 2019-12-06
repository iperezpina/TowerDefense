package model;

public enum MoveDir {

	UP("up"), DOWN("down"), RIGHT("right"), LEFT("left");
	
	int dx, dy;
	String state;

	MoveDir(String state){
		if (state.toLowerCase().equals("up")) {
			this.dx = 0;
			this.dy = -1;
			this.state = "up";
		}
		if (state.toLowerCase().equals("down")) {
			this.dx = 0;
			this.dy = 1;
			this.state = "down";
		}
		if (state.toLowerCase().equals("right")) {
			this.dx = 1;
			this.dy = 0;
			this.state = "right";
		}
		if (state.toLowerCase().equals("left")) {
			this.dx = -1;
			this.dy = 0;
			this.state = "left";
		}
		
	}
}
