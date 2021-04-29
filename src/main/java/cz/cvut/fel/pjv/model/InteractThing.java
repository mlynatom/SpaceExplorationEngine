package cz.cvut.fel.pjv.model;

/**
 * abstract class for all interactive things in game
 */
public abstract class InteractThing extends Actor {

	public InteractThing(double positionX, double positionY, String spriteBound, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
	}

	@Override
	public abstract void update();
}
