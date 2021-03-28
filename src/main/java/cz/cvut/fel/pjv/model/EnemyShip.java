package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;

/**
 * Enemy Ship object, which can shoot projectile
 */
public class EnemyShip extends Ship {
	public EnemyShip(double iX, double iY, String spriteBound, double life, double damage, boolean isAlive, Projectile projectile, Image... spriteImage) {
		super(iX, iY, spriteBound, life, damage, isAlive, projectile, spriteImage);
	}

	@Override
	public void update() {

	}


	@Override
	public void shootProjectile() {

	}
}
