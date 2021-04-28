package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CastingDirectorTest {
	class mockActor extends Actor {
		public mockActor(double positionX, double positionY, String spriteBound, String... imageName) {
			super(positionX, positionY, spriteBound, imageName);
		}

		@Override
		public void update() {

		}
	}

	private final Actor mockActor1, mockActor2;
	private final int mockPositionX = 10;
	private final int mockPositionY = 10;
	private final String mockSpriteBound = "M0 6 L0 52 70 52 70 0 68 16 Z";
	private final String mockImageName = "mockImageName";

	public CastingDirectorTest() {
		mockActor1 = new mockActor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName);
		mockActor2 = new mockActor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName);
	}

	@Test
	public void testAddActorToCurrentActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCurrentActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getCurrentActors().toArray());
	}

	@Test
	public void testAddActorsToCurrentActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCurrentActors(mockActor1, mockActor2);
		Actor[] rightArray = {mockActor1, mockActor2};
		assertArrayEquals(rightArray, castingDirector.getCurrentActors().toArray());
	}

	@Test
	public void testAddActorToRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addToRemovedActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getRemovedActors().toArray());
	}

	@Test
	public void testAddActorsToRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addToRemovedActors(mockActor1, mockActor2);
		Actor[] rightArray = {mockActor1, mockActor2};
		assertEquals(2, castingDirector.getRemovedActors().size());
	}

	@Test
	public void testResetRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCurrentActors(mockActor1, mockActor2);
		castingDirector.addToRemovedActors(mockActor1);
		castingDirector.resetRemovedActors();
		Actor[] rightCurrentArray = {mockActor2};
		assertArrayEquals(rightCurrentArray, castingDirector.getCurrentActors().toArray());
		assertEquals(0, castingDirector.getRemovedActors().size());
	}
}