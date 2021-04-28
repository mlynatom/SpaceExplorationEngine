package cz.cvut.fel.pjv.fileIO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelDataTest {

	@Test
	void testSetGravityMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setGravity(101);
		assertEquals(100, mockLevelData.getGravity());
	}

	@Test
	void testSetGravityLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setGravity(-1);
		assertEquals(0, mockLevelData.getGravity());
	}

	@Test
	void testSetGravityNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setGravity(20);
		assertEquals(20, mockLevelData.getGravity());
	}

	@Test
	void testSetNumOfFuelsMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfFuels(11);
		assertEquals(10, mockLevelData.getNumOfFuels());
	}

	@Test
	void testSetNumOfFuelsLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfFuels(-1);
		assertEquals(0, mockLevelData.getNumOfFuels());
	}

	@Test
	void testSetNumOfFuelsNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfFuels(2);
		assertEquals(2, mockLevelData.getNumOfFuels());
	}

	@Test
	void testSetNumOfLevelEnhancersMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLevelEnhancers(11);
		assertEquals(10, mockLevelData.getNumOfLevelEnhancers());
	}

	@Test
	void testSetNumOfLevelEnhancersLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLevelEnhancers(-2);
		assertEquals(0, mockLevelData.getNumOfLevelEnhancers());
	}

	@Test
	void testSetNumOfLevelEnhancersNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLevelEnhancers(4);
		assertEquals(4, mockLevelData.getNumOfLevelEnhancers());
	}

	@Test
	void testSetNumOfLifeAddersMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLifeAdders(11);
		assertEquals(10, mockLevelData.getNumOfLifeAdders());
	}

	@Test
	void testSetNumOfLifeAddersLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLifeAdders(-1);
		assertEquals(0, mockLevelData.getNumOfLifeAdders());
	}

	@Test
	void testSetNumOfLifeAddersNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setNumOfLifeAdders(5);
		assertEquals(5, mockLevelData.getNumOfLifeAdders());
	}

	@Test
	void testSetEnemyStrengthMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(102);
		assertEquals(100, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyStrengthLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(0.5);
		assertEquals(1, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyStrengthNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(20.5);
		assertEquals(20.5, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyLifeMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyLife(101);
		assertEquals(100, mockLevelData.getEnemyLife());
	}@Test
	void testSetEnemyLifeLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyLife(0.2);
		assertEquals(1, mockLevelData.getEnemyLife());
	}@Test
	void testSetEnemyLife() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyLife(5.6);
		assertEquals(5.6, mockLevelData.getEnemyLife());
	}
}