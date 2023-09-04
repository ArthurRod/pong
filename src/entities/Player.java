package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import utils.Constants;

public class Player {

	private boolean isRight, isLeft, isUp, isDown;
	private int points;
	private int x, y;

	public Player(int x, int y, int points) {
		this.x = x;
		this.y = y;
		this.points = points;
	}

	public void tick() {
		if (isRight) {
			x += Constants.GAME_SPEED;
		} else if (isLeft) {
			x -= Constants.GAME_SPEED;
		}

		if (x + Constants.PLAYER_WIDTH > Constants.GAME_WIDTH) {
			x = Constants.GAME_WIDTH - Constants.PLAYER_WIDTH;
		} else if (x < 0) {
			x = 0;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int incrementPoints() {
		return ++points;
	}
}
