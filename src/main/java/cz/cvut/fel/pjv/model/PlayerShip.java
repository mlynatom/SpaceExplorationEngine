package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.view.SpaceExplorationEngine;
import javafx.scene.image.Image;

/**
 * This class is for player ship, the main and only playable object in game.
 */
public class PlayerShip extends Ship {
	private int level; // current level of ship
	private double fuel; // amount of fuel in ship
	private boolean isAlive;
	private double gravity;
	private static double rightBorder;
	private static final double leftBorder = 0;
	private static final double upBorder = 0;
	private static double bottomBorder;

	public PlayerShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, String spriteBound, double life, double damage, boolean isAlive, Projectile projectile, int level, double fuel, Image... spriteImage) {
		super(spaceExplorationEngine, positionX, positionY, 2, 3, spriteBound, life, damage, projectile, spriteImage);
		this.level = level;
		this.fuel = fuel;
		this.isAlive = isAlive;
		gravity = 0.3;
		rightBorder = SpaceExplorationEngine.getWIDTH() - spaceExplorationEngine.getPlayerShipImageDimension();
		bottomBorder = SpaceExplorationEngine.getHEIGHT() - spaceExplorationEngine.getPlayerShipImageDimension();
	}

	@Override
	public void update() {
		getNewCoordinates();
		applyGravity();
		checkBorders();
		moveImage();
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
