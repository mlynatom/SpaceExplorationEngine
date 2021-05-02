package cz.cvut.fel.pjv.model;

/**
 * This class defines obstacle (i.g. rock) which cause damage to player ship in collisions and not allow ship fly through it.
 */
public class Obstacle extends Actor {
	protected double damage;

	public Obstacle(double positionX, double positionY, String spriteBound, double damage, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.damage = damage;
	}

	@Override
	public void update() {
	}
}
