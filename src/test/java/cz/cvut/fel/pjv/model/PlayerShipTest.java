package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

	public PlayerShipTest() {
		mockSpaceExplorationEngine = new SpaceExplorationEngine();
		double mockLifeSpan = 10;
		mockProjectile = new Projectile(mockPositionX, mockPositionY, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", mockLifeSpan, mockImageName);
		mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(100);
		mockPlayerData.setShipLevel(1);
		mockPlayerData.setShipLife(100);
	}

	@Test
	public void testGetNewCoordinatesLeft() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(true);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(false);

		try {
			Method getNewCoordinates = playerShip.getClass().getDeclaredMethod("getNewCoordinates");
			getNewCoordinates.setAccessible(true);
			getNewCoordinates.invoke(playerShip);
			assertEquals(mockPositionX - playerShip.velocityX, playerShip.positionX);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetNewCoordinatesRight() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(true);
		mockSpaceExplorationEngine.setUp(false);

		try {
			Method getNewCoordinates = playerShip.getClass().getDeclaredMethod("getNewCoordinates");
			getNewCoordinates.setAccessible(true);
			getNewCoordinates.invoke(playerShip);
			assertEquals(mockPositionX + playerShip.velocityX, playerShip.positionX);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNewCoordinatesUp() {

	}

	@Test
	public void testGetNewCoordinatesUpLowFuel() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		mockSpaceExplorationEngine.setLeft(false);
		mockSpaceExplorationEngine.setRight(false);
		mockSpaceExplorationEngine.setUp(true);
		playerShip.setFuel(0);

		try {
			Method getNewCoordinates = playerShip.getClass().getDeclaredMethod("getNewCoordinates");
			getNewCoordinates.setAccessible(true);
			getNewCoordinates.invoke(playerShip);
			assertEquals(mockPositionY, playerShip.positionY);
			assertEquals(0, playerShip.getFuel());
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNewCoordinatesUpCroppingFuel() {

	}

	@Test
	public void testCheckBordersRight() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionX(PlayerShip.getRightBorder() + 1);

		try {
			Method checkBorders = playerShip.getClass().getDeclaredMethod("checkBorders");
			checkBorders.setAccessible(true);
			checkBorders.invoke(playerShip);
			assertEquals(PlayerShip.getRightBorder(), playerShip.positionX);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckBordersLeft() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionX(PlayerShip.getLeftBorder() - 1);

		try {
			Method checkBorders = playerShip.getClass().getDeclaredMethod("checkBorders");
			checkBorders.setAccessible(true);
			checkBorders.invoke(playerShip);
			assertEquals(PlayerShip.getLeftBorder(), playerShip.positionX);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckBordersUp() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionY(PlayerShip.getUpBorder() - 1);

		try {
			Method checkBorders = playerShip.getClass().getDeclaredMethod("checkBorders");
			checkBorders.setAccessible(true);
			checkBorders.invoke(playerShip);
			assertEquals(PlayerShip.getUpBorder(), playerShip.positionY);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckBordersBottom() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		playerShip.setPositionY(PlayerShip.getBottomBorder() + 1);

		try {
			Method checkBorders = playerShip.getClass().getDeclaredMethod("checkBorders");
			checkBorders.setAccessible(true);
			checkBorders.invoke(playerShip);
			assertEquals(PlayerShip.getBottomBorder(), playerShip.positionY);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testApplyGravity() {
		PlayerShip playerShip = new PlayerShip(mockSpaceExplorationEngine, mockPositionX, mockPositionY, mockSpriteBound, mockProjectile, mockPlayerData, mockGravity, mockImageName);
		try {
			Method applyGravity = playerShip.getClass().getDeclaredMethod("applyGravity");
			applyGravity.setAccessible(true);
			applyGravity.invoke(playerShip);
			assertEquals(mockPositionY + mockGravity, playerShip.positionY);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}