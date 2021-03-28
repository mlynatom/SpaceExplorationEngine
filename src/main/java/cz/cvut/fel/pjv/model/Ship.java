package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * Abstract class for implementing player and enemy ships
 */
public abstract class Ship extends Actor {
	protected double life, damage;
	protected boolean isAlive;
	protected Projectile projectile;

	public Ship(double iX, double iY, String spriteBound, double life, double damage, boolean isAlive, Projectile projectile, Image... spriteImage) {
		super(iX, iY, spriteBound, spriteImage);
		this.life = life;
		this.damage = damage;
		this.isAlive = isAlive;
		this.projectile = projectile;
	}

	@Override
	public abstract void update();

	/**
	 * This method shoots projectile with entered damage
	 */
	public abstract void shootProjectile();
}
