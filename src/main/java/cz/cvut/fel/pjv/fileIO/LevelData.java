package cz.cvut.fel.pjv.fileIO;

import java.util.List;

public class LevelData {
	private double gravity;
	private String backgroundImagePath;
	private String shipImagePath;
	private String shipImageRotatedLeftPath;
	private int numOfFuels;
	private int numOfLevelEnhancers;
	private int numOfLifeAdders;
	private double enemyStrength;
	private double enemyLife;
	private List<coordinate2D> obstaclesPositions;
	private List<coordinate2D> enemiesPositions;

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
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

	public String getShipImageRotatedLeftPath() {
		return shipImageRotatedLeftPath;
	}

	public void setShipImageRotatedLeftPath(String shipImageRotatedLeftPath) {
		this.shipImageRotatedLeftPath = shipImageRotatedLeftPath;
	}

	public int getNumOfFuels() {
		return numOfFuels;
	}

	public void setNumOfFuels(int numOfFuels) {
		this.numOfFuels = numOfFuels;
	}

	public int getNumOfLevelEnhancers() {
		return numOfLevelEnhancers;
	}

	public void setNumOfLevelEnhancers(int numOfLevelEnhancers) {
		this.numOfLevelEnhancers = numOfLevelEnhancers;
	}

	public int getNumOfLifeAdders() {
		return numOfLifeAdders;
	}

	public void setNumOfLifeAdders(int numOfLifeAdders) {
		this.numOfLifeAdders = numOfLifeAdders;
	}

	public double getEnemyStrength() {
		return enemyStrength;
	}

	public void setEnemyStrength(double enemyStrength) {
		this.enemyStrength = enemyStrength;
	}

	public double getEnemyLife() {
		return enemyLife;
	}

	public void setEnemyLife(double enemyLife) {
		this.enemyLife = enemyLife;
	}

	public List<coordinate2D> getObstaclesPositions() {
		return obstaclesPositions;
	}

	public void setObstaclesPositions(List<coordinate2D> obstaclesPositions) {
		this.obstaclesPositions = obstaclesPositions;
	}

	public List<coordinate2D> getEnemiesPositions() {
		return enemiesPositions;
	}

	public void setEnemiesPositions(List<coordinate2D> enemiesPositions) {
		this.enemiesPositions = enemiesPositions;
	}
}
