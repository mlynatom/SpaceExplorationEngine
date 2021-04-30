package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {
	private final SpaceExplorationEngine mockSpaceExplorationEngine;
	private final Projectile mockProjectile;
	private final PlayerData mockPlayerData;
	private final double mockPositionX = 10;
	private final double mockPositionY = 10;
	private final String mockSpriteBound = "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z";
	private final double mockGravity = 0.1;
	private final String mockImageName = "/projectile.png";
	private final int mockFuel = 100;

	public ShipTest() {
		mockSpaceExplorationEngine = new SpaceExplorationEngine();
		double mockLifeSpan = 10;
		double mockDamage = 50;
		mockProjectile = new Projectile(mockPositionX, mockPositionY, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", mockLifeSpan, mockDamage, mockImageName);
		mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(mockFuel);
		mockPlayerData.setShipLevel(1);
		mockPlayerData.setShipLife(100);
		mockPlayerData.setFuelConsumption(0.1);
	}

	@Test
	public void testCollideTrue() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		assertTrue(mockShip.collide(mockObject));
	}

	@Test
	public void testCollideFalse() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX + 100, mockPositionY + 100, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		assertFalse(mockShip.collide(mockObject));
	}

	@Test
	public void testCheckBordersRight() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockShip.setPositionX(PlayerShip.rightBorder + 1);

		mockShip.checkBorders();
		assertEquals(Ship.rightBorder, mockShip.positionX);
	}

	@Test
	public void testCheckBordersLeft() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockShip.setPositionX(PlayerShip.leftBorder - 1);
		mockShip.checkBorders();
		assertEquals(Ship.leftBorder, mockShip.positionX);

	}

	@Test
	public void testCheckBordersUp() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockShip.setPositionY(PlayerShip.upBorder - 1);

		mockShip.checkBorders();
		assertEquals(Ship.upBorder, mockShip.positionY);
	}

	@Test
	public void testCheckBordersBottom() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockShip.setPositionY(PlayerShip.bottomBorder + 1);

		mockShip.checkBorders();
		assertEquals(PlayerShip.bottomBorder, mockShip.positionY);

	}

	@Test
	public void testMoveSpriteFrame() {
		Ship mockShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockShip.positionX = mockPositionX + 10;
		mockShip.positionY = mockPositionY + 10;
		mockShip.moveSpriteFrame();
		assertEquals(mockPositionX + 10, mockShip.spriteFrame.getTranslateX());
		assertEquals(mockPositionY + 10, mockShip.spriteFrame.getTranslateY());
	}
}