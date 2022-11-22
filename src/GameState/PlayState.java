package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import Entity.Diamond;
import Entity.Player;
import HUD.Hud;
import Main.GamePanel;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;
import TileMap.TileMap;

public class PlayState extends GameState {
	private Player player;
	private TileMap tileMap;
	private ArrayList<Diamond> diamonds;
	private int xsector;
	private int ysector;
	private int sectorSize;
	private Hud hud;
	private boolean eventStart;
	private boolean eventMistake;
	private boolean eventFinish;
	private int eventTick;
	private ArrayList<Rectangle> boxes;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		diamonds = new ArrayList<Diamond>();
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");

		player = new Player(tileMap);
		populateTrap();
		populateDiamonds();
		populateChest();
		player.setTilePosition(1, 1);
		player.setTotalDiamonds(diamonds.size());
		sectorSize = GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		hud = new Hud(player);
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();
	}

	public void populateTrap() {
		Random rd = new Random();
		for (int i = 1; i < 11; i++) {
			for (int j = 3; j < 12; j++) {
				if (rd.nextInt(10) == 7 ) tileMap.setTile(j, i, 36);
			}
			for (int j = 13; j < 24; j++) {
				if (rd.nextInt(10) == 7) tileMap.setTile(j, i, 37);
			}
			for (int j = 25; j < 35; j++) {
				if (rd.nextInt(10) == 7) tileMap.setTile(j, i, 38);
			}
			for (int j = 35; j < 36; j++) {
				if (rd.nextInt(10) == 7) tileMap.setTile(j, i, 39);
			}
			for (int j = 37; j < 44; j++) {
				if (rd.nextInt(10) == 7) tileMap.setTile(j, i, 39);
			}
		}
	}

	private void populateDiamonds() {
		Diamond d;
		Random rd = new Random();
		int number1, number2;

		d = new Diamond(tileMap);
		number1 = rd.nextInt(4) + 4;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 36) {
			number1 = rd.nextInt(4) + 4;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(4) + 8;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 36) {
			number1 = rd.nextInt(4) + 8;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(5) + 13;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 37) {
			number1 = rd.nextInt(5) + 13;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(6) + 18;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 37) {
			number1 = rd.nextInt(6) + 18;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(5) + 25;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 38) {
			number1 = rd.nextInt(5) + 25;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(5) + 30;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 38) {
			number1 = rd.nextInt(6) + 30;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(3) + 37;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 39) {
			number1 = rd.nextInt(3) + 37;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);

		d = new Diamond(tileMap);
		number1 = rd.nextInt(4) + 40;
		number2 = rd.nextInt(10) + 1;
		while (tileMap.getIndex(number1, number2) == 39) {
			number1 = rd.nextInt(4) + 40;
			number2 = rd.nextInt(10) + 1;
		}
		d.setTilePosition(number1, number2);
		diamonds.add(d);
	}

	public void populateChest() {
		Random rd = new Random();
		int finish = rd.nextInt(2);
		if (finish == 0) {
			tileMap.setTile(45, 1, 6);
			tileMap.setTile(45, 2, 7);
			tileMap.setTile(44, 1, 4);
			tileMap.setTile(44, 2, 5);
		} else if (finish == 1) {
			tileMap.setTile(45, 9, 8);
			tileMap.setTile(45, 10, 9);
			tileMap.setTile(44, 9, 4);
			tileMap.setTile(44, 10, 5);
		}
	}

	public void update() {
		handleInput();
		if (eventStart) eventStart();
		if (eventMistake) eventMistake();
		if (eventFinish) eventFinish();
		if (player.hitBomb()) eventMistake = true;
		if (player.finish()) eventFinish = true;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		if (tileMap.isMoving()) return;
		player.update();

		for (int i = 0; i < diamonds.size(); i++) {
			Diamond d = diamonds.get(i);
			d.update();
			if (player.intersects(d)) {
				diamonds.remove(i);
				i--;
				player.collectedDiamond();
				ArrayList<int[]> ali = d.getChanges();
				for (int[] j : ali) tileMap.setTile(j[0], j[1], j[2]);
			}
		}
	}

	public void draw(Graphics2D g) {
		tileMap.draw(g);
		player.draw(g);
		for (Diamond d : diamonds) d.draw(g);
		hud.draw(g);
		g.setColor(new Color(164,198,222));
		for (int i = 0; i < boxes.size(); i++) g.fill(boxes.get(i));
	}

	public void handleInput() {
		if (Keys.isDown(Keys.LEFT)) player.setLeft();
		if (Keys.isDown(Keys.RIGHT)) player.setRight();
		if (Keys.isDown(Keys.DOWN)) player.setDown();
		if (Keys.isPressed(Keys.SPACE)) player.setAction();
		if (Keys.isPressed(Keys.ESC)) System.exit(0);
	}

	private void eventStart() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 20; i++) boxes.add(new Rectangle(0, i * 11, GamePanel.WIDTH, 11));
		}
		if (eventTick > 1 && eventTick < 39) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) r.x -= 5;
				else r.x += 5;
			}
		}
		if (eventTick == 39) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}

	public void eventMistake() {
		Data.setDeath();
		gsm.setState(GameStateManager.RESTART);
	}

	private void eventFinish() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 20; i++) {
				if (i % 2 == 0) boxes.add(new Rectangle(-128, i * 11, GamePanel.WIDTH, 11));
				else boxes.add(new Rectangle(128, i * 11, GamePanel.WIDTH, 11));
			}
		}
		if (eventTick > 1) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				} else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if (eventTick > 39) {
			Data.setDia(player.numDiamonds());
			Data.setTime(player.getTicks());
			gsm.setState(GameStateManager.GAMEOVER);
		}
	}
}