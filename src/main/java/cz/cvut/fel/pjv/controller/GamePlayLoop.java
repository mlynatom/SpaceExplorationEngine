package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.EnemyShip;
import cz.cvut.fel.pjv.model.Projectile;
import cz.cvut.fel.pjv.view.ViewEngine;
import javafx.animation.AnimationTimer;

/**
 * This class creates animation timer which periodically call update methods of classes.
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
		viewEngine.getPlayerProjectile().update();
		for (Projectile enemyProjectile : viewEngine.getEnemyProjectiles()) {
			enemyProjectile.update();
		}
	}
}
