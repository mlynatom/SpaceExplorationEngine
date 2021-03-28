package cz.cvut.fel.pjv.model;

public class PlayerShip extends Ship {
	private int level;

	public PlayerShip(double iX, double iY, double life, double damage, int level) {
		super(iX, iY, life, damage);
		this.level = level;
	}

	@Override
	public void update() {

	}
}
