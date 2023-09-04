package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import utils.Constants;

public class Enemy {

	private boolean isRight, isLeft, isUp, isDown;
	private double x, y;
	private int points;
	private Ball gameBall;

	public Enemy(int x, int y, int points) {
		this.x = x;
		this.y = y;
		this.points = points;
	}

	public void tick() {
		x += (gameBall.getX() - x - (Constants.PLAYER_WIDTH / 2)) * 0.04;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
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

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
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

	public void setGameBall(Ball gameBall) {
		this.gameBall = gameBall;
	}
}
