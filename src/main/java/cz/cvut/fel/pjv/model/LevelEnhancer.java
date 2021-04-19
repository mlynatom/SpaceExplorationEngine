package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * class for interactive thing which can increase level of PlayerShip class
 */
public class LevelEnhancer extends InteractThing {
	private int amountOfLevelToAdd;

	public LevelEnhancer(double positionX, double positionY, String spriteBound, int amountOfLevelToAdd, Image... spriteImage) {
		super(positionX, positionY, spriteBound, spriteImage);
		this.amountOfLevelToAdd = amountOfLevelToAdd;
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}
}
