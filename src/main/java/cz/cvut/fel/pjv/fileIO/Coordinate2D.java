package cz.cvut.fel.pjv.fileIO;

import javafx.geometry.Point2D;

/**
 * This class is used for serializing coordinates (x and y positions) in order to allow use in Jackson
 * library for yaml files.
 */
public class Coordinate2D extends Point2D {

	/**
	 * Creates a new instance of {@code Point2D}.
	 *
	 * @param x the x coordinate of the point.
	 * @param y the y coordinate of the point.
	 */
	public Coordinate2D(double x, double y) {
		super(x, y);
	}

	/**
	 * Creates a new instance of {@code Point2D}. This constructor is needed for Jackson.
	 */
	public Coordinate2D() {
		super(-1, -1);
	}
}
