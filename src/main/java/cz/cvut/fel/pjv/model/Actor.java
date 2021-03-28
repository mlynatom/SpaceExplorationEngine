package cz.cvut.fel.pjv.model;

public abstract class Actor {
	protected double iX; //x position
	protected double iY; //y position

	public Actor(double iX, double iY) {
		this.iX = iX;
		this.iY = iY;
	}
}
