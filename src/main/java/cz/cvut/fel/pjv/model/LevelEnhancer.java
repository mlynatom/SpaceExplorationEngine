package cz.cvut.fel.pjv.model;

/**
 * class for interactive thing which can increase level of PlayerShip class
 */
public class LevelEnhancer extends InteractThing {
	protected int amountOfLevelToAdd;

	public LevelEnhancer(double positionX, double positionY, String spriteBound, int amountOfLevelToAdd, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.amountOfLevelToAdd = amountOfLevelToAdd;
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}
}
