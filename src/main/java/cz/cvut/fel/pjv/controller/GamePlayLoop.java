package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.EnemyShip;
import cz.cvut.fel.pjv.view.ViewEngine;
import javafx.animation.AnimationTimer;

/**
 * This class control whole game in pulses.
 */
public class GamePlayLoop extends AnimationTimer {
	private final ViewEngine viewEngine;

	public GamePlayLoop(ViewEngine viewEngine) {
		this.viewEngine = viewEngine;
	}

	@Override
	public void handle(long now) {
		viewEngine.update();
		viewEngine.getPlayerShip().update();
		for (EnemyShip enemyShip : viewEngine.getEnemyShips()) {
			enemyShip.update();
		}
	}
}
