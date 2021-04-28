package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;

import static cz.cvut.fel.pjv.controller.Constants.*;

/**
 * This class is for player ship, the main and only playable object in game.
 */
public class PlayerShip extends Ship {
	protected final double fuelConsumption;
	protected int level; // current level of ship
	protected double fuel; // amount of fuel in ship
	protected boolean isAlive;
	protected final double gravity;
	protected static final double rightBorder = WIDTH - SHIP_DIMENSIONS;
	protected static final double leftBorder = 0;
	protected static final double upBorder = 0;
	protected static final double bottomBorder = HEIGHT - SHIP_DIMENSIONS;

	public PlayerShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, String spriteBound,
					  Projectile projectile, PlayerData playerData, double gravity, String... imageName) {
		super(spaceExplorationEngine, positionX, positionY, SHIP_VELOCITY_X, SHIP_VELOCITY_Y, spriteBound, playerData.getShipLife(), 10, projectile, imageName);
		this.level = playerData.getShipLevel();
		this.fuel = playerData.getShipFuel();
		this.fuelConsumption = playerData.getFuelConsumption();
		this.isAlive = true;
		this.gravity = gravity;
	}

	@Override
	public void update() {
		getNewCoordinates();
		spaceExplorationEngine.updateFuelOnScreen();
		applyGravity();
		checkBorders();
		moveImage();
		setRightImage();
	}

	@Override
	public void shootProjectile() {

	}

	protected void getNewCoordinates() {
		if (spaceExplorationEngine.isLeft()) {
			positionX -= velocityX;
		}
		if (spaceExplorationEngine.isRight()) {
			positionX += velocityX;
		}
		if (spaceExplorationEngine.isUp() && fuel > 0) {
			positionY -= velocityY;
			fuel -= fuelConsumption;
			if (fuel <= 0) {
				fuel = 0; //crop fuel to never be less than zero
			}
		}
	}

	protected void checkBorders() {
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

	protected void  applyGravity() {
		positionY += gravity;
	}

	protected void moveImage() {
		spriteFrame.setTranslateX(positionX);
		spriteFrame.setTranslateY(positionY);
	}

	protected void setRightImage() {
		if (spaceExplorationEngine.isUp() && fuel > 0) {
			if (spaceExplorationEngine.isRight()) {
				spriteFrame.setRotate(30);
			} else if (spaceExplorationEngine.isLeft()) {
				spriteFrame.setRotate(-30);
			} else {
				spriteFrame.setRotate(0);
			}
			spriteFrame.setImage(spaceExplorationEngine.getImageDirector().getImage(imageNameList.get(1)));
		} else {
			if (spaceExplorationEngine.isRight()) {
				spriteFrame.setRotate(30);
			} else if (spaceExplorationEngine.isLeft()) {
				spriteFrame.setRotate(-30);
			} else {
				spriteFrame.setRotate(0);
			}
			spriteFrame.setImage(spaceExplorationEngine.getImageDirector().getImage(imageNameList.get(0)));
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
