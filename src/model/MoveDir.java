
package model;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public enum MoveDir {

	// The four basic directions
	UP("up"), DOWN("down"), RIGHT("right"), LEFT("left");

	// The variables for this enum
	int dx, dy;
	String state;

	/**
	 * The constructor for this enum, takes in a string specifying one of the four
	 * states
	 * 
	 * @param state
	 */
	MoveDir(String state) {
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

