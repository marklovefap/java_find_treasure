package HUD;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entity.Player;
import Main.GamePanel;
import Manager.Content;
import Manager.Data;

public class Hud {
	private int yoffset;
	private BufferedImage bar;
	private BufferedImage diamond;
	private BufferedImage pickaxe;
	private BufferedImage bomb;
	private BufferedImage death;
	private Player player;
	private int numDiamonds;
	private Font font;
	
	public Hud(Player p) {
		player = p;
		numDiamonds = 8;
		yoffset = GamePanel.HEIGHT;
		bar = Content.BAR[0][0];
		diamond = Content.DIAMOND[0][0];
		pickaxe = Content.ITEMS[0][0];
		bomb = Content.ITEMS[0][1];
		death = Content.ITEMS[0][2];
		font = new Font("Arial", Font.PLAIN, 10);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(bar, 0, yoffset, null);
		g.setFont(font);
		Content.drawString(g, player.numDiamonds() + "/" + numDiamonds, 53, yoffset + 3);
		g.drawImage(diamond, 78, yoffset, null);

		g.drawImage(death, 100, yoffset, null);
		Content.drawString(g, ":" + Data.getDeath(), 113, yoffset + 3);

		g.drawImage(bomb, 143, yoffset, null);
		Content.drawString(g, ":" + player.numBombs(), 156, yoffset + 3);
		g.drawImage(pickaxe, 176, yoffset, null);

		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if (seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 4, 195);
		else Content.drawString(g, "0" + minutes + ":" + seconds, 4, 195);
	}
}