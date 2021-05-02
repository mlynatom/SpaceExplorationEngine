package cz.cvut.fel.pjv.model;

/**
 * This class creates fuel barrel which adds fuel to player ship
 */
public class FuelBarrel extends Actor {
	protected double amountOfFuelToAdd;

	public FuelBarrel(double positionX, double positionY, String spriteBound, double amountOfFuelToAdd, String... imageName) {
		super(positionX, positionY, spriteBound, imageName);
		this.amountOfFuelToAdd = amountOfFuelToAdd;
	}

	@Override
	public void update() {
	}
}
