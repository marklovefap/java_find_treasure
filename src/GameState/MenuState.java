package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class MenuState extends GameState {
	private BufferedImage bg;
	private BufferedImage arrow;
	private int currentOption = 0;
	private String[] options = {
		"START",
		"QUIT"
	};

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		arrow = Content.ARROW[0][0];
	}

	public void update() { handleInput(); }
	
	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		Content.drawString(g, options[0], 80, 120);
		Content.drawString(g, options[1], 80, 140);
		if (currentOption == 0) g.drawImage(arrow, 63, 116, null);
		else if (currentOption == 1) g.drawImage(arrow, 63, 136, null);
	}
	
	public void handleInput() {
		if (Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) currentOption++;
		if (Keys.isPressed(Keys.UP) && currentOption > 0) currentOption--;
		if (Keys.isPressed(Keys.ENTER)) selectOption();
		if (Keys.isPressed(Keys.SPACE)) selectOption();
	}
	
	private void selectOption() {
		if (currentOption == 0) gsm.setState(GameStateManager.PLAY);
		if (currentOption == 1) System.exit(0);
	}
}