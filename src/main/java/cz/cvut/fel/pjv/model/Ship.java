package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import static cz.cvut.fel.pjv.controller.Constants.*;
import static cz.cvut.fel.pjv.controller.Constants.SHIP_DIMENSIONS;

/**
 * Abstract class for implementing player and enemy ships
 */
public abstract class Ship extends Actor {
	protected double damage;
	protected double life;
	protected boolean isAlive;
	protected Projectile projectile;
	protected double velocityX, velocityY;
	protected SpaceExplorationEngine spaceExplorationEngine;
	protected static final double rightBorder = WIDTH - SHIP_DIMENSIONS;
	protected static final double leftBorder = 0;
	protected static final double upBorder = 0;
	protected static final double bottomBorder = HEIGHT - SHIP_DIMENSIONS;

	public Ship(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX, double velocityY, String spriteBound, double life, double damage, Projectile projectile, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
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
	protected abstract void shootProjectile();

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	/**
	 * This method controls collision with another object in game (Actor class)
	 *
	 * @param object any object of Actor class
	 * @return true if collision happened
	 */
	protected boolean collide(Actor object) {
		if (spriteFrame.getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
			Shape intersection = SVGPath.intersect(spriteBound, object.spriteBound);
			return intersection.getBoundsInLocal().getWidth() != -1;
		}
		return false;
	}

	protected abstract void handleCollision(Actor object);

	protected abstract void checkCollision();
}
