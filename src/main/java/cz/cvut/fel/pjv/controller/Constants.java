package cz.cvut.fel.pjv.controller;

/**
 * Simple class that contains useful constants which are widely used among other classes.
 */
public class Constants {
	public static final double WIDTH = 1024, HEIGHT = 600; // dimensions of window
	public static final double SHIP_DIMENSIONS = HEIGHT / 6; // dimension of player ship according to window size
	public static final String LEVEL_PATH = "level.yaml"; // name of level data file
	public static final String PLAYER_PATH = "player.yaml"; // name of player data file
	public static final double DEFAULT_SHIP_X_POSITION = 20;
	public static final double DEFAULT_SHIP_Y_POSITION = HEIGHT - SHIP_DIMENSIONS;
	public static final double FINISH_LOCATION_X = WIDTH - SHIP_DIMENSIONS;
	public static final double SHIP_VELOCITY_X = 2;
	public static final double SHIP_VELOCITY_Y = 4;
	public static final double POS_OFF_SCREEN = -500;
	public static final double INTERACT_THING_DIMENSION = 50;
	public static final double ENEMY_DIMENSION = HEIGHT / 12;
	public static final double INIT_PLAYER_PROJECTILE_DAMAGE = 50;
}
