package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Actor;

import java.util.*;

/**
 * This class contains information about which actors are currently on screen and it is need to check them for collision.
 */
public class CastingDirector {
	private final List<Actor> currentActors;
	private final Set<Actor> removedActors;

	public CastingDirector() {
		currentActors = new ArrayList<>();
		removedActors = new HashSet<>();
	}

	/**
	 * This method adds all entered actors to the list of current actors.
	 * @param actors one or more actors to be added.
	 */
	public void addActorsToCurrentActors(Actor... actors) {
		currentActors.addAll(Arrays.asList(actors));
	}

	/**
	 * This method adds all given actors to the set of removed actors.
	 * @param actors one or more actors to be added.
	 */
	public void addToRemovedActors(Actor... actors) {
		removedActors.addAll(Arrays.asList(actors));
	}

	/**
	 * This method removes actors which are marked as to be removed (in removedActors) and reset removedActors to
	 * prepare this list for next collision.
	 */
	public void resetRemovedActors(){
		currentActors.removeAll(removedActors);
		removedActors.clear();
	}

	public List<Actor> getCurrentActors() {
		return currentActors;
	}

	public Set<Actor> getRemovedActors() {
		return removedActors;
	}
}
