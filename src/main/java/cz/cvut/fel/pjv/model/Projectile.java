package cz.cvut.fel.pjv.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.POS_OFF_SCREEN;

/**
 * This class creates projectile actor which can be assigned to player and enemy. It flies during lifeSpan and
 * cause entered damage.
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
		LOGGER.log(Level.FINE, "Projectile put off the screen");
		positionX = POS_OFF_SCREEN;
		positionY = POS_OFF_SCREEN;
		updateSpriteFramePositions();
	}

	/**
	 * This method change position of projectile to the shoot location and choose direction of image
	 *
	 * @param right if true direction of image is to right, else to left
	 * @param posX  position to change
	 * @param posY  position to change
	 */
	protected void prepareForShoot(boolean right, double posX, double posY) {
		LOGGER.log(Level.FINE, "Projectile prepared for shoot.");
		if (right) {
			spriteFrame.setScaleX(-1);
		} else {
			spriteFrame.setScaleX(1);
		}
		positionX = posX + 30; // changed slightly to be on the center of ship
		positionY = posY + 20; // changed slightly to be on the center of ship
		updateSpriteFramePositions();
	}

	/**
	 * This method changes X position according to "speed"
	 *
	 * @param speed if > 0 goes to the right, if < 0 goes to the left
	 */
	protected void changeXPosition(double speed) {
		positionX += speed;
		spriteFrame.setTranslateX(positionX);
	}

	private void updateSpriteFramePositions() {
		spriteFrame.setTranslateX(positionX);
		spriteFrame.setTranslateY(positionY);
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
}
