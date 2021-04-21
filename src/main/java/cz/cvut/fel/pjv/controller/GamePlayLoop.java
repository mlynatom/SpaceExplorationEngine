package cz.cvut.fel.pjv.controller;

import javafx.animation.AnimationTimer;

/**
 * This class control whole game in pulses.
 */
public class GamePlayLoop extends AnimationTimer {
	private final SpaceExplorationEngine spaceExplorationEngine;

	public GamePlayLoop(SpaceExplorationEngine spaceExplorationEngine) {
		this.spaceExplorationEngine = spaceExplorationEngine;
	}

	@Override
	public void handle(long now) {
		spaceExplorationEngine.update();
		spaceExplorationEngine.viewEngine.getPlayerShip().update();
	}
}
