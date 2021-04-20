package cz.cvut.fel.pjv.fileIO;

import javafx.geometry.Point2D;

public class coordinate2D extends Point2D {

	/**
	 * Creates a new instance of {@code Point2D}.
	 *
	 * @param x the x coordinate of the point
	 * @param y the y coordinate of the point
	 */
	public coordinate2D(double x, double y) {
		super(x, y);
	}

	/**
	 * Creates a new instance of {@code Point2D}. This constructor is needed for Jackson.
	 */
	public coordinate2D() {
		super(-1,-1);
	}
}
