package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;

/**
 * Abstract class for implementing player and enemy ships
 */
public abstract class Ship extends Actor {
	protected double damage;
	private double life;
	protected boolean isAlive;
	protected Projectile projectile;
	protected double velocityX, velocityY;
	protected SpaceExplorationEngine spaceExplorationEngine;

	public Ship(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX, double velocityY, String spriteBound, double life, double damage, Projectile projectile, String... imageName) {
		super(positionX, positionY, spriteBound,imageName);
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

	public  double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}
