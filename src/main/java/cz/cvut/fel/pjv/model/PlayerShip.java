package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.*;

/**
 * This class is for player ship, the main and only playable object in game.
 */
public class PlayerShip extends Ship {
	private static final Logger LOGGER = Logger.getLogger(PlayerShip.class.getName());

	protected final double fuelConsumption;
	protected int level; // current level of ship
	protected double fuel; // amount of fuel in ship
	protected boolean isAlive;
	protected final double gravity;
	protected static final double rightBorder = WIDTH - SHIP_DIMENSIONS;
	protected static final double leftBorder = 0;
	protected static final double upBorder = 0;
	protected static final double bottomBorder = HEIGHT - SHIP_DIMENSIONS;
	protected double lastXPosition, lastYPosition;

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
		setRightImage();
		checkCollision();
		moveImage();
		spaceExplorationEngine.updateLifeOnScreen();
		spaceExplorationEngine.updateLevelOnScreen();
	}

	@Override
	public void shootProjectile() {

	}

	protected void getNewCoordinates() {
		lastXPosition = positionX;
		lastYPosition = positionY;

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

	protected void applyGravity() {
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

	protected void checkCollision() {
		for (int i = 0; i < spaceExplorationEngine.getCastingDirector().getCurrentActors().size(); i++) {
			Actor object = spaceExplorationEngine.getCastingDirector().getCurrentActors().get(i);
			if (collide(object)) {
				LOGGER.log(Level.FINE, PlayerShip.class.getName() + "collided with " + object.getClass().getName());
				handleCollision(object);
				spaceExplorationEngine.getCastingDirector().resetRemovedActors();
			}
		}
	}

	protected void handleCollision(Actor object) {
		if (object instanceof FuelBarrel) {
			spaceExplorationEngine.getCastingDirector().addToRemovedActors(object);
			spaceExplorationEngine.removeActorFromRoot(object);
			addFuel(((FuelBarrel) object).amountOfFuelToAdd);
			LOGGER.log(Level.INFO, "Fuel added");
		} else if (object instanceof LevelEnhancer) {
			spaceExplorationEngine.getCastingDirector().addToRemovedActors(object);
			spaceExplorationEngine.removeActorFromRoot(object);
			LOGGER.log(Level.INFO, "Level added");
			addLevel(((LevelEnhancer) object).amountOfLevelToAdd);
		} else if (object instanceof LifeAdder) {
			spaceExplorationEngine.getCastingDirector().addToRemovedActors(object);
			spaceExplorationEngine.removeActorFromRoot(object);
			LOGGER.log(Level.INFO, "Life added");
			addLife(((LifeAdder) object).lifeToAdd);
		} else if (object instanceof Obstacle) {
			decreaseLife(((Obstacle) object).damage);
			recoverPosition(object);
		} else if (object instanceof EnemyShip) {
			decreaseLife(((EnemyShip) object).damage);
			recoverPosition(object);
		} else if (object instanceof Projectile) {
			decreaseLife(((Projectile) object).damage);
			((Projectile) object).putOffScreen();
		}
	}

	protected void addFuel(double fuelToAdd) {
		if (fuel + fuelToAdd > 100) {
			fuel = 100;
		} else {
			fuel += fuelToAdd;
		}
	}

	protected void addLevel(int levelToAdd) {
		if (level + levelToAdd > 10) {
			level = 10;
		} else {
			level += levelToAdd;
		}
	}

	protected void addLife(double lifeToAdd) {
		if (life + lifeToAdd > 100) {
			life = 100;
		} else {
			life += lifeToAdd;
		}
	}

	protected void decreaseLife(double lifeToSubtract) {
		if (life - lifeToSubtract <= 0) {
			spaceExplorationEngine.callEndGame();
		} else {
			life -= lifeToSubtract;
		}
	}

	protected void recoverPosition(Actor object) {
		if (positionY > object.positionY) {
			positionY = lastYPosition + 0.1;
		} else if (positionY < object.positionY) {
			positionY = lastYPosition - 0.1;
		}

		if (positionX < object.positionX) {
			positionX = lastXPosition - 0.1;
		} else if (positionX > object.positionX) {
			positionX = lastXPosition + 0.1;
		}
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
