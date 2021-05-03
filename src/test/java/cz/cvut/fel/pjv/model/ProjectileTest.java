package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import org.junit.jupiter.api.Test;

import static cz.cvut.fel.pjv.controller.Constants.POS_OFF_SCREEN;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {
	private final double mockPositionX = 10;
	private final double mockPositionY = 10;
	private final String mockSpriteBound = "M0 6 L0 5 70 93 115 45 115 0 84 0 68 16 Z";
	private final double mockLifeSpan = 100;
	private final double mockDamage = 50;
	private final String mockImageName = "/projectile.png";
	private final SpaceExplorationEngine mockSpaceExplorationEngine = new SpaceExplorationEngine();

	@Test
	void testPutOffScreen() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		mockProjectile.positionX = mockPositionX;
		mockProjectile.positionY = mockPositionY;
		mockProjectile.putOffScreen();
		assertEquals(POS_OFF_SCREEN, mockProjectile.positionX);
		assertEquals(POS_OFF_SCREEN, mockProjectile.positionY);
		assertEquals(POS_OFF_SCREEN, mockProjectile.spriteFrame.getTranslateX());
		assertEquals(POS_OFF_SCREEN, mockProjectile.spriteFrame.getTranslateY());
	}

	@Test
	void prepareForShootRight() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		mockProjectile.positionX = mockPositionX;
		mockProjectile.positionY = mockPositionY;
		mockProjectile.prepareForShoot(true, mockPositionX, mockPositionY);
		assertEquals(mockPositionX + 30, mockProjectile.positionX);
		assertEquals(mockPositionY + 20, mockProjectile.positionY);
		assertEquals(mockPositionX + 30, mockProjectile.spriteFrame.getTranslateX());
		assertEquals(mockPositionY + 20, mockProjectile.spriteFrame.getTranslateY());
		assertEquals(-1, mockProjectile.spriteFrame.getScaleX());
	}

	@Test
	void prepareForShootLeft() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		mockProjectile.positionX = mockPositionX;
		mockProjectile.positionY = mockPositionY;
		mockProjectile.prepareForShoot(false, mockPositionX, mockPositionY);
		assertEquals(mockPositionX + 30, mockProjectile.positionX);
		assertEquals(mockPositionY + 20, mockProjectile.positionY);
		assertEquals(mockPositionX + 30, mockProjectile.spriteFrame.getTranslateX());
		assertEquals(mockPositionY + 20, mockProjectile.spriteFrame.getTranslateY());
		assertEquals(1, mockProjectile.spriteFrame.getScaleX());
	}

	@Test
	void changeXPosition() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		mockProjectile.positionX = mockPositionX;
		mockProjectile.changeXPosition(2);
		assertEquals(mockPositionX + 2, mockProjectile.positionX);
		assertEquals(mockPositionX + 2, mockProjectile.spriteFrame.getTranslateX());
	}

	@Test
	public void testCollideTrue() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		assertTrue(mockProjectile.collide(mockObject));
	}

	@Test
	public void testCollideFalse() {
		Projectile mockProjectile = new Projectile(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockLifeSpan, mockDamage, mockImageName);
		Actor mockObject = new Actor(mockPositionX + 100, mockPositionY + 100, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		assertFalse(mockProjectile.collide(mockObject));
	}
}