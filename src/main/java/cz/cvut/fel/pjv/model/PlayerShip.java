package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import javafx.scene.image.Image;

import static cz.cvut.fel.pjv.controller.Constants.*;

/**
 * This class is for player ship, the main and only playable object in game.
 */
public class PlayerShip extends Ship {
	private int level; // current level of ship
	private double fuel; // amount of fuel in ship
	private boolean isAlive;
	private final double gravity;
	private static final double rightBorder = WIDTH - SHIP_DIMENSIONS;
	private static final double leftBorder = 0;
	private static final double upBorder = 0;
	private static final double bottomBorder = HEIGHT - SHIP_DIMENSIONS;

	public PlayerShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, String spriteBound,
					  Projectile projectile, PlayerData playerData, double gravity, Image... spriteImage) {
		super(spaceExplorationEngine, positionX, positionY, 2, 3, spriteBound, playerData.getShipLife(), 10, projectile, spriteImage);
		this.level = playerData.getShipLevel();
		this.fuel = playerData.getShipFuel();
		this.isAlive = true;
		this.gravity = gravity;
	}

	@Override
	public void update() {
		getNewCoordinates();
		applyGravity();
		checkBorders();
		moveImage();
		setRightImage();
	}

	@Override
	public void shootProjectile() {

	}

	private void getNewCoordinates() {
		if (spaceExplorationEngine.isLeft()) {
			positionX -= velocityX;
		}
		if (spaceExplorationEngine.isRight()) {
			positionX += velocityX;
		}
		if (spaceExplorationEngine.isUp()) {
			positionY -= velocityY;
		}
	}

	private void checkBorders() {
		if (positionX > rightBorder) {
			positionX = rightBorder;
		} else if (positionX < leftBorder) {
			positionX = leftBorder;
		}

		if (positionY < upBorder) {
			positionY = upBorder;
		} else if (positionY > bottomBorder) {
			positionY = bottomBorder;
		}
	}

	private void applyGravity() {
		positionY += gravity;
	}

	private void moveImage() {
		spriteFrame.setTranslateX(positionX);
		spriteFrame.setTranslateY(positionY);
	}

	private void setRightImage() {
		if (spaceExplorationEngine.isUp()) {
			if (spaceExplorationEngine.isRight()) {
				spriteFrame.setRotate(30);
			} else if (spaceExplorationEngine.isLeft()) {
				spriteFrame.setRotate(-30);
			} else {
				spriteFrame.setRotate(0);
			}
			spriteFrame.setImage(imageList.get(1));
		} else {
			if (spaceExplorationEngine.isLeft()) {
				spriteFrame.setRotate(-30);
				spriteFrame.setImage(imageList.get(0));
			} else if (spaceExplorationEngine.isRight()) {
				spriteFrame.setRotate(30);
				spriteFrame.setImage(imageList.get(0));
			} else {
				spriteFrame.setRotate(0);
				spriteFrame.setImage(imageList.get(0));
			}
		}

	}

	/**
	 * This method controls collision with another object in game (Actor class)
	 *
	 * @param object any object of Actor class
	 * @return true if collision happened
	 */
	public boolean collide(Actor object) {
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}
}
