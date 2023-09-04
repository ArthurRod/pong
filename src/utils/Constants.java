package utils;

public class Constants {
	public static final int GAME_SCALE = 3;
	public static final double GAME_FPS = 60.0;
	public static final int GAME_WIDTH = 160 * GAME_SCALE;
	public static final int GAME_HEIGHT = 120 * GAME_SCALE;
	public static final int GAME_SPEED = 3;

	public static final int PLAYER_WIDTH = 40 * GAME_SCALE;
	public static final int PLAYER_HEIGHT = 5 * GAME_SCALE;
	public static final int PLAYER_INITIAL_POINT_X = GAME_WIDTH / 2 - PLAYER_WIDTH / 2;
	public static final int PLAYER_INITIAL_POINT_Y = GAME_HEIGHT - PLAYER_HEIGHT;

	public static final int BALL_WIDTH = 4 * GAME_SCALE;
	public static final int BALL_HEIGHT = 4 * GAME_SCALE;
	public static final double BALL_SPEED = 5;
	public static final int BALL_INITIAL_POINT_X = GAME_WIDTH / 2 - BALL_WIDTH / 2;
	public static final int BALL_INITIAL_POINT_Y = GAME_HEIGHT / 2 - BALL_HEIGHT / 2;
}
