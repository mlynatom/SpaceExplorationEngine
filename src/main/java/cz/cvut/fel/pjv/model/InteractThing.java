package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * abstract class for all interactive things in game
 */
public abstract class InteractThing extends Actor {
	public InteractThing(double iX, double iY, String spriteBound, Image... spriteImage) {
		super(iX, iY, spriteBound, spriteImage);
	}

	@Override
	public abstract void update();

	/**
	 * this method provides interaction with PlayerShip
	 */
	public abstract void interact(PlayerShip playerShip);
}
