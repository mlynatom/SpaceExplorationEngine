package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyShipTest {
	private final SpaceExplorationEngine mockSpaceExplorationEngine = new SpaceExplorationEngine();
	private final double mockPositionX = 10, mockPositionY = 10;
	private final double mockVelocityX = 1, mockVelocityY = 1;
	private final String mockSpriteBound = "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z";
	private final double mockLife = 100, mockDamage = 1, mockLifeSpan = 100;
	private final String mockImageName = "mockName";
	private final Projectile mockProjectile = new Projectile(mockPositionX, mockPositionY, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", mockLifeSpan, mockDamage, mockImageName);

	@Test
	void testShootProjectileDo() {
		EnemyShip mockEnemyShip = new EnemyShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockVelocityX, mockVelocityY, mockSpriteBound, mockLife, mockDamage, mockProjectile, mockImageName);
		mockEnemyShip.timeCounter = 200;
		mockEnemyShip.projectileCounter = 0;
		mockEnemyShip.shootProjectile();
		assertTrue(mockEnemyShip.projectileShot);
		assertEquals(1, mockEnemyShip.timeCounter);
		assertEquals(1,mockEnemyShip.projectileCounter);
	}

	@Test
	void testShootProjectile() {
		EnemyShip mockEnemyShip = new EnemyShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockVelocityX, mockVelocityY, mockSpriteBound, mockLife, mockDamage, mockProjectile, mockImageName);
		mockEnemyShip.timeCounter = 50;
		mockEnemyShip.projectileCounter = 0;
		mockEnemyShip.shootProjectile();
		assertFalse(mockEnemyShip.projectileShot);
		assertEquals(51, mockEnemyShip.timeCounter);
		assertEquals(0,mockEnemyShip.projectileCounter);
	}

	@Test
	void testDecreaseLife() {
		EnemyShip mockEnemyShip = new EnemyShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockVelocityX, mockVelocityY, mockSpriteBound, mockLife, mockDamage, mockProjectile, mockImageName);
		mockEnemyShip.decreaseLife(10);
		assertEquals(mockLife-10, mockEnemyShip.life);
	}

	@Test
	void testMoveUp() {
		EnemyShip mockEnemyShip = new EnemyShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockVelocityX, mockVelocityY, mockSpriteBound, mockLife, mockDamage, mockProjectile, mockImageName);
		mockEnemyShip.moveUp = true;
		mockEnemyShip.move();
		assertEquals(mockPositionY - mockVelocityY, mockEnemyShip.positionY);
	}

	@Test
	void testMoveDown() {
		EnemyShip mockEnemyShip = new EnemyShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockVelocityX, mockVelocityY, mockSpriteBound, mockLife, mockDamage, mockProjectile, mockImageName);
		mockEnemyShip.moveUp = false;
		mockEnemyShip.move();
		assertEquals(mockPositionY + mockVelocityY, mockEnemyShip.positionY);
	}
}