package Manager;

import java.awt.Graphics2D;
import GameState.*;

public class GameStateManager {
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 5;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int RESTART = 4;
	
	public GameStateManager() {
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if (i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		} else if (i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		} else if (i == PLAY) {
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		} else if (i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		} else if (i == RESTART) {
			gameStates[i] = new ReStartState(this);
			gameStates[i].init();
		}
	}

	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void update() {
		if (gameStates[currentState] != null) gameStates[currentState].update();
	}
	
	public void draw(Graphics2D g) {
		if (gameStates[currentState] != null) gameStates[currentState].draw(g);
	}
}