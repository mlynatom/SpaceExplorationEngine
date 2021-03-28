package cz.cvut.fel.pjv.model;

/**
 * "Super class" for all other object on scene.
 */
public abstract class Actor {
	protected double iX; //initial x position
	protected double iY; //initial y position

	public Actor(double iX, double iY) {
		this.iX = iX;
		this.iY = iY;
	}

	public abstract void update();
}
