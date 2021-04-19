package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * This class defines obstacle (i.g. rock) which cause damage to player ship in collisions
 */
public class Obstacle extends Actor {
	private double damage;

	public Obstacle(double positionX, double positionY, String spriteBound, double damage, Image... spriteImage) {
		super(positionX, positionY, spriteBound, spriteImage);
		this.damage = damage;
	}

	@Override
	public void update() {

	}
}
