package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import pong.Game;
import utils.Constants;

public class Ball {

	private double x, y;
	private double dx, dy;

	private Player player;
	private Enemy enemy;

	public Ball(int x, int y, Player player, Enemy enemy) {
		this.x = x;
		this.y = y;
		this.dx = Math.cos(Math.toRadians(getRandomAngle()));
		this.dy = Math.sin(Math.toRadians(getRandomAngle()));
		this.player = player;
		this.enemy = enemy;
	}

	public void tick() {
		collideChangeBallDirectionX();
		pointsUpdate();
		collideChangeBallDirectionY();

		this.x += this.dx * Constants.BALL_SPEED;
		this.y += this.dy * Constants.BALL_SPEED;
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) this.x, (int) this.y, Constants.BALL_WIDTH, Constants.BALL_HEIGHT);
	}

	private void collideChangeBallDirectionX() {
		if (this.x + (this.dx * Constants.BALL_SPEED) + Constants.BALL_WIDTH >= Constants.GAME_WIDTH
				|| this.x + (this.dx * Constants.BALL_SPEED) < 0) {
			this.dx *= -1;
		}
	}

	private void collideChangeBallDirectionY() {
		Rectangle ballBounds = new Rectangle((int) (this.x + (this.dx * Constants.BALL_SPEED)),
				(int) (this.y + (this.dy * Constants.BALL_SPEED)), Constants.BALL_WIDTH, Constants.BALL_HEIGHT);
		Rectangle playerBounds = this.player.getBounds();
		Rectangle enemyBounds = this.enemy.getBounds();

		if (ballBounds.intersects(playerBounds) || ballBounds.intersects(enemyBounds)) {
			this.dx = Math.cos(Math.toRadians(getRandomAngle()));
			this.dy = Math.sin(Math.toRadians(getRandomAngle()));

			if ((this.dy > 0 && ballBounds.intersects(playerBounds))
					|| (this.dy < 0 && ballBounds.intersects(enemyBounds))) {
				this.dy *= -1;
			}
		}
	}

	private void pointsUpdate() {
		if (this.y >= Constants.GAME_HEIGHT || this.y < 0) {
			verifyCurrentPoint();
			System.out.println("Enemy: " + this.enemy.getPoints() + " || Player: " + this.player.getPoints());
			Game.gameIsPaused = true;
			System.out.println("Press Enter to continue...");
		}
	}

	private void verifyCurrentPoint() {
		if (this.y >= Constants.GAME_HEIGHT - Constants.PLAYER_HEIGHT) {
			this.enemy.incrementPoints();
		} else if (this.y < Constants.PLAYER_HEIGHT) {
			this.player.incrementPoints();
		}
	}

	public double getRandomAngle() {
		return new Random().nextInt(76) + 45;
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

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}
