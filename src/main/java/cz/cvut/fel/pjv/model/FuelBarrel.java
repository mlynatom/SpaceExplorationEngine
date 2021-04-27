package cz.cvut.fel.pjv.model;

/**
 * Builds a object of fuel barrel which can add fuel to PlayerShip
 */
public class FuelBarrel extends InteractThing {
	private double amountOfFuelToAdd;

	public FuelBarrel(double positionX, double positionY, String spriteBound, double amountOfFuelToAdd, String... imageName) {
		super(positionX, positionY, spriteBound,imageName);
		this.amountOfFuelToAdd = amountOfFuelToAdd;
	}

	@Override
	public void update() {

	}

	@Override
	public void interact(PlayerShip playerShip) {

	}

}
