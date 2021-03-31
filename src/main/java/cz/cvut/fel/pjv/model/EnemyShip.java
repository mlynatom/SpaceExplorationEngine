package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * Enemy Ship object, which can shoot projectile
 */
public class EnemyShip extends Ship {
	public EnemyShip(double iX, double iY, double velocityX, double velocityY, String spriteBound, double life, double damage, Projectile projectile, Image... spriteImage) {
		super(iX, iY, velocityX, velocityY, spriteBound, life, damage, projectile, spriteImage);
	}

	@Override
	public void update() {

	}


	@Override
	public void shootProjectile() {

	}
}
