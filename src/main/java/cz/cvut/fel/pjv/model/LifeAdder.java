package cz.cvut.fel.pjv.model;

/**
 * class for interactive thing which can add life to PlayerShip class
 */
public class LifeAdder extends InteractThing {

	public LifeAdder(double positionX, double positionY, String spriteBound, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}
}
