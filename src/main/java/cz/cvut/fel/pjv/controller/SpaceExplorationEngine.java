package cz.cvut.fel.pjv.controller;

import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import cz.cvut.fel.pjv.fileIO.YamlIO;
import cz.cvut.fel.pjv.view.ViewEngine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main game class.
 */
public class SpaceExplorationEngine extends Application {
	private boolean up, left, right, space, escape;
	private GamePlayLoop gamePlayLoop;
	protected ViewEngine viewEngine;
	private LevelData levelData;
	private PlayerData playerData;


	@Override
	public void start(Stage primaryStage) {
		loadFiles();
		viewEngine = new ViewEngine(primaryStage, this, levelData, playerData);
		viewEngine.startViewEngine();
		createKeyHandlers();
		startGamePlayLoop();
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

	private void startGamePlayLoop() {
		gamePlayLoop = new GamePlayLoop(viewEngine);
		gamePlayLoop.start();
	}

	public void stopGamePlayLoop() {
		gamePlayLoop.stop();
	}

	public void savePlayerData(PlayerData playerData) {
		this.playerData = playerData;
		YamlIO.savePlayerDataYaml(playerData);
	}

	public void updateFuelOnScreen(){
		viewEngine.updateFuelBar();
	}
	public void updateLifeOnScreen(){
		viewEngine.updateLifeBar();
	}
	public void updateLevelOnScreen(){
		viewEngine.updateLevelText();
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

}
