package cz.cvut.fel.pjv.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Projectile class for objects which can be shot from ship with entered damage and lifespan. It is based on Actor class.
 */
public class Projectile extends Actor {
	private static final Logger LOGGER = Logger.getLogger(Projectile.class.getName());
	protected double lifeSpan;
	protected double damage;

	public Projectile(double positionX, double positionY, String spriteBound, double lifeSpan, double damage, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.lifeSpan = lifeSpan;
		this.damage = damage;
	}

	@Override
	public void update() {

	}

	/**
	 * This method puts Projectile off the screen to make it ready for next reuse.
	 */
	public void putOffScreen() {
		LOGGER.log(Level.INFO, "Projectile put off the screen");
		positionX = -1000;
		positionY = -1000;
		spriteFrame.setTranslateY(-1000);
		spriteFrame.setTranslateX(-1000);
	}

	protected void prepareForShoot(boolean right, double posX, double posY) {
		if (right) {
			spriteFrame.setScaleX(-1);
		} else {
			spriteFrame.setScaleX(1);
		}
		positionX = posX + 40;
		positionY = posY + 20;
		spriteFrame.setTranslateX(positionX);
		spriteFrame.setTranslateY(positionY);
	}

	protected void changeXPosition(double speed){
		positionX += speed;
		spriteFrame.setTranslateX(positionX);
	}
}
