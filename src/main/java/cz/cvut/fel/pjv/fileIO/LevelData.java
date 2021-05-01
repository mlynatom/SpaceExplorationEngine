package cz.cvut.fel.pjv.fileIO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a data structure for loading level configurations.
 * It is used for saving and loading from yaml files.
 * All public getters, setters and constructor are vital for Jackson library.
 */
public class LevelData {
	private static final Logger LOGGER = Logger.getLogger(LevelData.class.getName());
	private double gravity;
	private String backgroundImagePath;
	private String shipImagePath;
	private String shipImageEnginesOnPath;
	private int numOfFuels;
	private int numOfLevelEnhancers;
	private int numOfLifeAdders;
	private double enemyStrength;
	private double enemyProjectileDamage;
	private double enemyLife;
	private List<Coordinate2D> obstaclesPositions;
	private List<Coordinate2D> enemiesPositions;

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		if (gravity > 100) {
			this.gravity = 100;
			LOGGER.log(Level.WARNING, "Loaded value of gravity was higher than allowed. Value was set to max value.");
		} else if (gravity < 0) {
			this.gravity = 0;
			LOGGER.log(Level.WARNING, "Loaded value of gravity was lower than allowed. Value was set to min value.");
		} else {
			this.gravity = gravity;
		}
	}

	public String getBackgroundImagePath() {
		return backgroundImagePath;
	}

	public void setBackgroundImagePath(String backgroundImagePath) {
		this.backgroundImagePath = backgroundImagePath;
	}

	public String getShipImagePath() {
		return shipImagePath;
	}

	public void setShipImagePath(String shipImagePath) {
		this.shipImagePath = shipImagePath;
	}

	public String getShipImageEnginesOnPath() {
		return shipImageEnginesOnPath;
	}

	public void setShipImageEnginesOnPath(String shipImageEnginesOnPath) {
		this.shipImageEnginesOnPath = shipImageEnginesOnPath;
	}

	public int getNumOfFuels() {
		return numOfFuels;
	}

	public void setNumOfFuels(int numOfFuels) {
		if (numOfFuels > 10) {
			this.numOfFuels = 10;
			LOGGER.log(Level.WARNING, "Loaded value of number of fuels was higher than allowed. Value was set to max value.");
		} else if (numOfFuels < 0) {
			this.numOfFuels = 0;
			LOGGER.log(Level.WARNING, "Loaded value of number of fuels was lower than allowed. Value was set to min value.");
		} else {
			this.numOfFuels = numOfFuels;
		}
	}

	public int getNumOfLevelEnhancers() {
		return numOfLevelEnhancers;
	}

	public void setNumOfLevelEnhancers(int numOfLevelEnhancers) {
		if (numOfLevelEnhancers > 10) {
			this.numOfLevelEnhancers = 10;
			LOGGER.log(Level.WARNING, "Loaded value of number of level enhancers was higher than allowed. Value was set to max value.");
		} else if (numOfLevelEnhancers < 0) {
			this.numOfLevelEnhancers = 0;
			LOGGER.log(Level.WARNING, "Loaded value of number of level enhancers was lower than allowed. Value was set to min value.");
		} else {
			this.numOfLevelEnhancers = numOfLevelEnhancers;
		}
	}

	public int getNumOfLifeAdders() {
		return numOfLifeAdders;
	}

	public void setNumOfLifeAdders(int numOfLifeAdders) {
		if (numOfLifeAdders > 10) {
			this.numOfLifeAdders = 10;
			LOGGER.log(Level.WARNING, "Loaded value of number of life adders was higher than allowed. Value was set to max value.");
		} else if (numOfLifeAdders < 0) {
			this.numOfLifeAdders = 0;
			LOGGER.log(Level.WARNING, "Loaded value of number of life adders was lower than allowed. Value was set to min value.");
		} else {
			this.numOfLifeAdders = numOfLifeAdders;
		}
	}

	public double getEnemyStrength() {
		return enemyStrength;
	}

	public void setEnemyStrength(double enemyStrength) {
		if (enemyStrength > 10) {
			this.enemyStrength = 10;
			LOGGER.log(Level.WARNING, "Loaded value of enemy strength was higher than allowed. Value was set to max value.");
		} else if (enemyStrength < 0) {
			this.enemyStrength = 0;
			LOGGER.log(Level.WARNING, "Loaded value of enemy strength was lower than allowed. Value was set to min value.");
		} else {
			this.enemyStrength = enemyStrength;
		}

	}

	public double getEnemyProjectileDamage() {
		return enemyProjectileDamage;
	}

	public void setEnemyProjectileDamage(double enemyProjectileDamage) {
		if (enemyProjectileDamage > 100) {
			this.enemyProjectileDamage = 100;
			LOGGER.log(Level.WARNING, "Loaded value of enemy projectile damage was higher than allowed. Value was set to max value.");
		} else if (enemyProjectileDamage < 1) {
			this.enemyProjectileDamage = 1;
			LOGGER.log(Level.WARNING, "Loaded value of enemy projectile damage was lower than allowed. Value was set to min value.");
		} else {
			this.enemyProjectileDamage = enemyProjectileDamage;
		}
	}

	public double getEnemyLife() {
		return enemyLife;
	}

	public void setEnemyLife(double enemyLife) {
		if (enemyLife > 100) {
			this.enemyLife = 100;
			LOGGER.log(Level.WARNING, "Loaded value of enemy life was higher than allowed. Value was set to max value.");
		} else if (enemyLife < 1) {
			this.enemyLife = 1;
			LOGGER.log(Level.WARNING, "Loaded value of enemy life was lower than allowed. Value was set to min value.");
		} else {
			this.enemyLife = enemyLife;
		}
	}

	public List<Coordinate2D> getObstaclesPositions() {
		return obstaclesPositions;
	}

	public void setObstaclesPositions(List<Coordinate2D> obstaclesPositions) {
		this.obstaclesPositions = obstaclesPositions;
	}

	public List<Coordinate2D> getEnemiesPositions() {
		return enemiesPositions;
	}

	public void setEnemiesPositions(List<Coordinate2D> enemiesPositions) {
		this.enemiesPositions = enemiesPositions;
	}
}
