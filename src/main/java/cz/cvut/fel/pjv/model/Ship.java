package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.view.SpaceExplorationEngine;
import javafx.scene.image.Image;

/**
 * Abstract class for implementing player and enemy ships
 */
public abstract class Ship extends Actor {
	protected double damage;
	private int life;
	protected boolean isAlive;
	protected Projectile projectile;
	protected double velocityX, velocityY;
	protected SpaceExplorationEngine spaceExplorationEngine;

	public Ship(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX, double velocityY, String spriteBound, int life, double damage, Projectile projectile, Image... spriteImage) {
		super(positionX, positionY, spriteBound, spriteImage);
		this.life = life;
		this.damage = damage;
		this.projectile = projectile;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		isAlive = true;
		this.spaceExplorationEngine = spaceExplorationEngine;
	}

	@Override
	public abstract void update();

	/**
	 * This method shoots projectile with entered damage
	 */
	public abstract void shootProjectile();

	public int getLife() {
		return life;
	}
}
