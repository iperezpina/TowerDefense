package model;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: Might be used as a way to tell when we should be doing
 *         stuff, but not too sure.
 *
 */
public enum GameState {

	start(0), mainmenu(1), options(2), quit(3), levelselect(4), gameready(5), gameplay(6), gamepaused(7), gamex2(8),
	endround(9);

	int state;

	GameState(int state) {
		this.state = state;
	}

	public boolean equals(GameState other) {
		return this.state == other.state;
	}

	public String toString() {
		String result = "";
		switch (state) {
		case 0:
			result = "start";
			break;
		case 1:
			result = "mainmenu";
			break;
		case 2:
			result = "options";
			break;
		case 3:
			result = "quit";
			break;
		case 4:
			result = "levelselect";
			break;
		case 5:
			result = "gameready";
			break;
		case 6:
			result = "gameplay";
			break;
		case 7:
			result = "gamepaused";
			break;
		case 8:
			result = "gamex2";
			break;
		case 9:
			result = "endround";
			break;
		}
		return result;
	}
}
