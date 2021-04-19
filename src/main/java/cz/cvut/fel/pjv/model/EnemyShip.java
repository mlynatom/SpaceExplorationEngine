package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.view.SpaceExplorationEngine;
import javafx.scene.image.Image;

/**
 * Enemy Ship object, which can shoot projectile
 */
public class EnemyShip extends Ship {
	public EnemyShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX, double velocityY, String spriteBound, double life, double damage, Projectile projectile, Image... spriteImage) {
		super(spaceExplorationEngine,positionX, positionY, velocityX, velocityY, spriteBound, life, damage, projectile, spriteImage);
	}

	@Override
	public void update() {

	}


	@Override
	public void shootProjectile() {

	}
}
