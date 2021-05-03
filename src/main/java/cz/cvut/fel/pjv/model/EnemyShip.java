package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enemy ship actor which moves automatically up and down and shoots projectiles.
 */
public class EnemyShip extends Ship {
	private static final Logger LOGGER = Logger.getLogger(EnemyShip.class.getName());
	protected int projectileCounter = 0;
	protected int timeCounter = 10;
	protected int moveCounter = 10;
	protected boolean projectileShot = false;
	protected boolean isAlive = true;
	protected boolean moveUp = true;
	private final double initPosY;

	public EnemyShip(SpaceExplorationEngine spaceExplorationEngine, double positionX, double positionY, double velocityX,
					 double velocityY, String spriteBound, double life, double damage, Projectile projectile, String... imageName) {
		super(spaceExplorationEngine, positionX, positionY, velocityX, velocityY, spriteBound, life, damage, projectile, imageName);
		initPosY = positionY;
	}

	@Override
	public void update() {
		if (isAlive) {
			shootProjectile();
			checkCollision();
			move();
			checkBorders();
			moveSpriteFrame();
		}
	}

	@Override
	protected void shootProjectile() {
		//if time counter from previous shot greater than 100 and previous shot is not visible
		if (timeCounter > 100 && !projectileShot) {
			projectileShot = true;
			projectile.prepareForShoot(false, positionX, positionY);
			timeCounter = 0;
		}
		//if projectile was shot, make it fly
		if (projectileShot) {
			//projectile fly for certain then reset
			if (projectileCounter < projectile.lifeSpan && projectile.positionX > -10) {
				projectile.changeXPosition(-2);
				projectileCounter++;
			} else {
				projectile.putOffScreen();
				projectileCounter = 0;
				projectileShot = false;
			}
		}
		timeCounter++;
	}

	@Override
	protected void checkCollision() {
		for (int i = 0; i < spaceExplorationEngine.getCastingDirector().getCollisionActorsEnemy().size(); i++) {
			Actor object = spaceExplorationEngine.getCastingDirector().getCollisionActorsEnemy().get(i);
			LOGGER.log(Level.FINE, EnemyShip.class.getName() + "collided with " + object.getClass().getName());
			if (collide(object)) {
				handleCollision(object);
			}
		}
	}

	@Override
	protected void handleCollision(Actor object) {
		if (object instanceof PlayerShip) {
			decreaseLife(((PlayerShip) object).damage);
		} else if (object instanceof Projectile) {
			decreaseLife(((Projectile) object).damage);
			((Projectile) object).putOffScreen();
		}
	}

	/**
	 * This method decrease damage if possible. If life drops to/below zero it removes this actor and his projectile.
	 *
	 * @param damage damage which decreases life.
	 */
	protected void decreaseLife(double damage) {
		if (life - damage <= 0) {
			spaceExplorationEngine.getCastingDirector().addToPlayerRemovedActors(this);
			spaceExplorationEngine.removeActorFromRoot(this);
			spaceExplorationEngine.getCastingDirector().addToPlayerRemovedActors(projectile);
			spaceExplorationEngine.removeActorFromRoot(projectile);
			spaceExplorationEngine.getCastingDirector().resetPlayerRemovedActors();
			isAlive = false;
		} else {
			life -= damage;
		}
	}

	/**
	 * This method provides basic movement periodically up and down.
	 */
	protected void move() {
		if (moveUp) {
			if (positionY - velocityY > initPosY - 50){
				positionY -= velocityY;
			}

		} else {
			if (positionY + velocityY < initPosY + 50)
			positionY += velocityY;
		}
		checkChangeDirection();
	}

	private void checkChangeDirection() {
		moveCounter--;
		if (moveCounter == 0) {
			moveUp = !moveUp;
			moveCounter = ThreadLocalRandom.current().nextInt(10, 100);
		}
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
