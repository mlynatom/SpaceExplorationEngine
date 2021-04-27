package cz.cvut.fel.pjv.model;

/**
 * This class defines obstacle (i.g. rock) which cause damage to player ship in collisions
 */
public class Obstacle extends Actor {
	private double damage;

	public Obstacle(double positionX, double positionY, String spriteBound, double damage, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.damage = damage;
	}

	@Override
	public void update() {

	}
}
