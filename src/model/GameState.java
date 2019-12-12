package model;

/**
 * 
 * Summary: Might be used as a way to tell when we should be doing stuff, but
 * not too sure.
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public enum GameState {
	// Enums to get the gameStates
	start(0), mainmenu(1), options(2), quit(3), levelselect(4), gameready(5), gameplay(6), gamepaused(7), gamex2(8),
	endround(9);

	int state;

	/**
	 * Contructor
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int state
	 * @return n/a
	 * @throws n/a
	 */
	GameState(int state) {
		this.state = state;
	}

	/**
	 * This takes in a games state and changes it
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param GameState other
	 * @return n/a
	 * @throws n/a
	 */
	public boolean equals(GameState other) {
		return this.state == other.state;
	}

	/**
	 * to String method also uses cases
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return String result
	 * @throws n/a
	 */
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
