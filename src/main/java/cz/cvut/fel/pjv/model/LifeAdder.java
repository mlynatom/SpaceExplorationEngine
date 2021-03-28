package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * class for interactive thing which can add life to PlayerShip class
 */
public class LifeAdder extends InteractThing {
	public LifeAdder(double iX, double iY, String spriteBound, Image... spriteImage) {
		super(iX, iY, spriteBound, spriteImage);
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}
}
