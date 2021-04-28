package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerShipTest {

	private final SpaceExplorationEngine mockSpaceExplorationEngine;
	private final Projectile mockProjectile;
	private final PlayerData mockPlayerData;
	private final double mockPositionX = 10;
	private final double mockPositionY = 10;
	private final String mockSpriteBound = "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z";
	private final double mockGravity = 0.1;
	private final String mockImageName = "/projectile.png";
	private final int mockFuel = 100;

	public PlayerShipTest() {
		mockSpaceExplorationEngine = new SpaceExplorationEngine();
		double mockLifeSpan = 10;
		mockProjectile = new Projectile(mockPositionX, mockPositionY, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", mockLifeSpan, mockImageName);
		mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(mockFuel);
		mockPlayerData.setShipLevel(1);
		mockPlayerData.setShipLife(100);
		mockPlayerData.setFuelConsumption(0.1);
	}

	@Test
	public void testGetNewCoordinatesLeft() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(true);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(false);

		playerShip.getNewCoordinates();
		assertEquals(mockPositionX - playerShip.velocityX, playerShip.positionX);
	}

	@Test
	public void testGetNewCoordinatesRight() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(true);
		mockSpaceExplorationEngine.setUp(false);

		playerShip.getNewCoordinates();
		assertEquals(mockPositionX + playerShip.velocityX, playerShip.positionX);
	}

	@Test
	public void testGetNewCoordinatesUp() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(true);

		playerShip.getNewCoordinates();
		assertEquals(mockPositionY - playerShip.velocityY, playerShip.positionY);
		assertEquals(mockFuel - playerShip.fuelConsumption, playerShip.getFuel());
	}

	@Test
	public void testGetNewCoordinatesUpLowFuel() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(true);
		playerShip.setFuel(0);

		playerShip.getNewCoordinates();
		assertEquals(mockPositionY, playerShip.positionY);
		assertEquals(0, playerShip.getFuel());
	}

	@Test
	public void testGetNewCoordinatesUpCroppingFuel() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(true);
		playerShip.setFuel(0.1);

		playerShip.getNewCoordinates();
		assertEquals(mockPositionY - playerShip.velocityY, playerShip.positionY);
		assertEquals(0, playerShip.getFuel());
	}

	@Test
	public void testCheckBordersRight() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionX(PlayerShip.rightBorder + 1);

		playerShip.checkBorders();
		assertEquals(PlayerShip.rightBorder, playerShip.positionX);
	}

	@Test
	public void testCheckBordersLeft() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionX(PlayerShip.leftBorder - 1);
		playerShip.checkBorders();
		assertEquals(PlayerShip.leftBorder, playerShip.positionX);

	}

	@Test
	public void testCheckBordersUp() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionY(PlayerShip.upBorder - 1);

		playerShip.checkBorders();
		assertEquals(PlayerShip.upBorder, playerShip.positionY);
	}

	@Test
	public void testCheckBordersBottom() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionY(PlayerShip.bottomBorder + 1);

		playerShip.checkBorders();
		assertEquals(PlayerShip.bottomBorder, playerShip.positionY);

	}

	@Test
	public void testApplyGravity() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.applyGravity();
		assertEquals(mockPositionY + mockGravity, playerShip.positionY);

	}

}