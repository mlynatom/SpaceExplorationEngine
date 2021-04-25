package cz.cvut.fel.pjv.fileIO;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class as data structure for holding level, life and fuel information about PlayerShip.
 * It is used for saving and loading from yaml files.
 * All public getters, setters and constructor are vital for Jackson library.
 */
public class PlayerData {
	private static final Logger LOGGER = Logger.getLogger(PlayerData.class.getName());
	private int shipLevel;
	private double shipLife;
	private double shipFuel;

	public PlayerData() {
	}

	public int getShipLevel() {
		return shipLevel;
	}

	public void setShipLevel(int shipLevel) {
		if (shipLevel > 10) {
			LOGGER.log(Level.WARNING, "Loaded value of level was higher than allowed. Value was set to max value.");
			this.shipLevel = 10;
		} else if (shipLevel < 1) {
			LOGGER.log(Level.WARNING, "Loaded value of level was lower than allowed. Value was set to min value.");
			this.shipLevel = 1;
		} else {
			this.shipLevel = shipLevel;
		}
	}

	public double getShipLife() {
		return shipLife;
	}

	public void setShipLife(double shipLife) {
		if (shipLife > 100) {
			this.shipLife = 100;
			LOGGER.log(Level.WARNING, "Loaded value of life was higher than allowed. Value was set to max value.");
		} else if (shipLife < 1) {
			this.shipLife = 1;
			LOGGER.log(Level.WARNING, "Loaded value of life was lower than allowed. Value was set to min value.");
		} else {
			this.shipLife = shipLife;
		}

	}

	public double getShipFuel() {
		return shipFuel;
	}

	public void setShipFuel(double shipFuel) {
		if (shipFuel > 100) {
			this.shipFuel = 100;
			LOGGER.log(Level.WARNING, "Loaded value of fuel was higher than allowed. Value was set to max value.");
		} else if (shipFuel < 1) {
			this.shipFuel = 1;
			LOGGER.log(Level.WARNING, "Loaded value of fuel was lower than allowed. Value was set to min value.");
		} else {
			this.shipFuel = shipFuel;
		}
	}
}
