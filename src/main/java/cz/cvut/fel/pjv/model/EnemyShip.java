package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;

/**
 * Enemy Ship object, which can shoot projectile
 */
public class EnemyShip extends Ship {
	public EnemyShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX,
					 double velocityY, String spriteBound, double life, double damage, Projectile projectile, String... imageName) {
		super(spaceExplorationEngine, positionX, positionY, velocityX, velocityY, spriteBound, life, damage, projectile, imageName);
	}

	@Override
	public void update() {

	}

	@Override
	public void shootProjectile() {

	}
}
