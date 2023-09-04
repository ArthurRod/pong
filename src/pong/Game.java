package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import utils.Constants;

public class Game extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean isRunning;

	private BufferedImage backgroundLayer;
	private Player player;
	private Enemy enemy;
	private Ball ball;

	public static boolean gameIsPaused;

	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
		this.backgroundLayer = new BufferedImage(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		initializeGame();
	}

	private void initializeGame() {
		this.player = new Player(Constants.PLAYER_INITIAL_POINT_X, Constants.PLAYER_INITIAL_POINT_Y, 0);
		this.enemy = new Enemy(Constants.PLAYER_INITIAL_POINT_X, 0, 0);
		this.ball = new Ball(Constants.BALL_INITIAL_POINT_X, Constants.BALL_INITIAL_POINT_Y, this.player, this.enemy);
		this.enemy.setGameBall(this.ball);
		gameIsPaused = true;
		System.out.println("Press Enter to start...");
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ns = 1000000000 / Constants.GAME_FPS;
		double delta = 0;

		while (this.isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}

			if (gameIsPaused) {
				resetGame();
			}
		}
	}

	public void tick() {

		if (!gameIsPaused) {
			/* Tick Player */
			this.player.tick();

			/* Tick Enemy */
			this.enemy.tick();

			/* Tick Ball */
			this.ball.tick();
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		/* Render Background layer */
		Graphics g = backgroundLayer.getGraphics();
		g = bs.getDrawGraphics();
		g.drawImage(backgroundLayer, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT, null);

		/* Render Player */
		this.player.render(g);

		/* Render Enemy */
		this.enemy.render(g);

		/* Render Ball */
		this.ball.render(g);

		bs.show();

	}

	public void createJFrame() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public synchronized void start() {
		this.thread = new Thread(this);
		this.isRunning = true;
		this.thread.start();
	}

	public void resetGame() {
		this.player.setX(Constants.PLAYER_INITIAL_POINT_X);
		this.player.setY(Constants.PLAYER_INITIAL_POINT_Y);

		this.enemy.setX(Constants.PLAYER_INITIAL_POINT_X);
		this.enemy.setY(0);

		this.ball.setX(Constants.BALL_INITIAL_POINT_X);
		this.ball.setY(Constants.BALL_INITIAL_POINT_Y);
		this.ball.setPlayer(this.player);
		this.ball.setEnemy(this.enemy);

		this.enemy.setGameBall(this.ball);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		playGame(e);

		switch (e.getKeyCode()) {

		case KeyEvent.VK_RIGHT:
			this.player.setRight(true);
			break;

		case KeyEvent.VK_LEFT:
			this.player.setLeft(true);
			break;

		case KeyEvent.VK_UP:
			this.player.setUp(true);
			break;

		case KeyEvent.VK_DOWN:
			this.player.setDown(true);
			break;

		default:
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_RIGHT:
			this.player.setRight(false);
			break;

		case KeyEvent.VK_LEFT:
			this.player.setLeft(false);
			break;

		case KeyEvent.VK_UP:
			this.player.setUp(false);
			break;

		case KeyEvent.VK_DOWN:
			this.player.setDown(false);
			break;

		default:
			break;

		}
	}

	public void playGame(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (gameIsPaused) {
				gameIsPaused = false;
			}
		}
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

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
