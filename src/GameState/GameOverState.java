package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.GamePanel;
import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;

public class GameOverState extends GameState {
	private int rank;
	private long ticks;
	private long score;
	private int numDia;
	private BufferedImage arrow;
	private BufferedImage diamond;
	private int currentOption = 0;
	private String[] options = {
			"MAKE IT BETTER",
			"QUIT"
	};
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		arrow = Content.ARROW[0][0];
		diamond = Content.DIAMOND[0][0];
		ticks = Data.getTime();
		numDia = Data.getDia();
		if (ticks < 1800) score = 100;
		else if (ticks < 2700) score = 90;
		else if (ticks < 3600) score = 80;
		else if (ticks < 5400) score = 70;
		else if (ticks < 7200) score = 60;
		else if (ticks < 9000) score = 50;
 		else score = 40;
 		checkscore();
	}

	public void update() { handleInput(); }
	
	public void draw(Graphics2D g) {
		g.setColor(new Color(164, 198, 222));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		Content.drawString(g, "finish game", 53, 26);

		int minutes = (int) (ticks / 1800);
		int seconds = (int) ((ticks / 30) % 60);
		if (seconds < 10) Content.drawString(g, "time " + "0" + minutes + ":0" + seconds, 62, 56);
		else Content.drawString(g, "time " + "0" + minutes + ":" + seconds, 62, 56);

		Content.drawString(g, "diamond " + numDia + "/8", 38, 71);
		g.drawImage(diamond, 127, 68, null);

		Content.drawString(g, "score " + score, 54, 86);

		Content.drawString(g, "rank", 81, 116);
		if (rank == 1) Content.drawString(g, " god of adventurer", 21, 131);
		else if (rank == 2) Content.drawString(g, "reseacher", 61, 131);
		else if (rank == 3) Content.drawString(g, "beginner", 65, 131);
		else if (rank == 4) Content.drawString(g, "flabby", 73, 131);
		else if (rank == 5) Content.drawString(g, "bumbling idiot", 41, 131);

		Content.drawString(g, options[0], 41, 161);
		Content.drawString(g, options[1], 81, 176);
		if (currentOption == 0) g.drawImage(arrow, 24, 157, null);
		else if (currentOption == 1) g.drawImage(arrow, 64, 172, null);
	}
	
	public void handleInput() {
		if (Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) currentOption++;
		if (Keys.isPressed(Keys.UP) && currentOption > 0) currentOption--;
		if (Keys.isPressed(Keys.ENTER)) selectOption();
		if (Keys.isPressed(Keys.SPACE)) selectOption();
	}

	private void selectOption() {
		Data.resetDeath();
		if (currentOption == 0) gsm.setState(GameStateManager.MENU);
		if (currentOption == 1) System.exit(0);
	}

	private void checkscore() {
		score -= (8 - numDia) * 5;
		score -= Data.getDeath();
		if (score > 80) rank = 1;
		else if (score > 60) rank = 2;
		else if (score > 40) rank = 3;
		else if (score > 20) rank = 4;
		else rank = 5;
	}
}