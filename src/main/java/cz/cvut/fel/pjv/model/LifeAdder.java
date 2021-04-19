package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * class for interactive thing which can add life to PlayerShip class
 */
public class LifeAdder extends InteractThing {
	public LifeAdder(double positionX, double positionY, String spriteBound, Image... spriteImage) {
		super(positionX, positionY, spriteBound, spriteImage);
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}
}
