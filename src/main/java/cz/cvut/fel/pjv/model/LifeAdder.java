package cz.cvut.fel.pjv.model;

/**
 * class for interactive thing which can add life to PlayerShip class
 */
public class LifeAdder extends InteractThing {
	protected double lifeToAdd;

	public LifeAdder(double positionX, double positionY, String spriteBound, double lifeToAdd, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.lifeToAdd = lifeToAdd;
	}

	@Override
	public void update() {

	}
}
