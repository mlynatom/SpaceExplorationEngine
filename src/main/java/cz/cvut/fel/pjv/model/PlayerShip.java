package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * This class is for player ship, the main and only playable object in game.
 */
public class PlayerShip extends Ship {
	private int level; // current level of ship
	private double fuel; // amount of fuel in ship

	public PlayerShip(double iX, double iY, double velocityX, double velocityY, String spriteBound, double life, double damage, boolean isAlive, Projectile projectile, int level, double fuel, Image... spriteImage) {
		super(iX, iY, velocityX, velocityY, spriteBound, life, damage, projectile, spriteImage);
		this.level = level;
		this.fuel = fuel;
	}

	@Override
	public void update() {

	}

	@Override
	public void shootProjectile() {

	}

	/**
	 * This method controls collision with another object in game (Actor class)
	 *
	 * @param object any object of Actor class
	 * @return true if collision happened
	 */
	public boolean collide(Actor object) {
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}
}
