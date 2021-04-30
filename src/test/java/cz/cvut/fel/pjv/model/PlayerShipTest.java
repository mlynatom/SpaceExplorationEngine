package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
		double mockDamage = 50;
		mockProjectile = new Projectile(mockPositionX, mockPositionY, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", mockLifeSpan, mockDamage, mockImageName);
		mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(mockFuel);
		mockPlayerData.setShipLevel(1);
		mockPlayerData.setShipLife(100);
		mockPlayerData.setFuelConsumption(0.1);
	}

	@Test
	public void testShootProjectileDo() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setSpace(true);
		playerShip.shootProjectile();
		assertTrue(playerShip.projectileShot);
		assertEquals(1,playerShip.counterProjectile);
	}

	@Test
	public void testShootProjectileNot() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setSpace(false);
		playerShip.shootProjectile();
		assertFalse(playerShip.projectileShot);
		assertEquals(0,playerShip.counterProjectile);
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
	public void testApplyGravity() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.applyGravity();
		assertEquals(mockPositionY + mockGravity, playerShip.positionY);

	}

	@Test
	public void addFuelNormal() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.fuel = 50;
		playerShip.addFuel(20);
		assertEquals(70, playerShip.fuel);
	}

	@Test
	public void addFuelExceed() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.fuel = 50;
		playerShip.addFuel(70);
		assertEquals(100, playerShip.fuel);
	}

	@Test
	public void addLevelNormal() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.level = 1;
		playerShip.addLevel(2);
		assertEquals(3, playerShip.level);
	}

	@Test
	public void addLevelExceed() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.level = 1;
		playerShip.addLevel(100);
		assertEquals(10, playerShip.level);
	}

	@Test
	public void testAddLifeNormal() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.life = 10;
		playerShip.addLife(2);
		assertEquals(12, playerShip.life);
	}

	@Test
	public void testAddLifeExceed() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.life = 10;
		playerShip.addLife(100);
		assertEquals(100, playerShip.life);
	}

	@Test
	public void testDecreaseLife() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.life = 10;
		playerShip.decreaseLife(2);
		assertEquals(8, playerShip.life);
	}

	@Test
	public void testRecoverPositionRight() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		mockObject.positionX -= 1;
		playerShip.lastXPosition = mockPositionX;
		playerShip.recoverPosition(mockObject);
		assertEquals(mockPositionX + 0.1, playerShip.positionX);

	}

	@Test
	public void testRecoverPositionDown() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		mockObject.positionY -= 1;
		playerShip.lastYPosition = mockPositionY;
		playerShip.recoverPosition(mockObject);
		assertEquals(mockPositionY + 0.1, playerShip.positionY);

	}

	@Test
	public void testRecoverPositionLeft() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		mockObject.positionX += 1;
		playerShip.lastXPosition = mockPositionX;
		playerShip.recoverPosition(mockObject);
		assertEquals(mockPositionX - 0.1, playerShip.positionX);

	}

	@Test
	public void testRecoverPositionUp() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		Actor mockObject = new Actor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName) {
			@Override
			public void update() {

			}
		};
		mockObject.positionY += 1;
		playerShip.lastYPosition = mockPositionY;
		playerShip.recoverPosition(mockObject);
		assertEquals(mockPositionY - 0.1, playerShip.positionY);

	}

}