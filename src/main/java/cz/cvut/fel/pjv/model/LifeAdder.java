package cz.cvut.fel.pjv.model;

/**
 * This class creates life adder actor which adds life to player ship.
 */
public class LifeAdder extends Actor {
	protected double lifeToAdd;

	public LifeAdder(double positionX, double positionY, String spriteBound, double lifeToAdd, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.lifeToAdd = lifeToAdd;
	}

	@Override
	public void update() {

	}
}
