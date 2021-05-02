package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import cz.cvut.fel.pjv.fileIO.YamlIO;
import cz.cvut.fel.pjv.model.Actor;
import cz.cvut.fel.pjv.view.ImageDirector;
import cz.cvut.fel.pjv.view.ViewEngine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main game javaFX class which creates graphic, starts loop and almost all other things.
 */
public class SpaceExplorationEngine extends Application {
	private boolean up = false, left = false, right = false, space = false, escape = false;
	private GamePlayLoop gamePlayLoop;
	protected ViewEngine viewEngine;
	private LevelData levelData;
	private PlayerData playerData;
	private ImageDirector imageDirector;
	private CastingDirector castingDirector;

	@Override
	public void start(Stage primaryStage) {
		loadFiles();
		imageDirector = new ImageDirector();
		castingDirector = new CastingDirector();
		viewEngine = new ViewEngine(primaryStage, this, levelData, playerData, imageDirector, castingDirector);
		viewEngine.startViewEngine();
		createKeyHandlers();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void loadFiles() {
		playerData = YamlIO.loadPlayerDataYaml();
		levelData = YamlIO.loadLevelDataYaml();
	}

	private void createKeyHandlers() {
		viewEngine.getScene().setOnKeyPressed(event -> {
			switch (event.getCode()) {
				case UP:
				case W:
					up = true;
					break;
				case LEFT:
				case A:
					left = true;
					break;
				case RIGHT:
				case D:
					right = true;
					break;
				case SPACE:
					space = true;
					break;
				case ESCAPE:
					escape = true;
					break;
			}
		});

		viewEngine.getScene().setOnKeyReleased(event -> {
			switch (event.getCode()) {
				case UP:
				case W:
					up = false;
					break;
				case LEFT:
				case A:
					left = false;
					break;
				case RIGHT:
				case D:
					right = false;
					break;
				case SPACE:
					space = false;
					break;
				case ESCAPE:
					escape = false;
					break;
			}
		});
	}

	/**
	 * This method creates new game loop (animation timer) and start it.
	 */
	public void startGamePlayLoop() {
		gamePlayLoop = new GamePlayLoop(viewEngine);
		gamePlayLoop.start();
	}

	/**
	 * This method calls stop on game loop (animation timer).
	 */
	public void stopGamePlayLoop() {
		gamePlayLoop.stop();
	}

	/**
	 * This method saves given playerData to Yaml file.
	 *
	 * @param playerData data to be saved.
	 */
	public void savePlayerData(PlayerData playerData) {
		this.playerData = playerData;
		YamlIO.savePlayerDataYaml(playerData);
	}

	public void updateFuelOnScreen() {
		viewEngine.updateFuelBar();
	}

	public void updateLifeOnScreen() {
		viewEngine.updateLifeBar();
	}

	public void updateLevelOnScreen() {
		viewEngine.updateLevelText();
	}

	public void removeActorFromRoot(Actor object) {
		viewEngine.getRootGroup().getChildren().remove(object.getSpriteFrame());
	}

	public void callEndGame() {
		viewEngine.endGame();
	}

	public boolean isUp() {
		return up;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isSpace() {
		return space;
	}

	public boolean isEscape() {
		return escape;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}

	public ImageDirector getImageDirector() {
		return imageDirector;
	}

	public CastingDirector getCastingDirector() {
		return castingDirector;
	}
}
