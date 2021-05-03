package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.model.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CastingDirectorTest {
	static class mockActor extends Actor {
		public mockActor(double positionX, double positionY, String spriteBound, String... imageName) {
			super(positionX, positionY, spriteBound, imageName);
		}

		@Override
		public void update() {

		}
	}

	private final Actor mockActor1, mockActor2;

	public CastingDirectorTest() {
		int mockPositionX = 10;
		int mockPositionY = 10;
		String mockSpriteBound = "M0 6 L0 52 70 52 70 0 68 16 Z";
		String mockImageName = "mockImageName";
		mockActor1 = new mockActor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName);
		mockActor2 = new mockActor(mockPositionX, mockPositionY, mockSpriteBound, mockImageName);
	}

	@Test
	public void testAddActorToCollisionPlayerActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionPlayerActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsPlayer().toArray());
	}

	@Test
	public void testAddActorsToCollisionPlayerActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionPlayerActors(mockActor1, mockActor2);
		Actor[] rightArray = {mockActor1, mockActor2};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsPlayer().toArray());
	}

	@Test
	public void testAddToPlayerRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addToPlayerRemovedActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getRemovedActorsPlayer().toArray());
	}

	@Test
	public void testAddsToPlayerRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addToPlayerRemovedActors(mockActor1, mockActor2);
		assertEquals(2, castingDirector.getRemovedActorsPlayer().size());
	}

	@Test
	public void testResetRemovedActors() {
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionPlayerActors(mockActor1, mockActor2);
		castingDirector.addToPlayerRemovedActors(mockActor1);
		castingDirector.resetPlayerRemovedActors();
		Actor[] rightCurrentArray = {mockActor2};
		assertArrayEquals(rightCurrentArray, castingDirector.getCollisionActorsPlayer().toArray());
		assertEquals(0, castingDirector.getRemovedActorsPlayer().size());
	}

	@Test
	public void testAddActorToCollisionEnemyActors(){
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionEnemyActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsEnemy().toArray());
	}

	@Test
	public void testAddActorsToCollisionEnemyActors(){
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionEnemyActors(mockActor1, mockActor2);
		Actor[] rightArray = {mockActor1, mockActor2};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsEnemy().toArray());
	}

	@Test
	public void testAddActorToCollisionProjectileActors(){
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionProjectileActors(mockActor1);
		Actor[] rightArray = {mockActor1};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsProjectile().toArray());
	}

	@Test
	public void testAddActorsToCollisionProjectileActors(){
		CastingDirector castingDirector = new CastingDirector();
		castingDirector.addActorsToCollisionProjectileActors(mockActor1, mockActor2);
		Actor[] rightArray = {mockActor1, mockActor2};
		assertArrayEquals(rightArray, castingDirector.getCollisionActorsProjectile().toArray());
	}
}