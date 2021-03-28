package cz.cvut.fel.pjv.model;

public abstract class Ship extends Actor{
	protected double life, damage;
	protected boolean isAlive;

	public Ship(double iX, double iY, double life, double damage) {
		super(iX, iY);
		this.life = life;
		this.damage = damage;
		isAlive = true;
	}

	@Override
	public abstract void update();
}
