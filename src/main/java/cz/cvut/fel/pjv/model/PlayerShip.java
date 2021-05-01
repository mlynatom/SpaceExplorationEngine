package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;

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
	protected double lastXPosition, lastYPosition;
	protected boolean projectileShot = false;
	protected int counterProjectile = 0;

	public PlayerShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, String spriteBound,
					  Projectile projectile, PlayerData playerData, double gravity, String... imageName) {
		super(spaceExplorationEngine, positionX, positionY, SHIP_VELOCITY_X, SHIP_VELOCITY_Y, spriteBound, playerData.getShipLife(), 0.1, projectile, imageName);
		this.level = playerData.getShipLevel();
		this.fuel = playerData.getShipFuel();
		this.fuelConsumption = playerData.getFuelConsumption();
		this.isAlive = true;
		this.gravity = gravity;
	}

	@Override
	public void update() {
		if (isAlive) {
			getNewCoordinates();
			spaceExplorationEngine.updateFuelOnScreen();
			applyGravity();
			checkBorders();
			checkForFinish();
			setRightImage();
			checkCollision();
			moveSpriteFrame();
			shootProjectile();
			spaceExplorationEngine.updateLifeOnScreen();
			spaceExplorationEngine.updateLevelOnScreen();
		}
	}

	@Override
	protected void shootProjectile() {
		if (spaceExplorationEngine.isSpace() && !projectileShot) {
			projectileShot = true;
			projectile.prepareForShoot(true, positionX, positionY);
		}
		if (projectileShot) {
			if (counterProjectile < projectile.lifeSpan && projectile.positionX < WIDTH + 10 && projectile.positionX != POS_OFF_SCREEN) {
				projectile.changeXPosition(2);
				counterProjectile++;
			} else {
				projectile.putOffScreen();
				counterProjectile = 0;
				projectileShot = false;
			}
		}
	}

	protected void getNewCoordinates() {
		lastXPosition = positionX;
		lastYPosition = positionY;

		if (spaceExplorationEngine.isLeft() && positionY != DEFAULT_SHIP_Y_POSITION) {
			positionX -= velocityX;
		}
		if (spaceExplorationEngine.isRight() && positionY != DEFAULT_SHIP_Y_POSITION) {
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

	protected void applyGravity() {
		positionY += gravity;
	}

	protected void checkForFinish() {
		if (positionY == DEFAULT_SHIP_Y_POSITION && positionX == FINISH_LOCATION_X) {
			spaceExplorationEngine.callEndGame();
		}
	}

	protected void setRightImage() {
		if (spaceExplorationEngine.isUp() && fuel > 0) {
			if (spaceExplorationEngine.isRight() && positionY != DEFAULT_SHIP_Y_POSITION) {
				spriteFrame.setRotate(30);
			} else if (spaceExplorationEngine.isLeft() && positionY != DEFAULT_SHIP_Y_POSITION) {
				spriteFrame.setRotate(-30);
			} else {
				spriteFrame.setRotate(0);
			}
			spriteFrame.setImage(spaceExplorationEngine.getImageDirector().getImage(imageNameList.get(1)));
		} else {
			if (spaceExplorationEngine.isRight() && positionY != DEFAULT_SHIP_Y_POSITION) {
				spriteFrame.setRotate(30);
			} else if (spaceExplorationEngine.isLeft() && positionY != DEFAULT_SHIP_Y_POSITION) {
				spriteFrame.setRotate(-30);
			} else {
				spriteFrame.setRotate(0);
			}
			spriteFrame.setImage(spaceExplorationEngine.getImageDirector().getImage(imageNameList.get(0)));
		}

	}

	@Override
	protected void checkCollision() {
		for (int i = 0; i < spaceExplorationEngine.getCastingDirector().getCollisionPlayerActors().size(); i++) {
			Actor object = spaceExplorationEngine.getCastingDirector().getCollisionPlayerActors().get(i);
			if (collide(object)) {
				LOGGER.log(Level.FINE, PlayerShip.class.getName() + "collided with " + object.getClass().getName());
				handleCollision(object);
				spaceExplorationEngine.getCastingDirector().resetPlayerRemovedActors();
			}
		}
	}

	@Override
	protected void handleCollision(Actor object) {
		if (object instanceof FuelBarrel) {
			spaceExplorationEngine.getCastingDirector().addToPlayerRemovedActors(object);
			spaceExplorationEngine.removeActorFromRoot(object);
			addFuel(((FuelBarrel) object).amountOfFuelToAdd);
			LOGGER.log(Level.INFO, "Fuel added");
		} else if (object instanceof LevelEnhancer) {
			spaceExplorationEngine.getCastingDirector().addToPlayerRemovedActors(object);
			spaceExplorationEngine.removeActorFromRoot(object);
			LOGGER.log(Level.INFO, "Level added");
			addLevel(((LevelEnhancer) object).amountOfLevelToAdd);
		} else if (object instanceof LifeAdder) {
			spaceExplorationEngine.getCastingDirector().addToPlayerRemovedActors(object);
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
			damage += 0.1;
			projectile.damage += 10;
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
			isAlive = false;
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
