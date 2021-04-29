package cz.cvut.fel.pjv.controller;

/**
 * Simple public class that contains useful constants which are widely used among other classes.
 */
public class Constants {
	public static final double WIDTH = 1024, HEIGHT = 600; // dimensions of window
	public static final double SHIP_DIMENSIONS = HEIGHT / 6; // dimension of player ship according to window size
	public static final String LEVEL_PATH = "level.yaml"; // name of level data file
	public static final String PLAYER_PATH = "player.yaml"; // name of player data file
	public static final double DEFAULT_SHIP_X_POSITION = 20;
	public static final double SHIP_VELOCITY_X = 2;
	public static final double SHIP_VELOCITY_Y = 4;
}
