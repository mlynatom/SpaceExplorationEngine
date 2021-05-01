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
	void testSetEnemyStrengthMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(102);
		assertEquals(10, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyStrengthLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(-1);
		assertEquals(0, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyStrengthNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyStrength(5);
		assertEquals(5, mockLevelData.getEnemyStrength());
	}

	@Test
	void testSetEnemyProjectileDamageMore() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyProjectileDamage(101);
		assertEquals(100, mockLevelData.getEnemyProjectileDamage());
	}

	@Test
	void testSetEnemyProjectileDamageLess() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyProjectileDamage(0.1);
		assertEquals(1, mockLevelData.getEnemyProjectileDamage());
	}

	@Test
	void testSetEnemyProjectileDamageNormal() {
		LevelData mockLevelData = new LevelData();
		mockLevelData.setEnemyProjectileDamage(50);
		assertEquals(50, mockLevelData.getEnemyProjectileDamage());
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