package cz.cvut.fel.pjv.model;

/**
 * This class creates level enhancer actor which increase level of playerShip.
 */
public class LevelEnhancer extends Actor {
	protected int amountOfLevelToAdd;

	public LevelEnhancer(double positionX, double positionY, String spriteBound, int amountOfLevelToAdd, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.amountOfLevelToAdd = amountOfLevelToAdd;
	}

	@Override
	public void update() {

	}
}
