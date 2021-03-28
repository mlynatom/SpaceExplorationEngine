package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * This class defines obstacle (i.g. rock) which cause damage to player ship in collisions
 */
public class Obstacle extends Actor {
	private double damage;

	public Obstacle(double iX, double iY, String spriteBound, double damage, Image... spriteImage) {
		super(iX, iY, spriteBound, spriteImage);
		this.damage = damage;
	}

	@Override
	public void update() {

	}
}
