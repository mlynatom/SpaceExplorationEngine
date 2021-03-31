package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * Abstract class for implementing player and enemy ships
 */
public abstract class Ship extends Actor {
	protected double life, damage;
	protected boolean isAlive;
	protected Projectile projectile;
	protected double velocityX, velocityY;

	public Ship(double iX, double iY, double velocityX, double velocityY, String spriteBound, double life, double damage, Projectile projectile, Image... spriteImage) {
		super(iX, iY, spriteBound, spriteImage);
		this.life = life;
		this.damage = damage;
		this.projectile = projectile;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		isAlive = true;
	}

	@Override
	public abstract void update();

	/**
	 * This method shoots projectile with entered damage
	 */
	public abstract void shootProjectile();
}
