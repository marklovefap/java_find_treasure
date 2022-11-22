package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Content;
import TileMap.TileMap;

public class Player extends Entity{
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private int numDiamonds;
	private int totalDiamonds;
	private long ticks;
	
	public Player(TileMap tm) {
		super(tm);
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		moveSpeed = 2;
		numDiamonds = 0;
		numBombs = 0;
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		animation.setFrames(downSprites);
		animation.setDelay(10);
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}

	public void collectedDiamond() { numDiamonds++; }

	public int numDiamonds() { return numDiamonds; }

	public void setTotalDiamonds(int i) { totalDiamonds = i; }

	public long getTicks() { return ticks; }

	public void setDown() { super.setDown(); }

	public void setLeft() { super.setLeft(); }

	public void setRight() { super.setRight(); }

	public void setAction() {
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
			tileMap.setTile(rowTile + 1, colTile, 2);
			setDown();
			moving = validateNextPosition();
		}
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
			tileMap.setTile(rowTile, colTile - 1, 2);
		}
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
			tileMap.setTile(rowTile, colTile + 1, 2);
		}
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 22) {
			tileMap.setTile(rowTile + 1, colTile, 3);
			setDown();
			moving = validateNextPosition();
		}
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 22) {
			tileMap.setTile(rowTile, colTile - 1, 3);
		}
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 22) {
			tileMap.setTile(rowTile, colTile + 1, 3);
		}
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 23) {
			tileMap.setTile(rowTile + 1, colTile, 3);
			setDown();
			moving = validateNextPosition();
		}
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 23) {
			tileMap.setTile(rowTile, colTile - 1, 3);
		}
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 23) {
			tileMap.setTile(rowTile, colTile + 1, 3);
		}
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 24) {
			tileMap.setTile(rowTile + 1, colTile, 3);
			setDown();
			moving = validateNextPosition();
		}
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 24) {
			tileMap.setTile(rowTile, colTile - 1, 3);
		}
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 24) {
			tileMap.setTile(rowTile, colTile + 1, 3);
		}
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 25) {
			tileMap.setTile(rowTile + 1, colTile, 3);
			setDown();
			moving = validateNextPosition();
		}
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 25) {
			tileMap.setTile(rowTile, colTile - 1, 3);
		}
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 25) {
			tileMap.setTile(rowTile, colTile + 1, 3);
		}
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 36) reveal();
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 36) reveal();
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 36) reveal();
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 37) reveal();
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 37) reveal();
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 37) reveal();
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 38) reveal();
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 38) reveal();
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 38) reveal();
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 39) reveal();
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 39) reveal();
		if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 39) reveal();
	}

	public void reveal() {
		for (int i = 0; i < 48; i++) {
			for (int j = 0; j < 12; j++) {
				if (tileMap.getIndex(i, j) == 36) tileMap.setTile(i, j, 10);
				if (tileMap.getIndex(i, j) == 37) tileMap.setTile(i, j, 10);
				if (tileMap.getIndex(i, j) == 38) tileMap.setTile(i, j, 10);
				if (tileMap.getIndex(i, j) == 39) tileMap.setTile(i, j, 10);
			}
		}
	}

	public boolean hitBomb() {
		if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 10) return true;
		else if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 10) return true;
		else if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 10) return true;
		else return false;
	}

	public boolean finish() {
		if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 7) return true;
		else if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 9) return true;
		else return false;
	}

	public void update() {
		ticks++;
		if (down) setAnimation(DOWN, downSprites, 10);
		if (left) setAnimation(LEFT, leftSprites, 10);
		if (right) setAnimation(RIGHT, rightSprites, 10);
		super.update();
	}

	public void draw(Graphics2D g) { super.draw(g); }
}