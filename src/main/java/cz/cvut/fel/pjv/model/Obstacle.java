package cz.cvut.fel.pjv.model;

public class Obstacle extends Actor {
	private double damage;

	public Obstacle(double iX, double iY, double damage) {
		super(iX, iY);
		this.damage = damage;
	}

	@Override
	public void update() {

	}
}
