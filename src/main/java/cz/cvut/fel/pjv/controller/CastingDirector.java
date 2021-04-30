package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Actor;

import java.util.*;

/**
 * This class contains information about which actors are currently on screen and it is need to check them for collision.
 */
public class CastingDirector {
	private final List<Actor> collisionPlayerActors;
	private final Set<Actor> removedActorsPlayer;
	private final List<Actor> collisionEnemyActors;
	private final Set<Actor> removedActorsEnemy;

	public CastingDirector() {
		collisionPlayerActors = new ArrayList<>();
		removedActorsPlayer = new HashSet<>();
		collisionEnemyActors = new ArrayList<>();
		removedActorsEnemy = new HashSet<>();
	}

	/**
	 * This method adds all entered actors to the list of current actors.
	 *
	 * @param actors one or more actors to be added.
	 */
	public void addActorsToCollisionPlayerActors(Actor... actors) {
		collisionPlayerActors.addAll(Arrays.asList(actors));
	}

	/**
	 * This method adds all given actors to the set of removed actors.
	 *
	 * @param actors one or more actors to be added.
	 */
	public void addToPlayerRemovedActors(Actor... actors) {
		removedActorsPlayer.addAll(Arrays.asList(actors));
	}

	/**
	 * This method removes actors which are marked as to be removed (in removedActorsPlayer) and reset removedActorsPlayer to
	 * prepare this list for next collision.
	 */
	public void resetPlayerRemovedActors() {
		collisionPlayerActors.removeAll(removedActorsPlayer);
		removedActorsPlayer.clear();
	}

	public List<Actor> getCollisionPlayerActors() {
		return collisionPlayerActors;
	}

	public Set<Actor> getRemovedActorsPlayer() {
		return removedActorsPlayer;
	}

	public void addActorsToCollisionEnemyActors(Actor... actors) {
		collisionEnemyActors.addAll(Arrays.asList(actors));
	}

	public void addToEnemyRemovedActors(Actor... actors) {
		removedActorsEnemy.addAll(Arrays.asList(actors));
	}

	public void resetEnemyRemovedActors() {
		collisionEnemyActors.removeAll(removedActorsEnemy);
		removedActorsEnemy.clear();
	}

	public List<Actor> getCollisionEnemyActors() {
		return collisionEnemyActors;
	}

	public Set<Actor> getRemovedActorsEnemy() {
		return removedActorsEnemy;
	}
}
