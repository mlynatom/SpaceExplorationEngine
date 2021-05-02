package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Actor;

import java.util.*;

/**
 * This class contains information about which actors are currently on screen and it is need to check them for collision.
 */
public class CastingDirector {
	private final List<Actor> collisionActorsPlayer; //actors which can collide with player
	private final Set<Actor> removedActorsPlayer; //actors which should be removed from list of actors which can collide with player
	private final List<Actor> collisionActorsEnemy; //actors which can collide with enemy

	public CastingDirector() {
		collisionActorsPlayer = new ArrayList<>();
		removedActorsPlayer = new HashSet<>();
		collisionActorsEnemy = new ArrayList<>();
	}

	/**
	 * This method adds all entered actors to the list of current actors.
	 *
	 * @param actors one or more actors to be added.
	 */
	public void addActorsToCollisionPlayerActors(Actor... actors) {
		collisionActorsPlayer.addAll(Arrays.asList(actors));
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
		collisionActorsPlayer.removeAll(removedActorsPlayer);
		removedActorsPlayer.clear();
	}

	/**
	 * This method adds all given actors to the list actors which collides with enemy.
	 *
	 * @param actors one or more actors to be added.
	 */
	public void addActorsToCollisionEnemyActors(Actor... actors) {
		collisionActorsEnemy.addAll(Arrays.asList(actors));
	}

	public List<Actor> getCollisionActorsPlayer() {
		return collisionActorsPlayer;
	}

	public Set<Actor> getRemovedActorsPlayer() {
		return removedActorsPlayer;
	}

	public List<Actor> getCollisionActorsEnemy() {
		return collisionActorsEnemy;
	}
}
