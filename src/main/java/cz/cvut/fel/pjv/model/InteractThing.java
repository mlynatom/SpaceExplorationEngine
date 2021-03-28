package cz.cvut.fel.pjv.model;

public abstract class InteractThing extends Actor{
	public InteractThing(double iX, double iY) {
		super(iX, iY);
	}

	@Override public abstract void update();
}
