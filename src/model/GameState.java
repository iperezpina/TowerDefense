package model;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: Might be used as a way to tell when we should be doing stuff, but
 *         not too sure.  
 *
 */
public enum GameState {

	start(0), mainmenu(1), options(2), quit(3), levelselect(4), 
	gameready(5), gameplay(6), gamepaused(7), gamex2(8), endround(9);
	
	int state;
	
	GameState(int state) {
		this.state = state;
	}
}
