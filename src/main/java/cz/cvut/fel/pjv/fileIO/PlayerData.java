package cz.cvut.fel.pjv.fileIO;

/**
 * This class as data structure for holding level, life and fuel information about PlayerShip.
 * It is used for saving and loading from yaml files.
 * All public getters, setters and constructor are vital for Jackson library.
 */
public class PlayerData {
	private int shipLevel;
	private int shipLife;
	private double shipFuel;

	public PlayerData() {
	}

	public int getShipLevel() {
		return shipLevel;
	}

	public void setShipLevel(int shipLevel) {
		this.shipLevel = shipLevel;
	}

	public int getShipLife() {
		return shipLife;
	}

	public void setShipLife(int shipLife) {
		this.shipLife = shipLife;
	}

	public double getShipFuel() {
		return shipFuel;
	}

	public void setShipFuel(double shipFuel) {
		this.shipFuel = shipFuel;
	}
}
