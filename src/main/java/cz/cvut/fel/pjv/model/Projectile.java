package cz.cvut.fel.pjv.model;

/**
 * Projectile class for objects which can be shot from ship with entered damage and lifespan. It is based on Actor class.
 */
public class Projectile extends Actor {
	private double lifeSpan;

	public Projectile(double positionX, double positionY, String spriteBound, double lifeSpan, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.lifeSpan = lifeSpan;
	}

	@Override
	public void update() {

	}
}
