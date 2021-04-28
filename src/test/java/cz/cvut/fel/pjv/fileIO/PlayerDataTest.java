package cz.cvut.fel.pjv.fileIO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDataTest {

	@Test
	public void testSetShipLevelMore() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLevel(20);
		assertEquals(10, mockPlayerData.getShipLevel());
	}

	@Test
	public void testSetShipLevelLess() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLevel(0);
		assertEquals(1, mockPlayerData.getShipLevel());
	}

	@Test
	public void testSetShipLevelNormal() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLevel(5);
		assertEquals(5, mockPlayerData.getShipLevel());
	}

	@Test
	public void testSetShipLifeMore() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLife(100.1);
		assertEquals(100, mockPlayerData.getShipLife());
	}

	@Test
	public void testSetShipLifeLess() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLife(0.5);
		assertEquals(1, mockPlayerData.getShipLife());
	}

	@Test
	public void testSetShipLifeNormal() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipLife(50);
		assertEquals(50, mockPlayerData.getShipLife());
	}

	@Test
	public void testSetShipFuelMore() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(120.5);
		assertEquals(100, mockPlayerData.getShipFuel());
	}

	@Test
	public void testSetShipFuelLess() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(0.1);
		assertEquals(1, mockPlayerData.getShipFuel());
	}

	@Test
	public void testSetShipFuelNormal() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setShipFuel(40.1);
		assertEquals(40.1, mockPlayerData.getShipFuel());
	}

	@Test
	public void testSetFuelConsumptionMore() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setFuelConsumption(20);
		assertEquals(10, mockPlayerData.getFuelConsumption());
	}

	@Test
	public void testSetFuelConsumptionLess() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setFuelConsumption(-10);
		assertEquals(0, mockPlayerData.getFuelConsumption());
	}

	@Test
	public void testSetFuelConsumptionNormal() {
		PlayerData mockPlayerData = new PlayerData();
		mockPlayerData.setFuelConsumption(5);
		assertEquals(5, mockPlayerData.getFuelConsumption());
	}
}