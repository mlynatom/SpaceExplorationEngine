package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import cz.cvut.fel.pjv.model.PlayerShip;
import cz.cvut.fel.pjv.model.Projectile;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.*;

public class ViewEngine {
	private static final Logger LOGGER = Logger.getLogger(ViewEngine.class.getName());

	private Image background, help, mainBack;
	private Image playerShip0, playerShip1;
	private HBox horizontalButtonBox;
	private Insets buttonBoxPadding;
	private Button playButton, exitButton, exitSaveButton;
	private ToggleButton helpButton;
	private ImageView mainScreenBackground;
	private PlayerShip playerShip;
	private Projectile playerProjectile;
	private final Stage primaryStage;
	private Group rootGroup;
	private Scene scene;
	private final LevelData levelData;
	private final PlayerData playerData;
	private final SpaceExplorationEngine spaceExplorationEngine;


	public ViewEngine(Stage primaryStage, SpaceExplorationEngine spaceExplorationEngine, LevelData levelData, PlayerData playerData) {
		this.primaryStage = primaryStage;
		this.spaceExplorationEngine = spaceExplorationEngine;
		this.levelData = levelData;
		this.playerData = playerData;
	}

	public void update() {
		handleEscape();
	}

	public void startViewEngine() {
		primaryStage.setTitle("Space exploration engine");
		rootGroup = new Group();
		scene = new Scene(rootGroup, WIDTH, HEIGHT, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		loadImages();
		createGameActors();
		addGameActorsNodes();
		createMainScreenNodes();
		addNodesToMainScreen();
	}


	private void loadImages() {
		mainBack = new Image("/main_back.png", 1024, 600, true, false, true);
		background = new Image("/background.png", 1024, 600, true, false, true);
		help = new Image("/help.png", 1024, 600, true, false, true);
		playerShip0 = new Image("/player_ship_0.png", SHIP_DIMENSIONS, SHIP_DIMENSIONS, true, false, true);
		playerShip1 = new Image("/player_ship_1.png", SHIP_DIMENSIONS, SHIP_DIMENSIONS, true, false, true);
	}

	private void createGameActors() {
		//Attention!!
		playerProjectile = new Projectile(-10, -10, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 20, playerShip0);
		playerShip = new PlayerShip(spaceExplorationEngine, 20, WIDTH - SHIP_DIMENSIONS, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 10, 1, true, playerProjectile, 1, 20, playerShip0, playerShip1);
	}

	private void addGameActorsNodes() {
		rootGroup.getChildren().add(playerShip.getSpriteFrame());
	}

	private void createMainScreenNodes() {
		horizontalButtonBox = new HBox(30);
		horizontalButtonBox.setLayoutY(HEIGHT - 100);
		buttonBoxPadding = new Insets(0, 0, 10, 290);
		horizontalButtonBox.setPadding(buttonBoxPadding);

		playButton = new Button("PLAY");
		playButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		playButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Play button was used");
			mainScreenBackground.setImage(background);
			mainScreenBackground.toBack();
			horizontalButtonBox.setVisible(false);
		});

		helpButton = new ToggleButton("HELP");
		helpButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		helpButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Help button used.");
			if (helpButton.isSelected()) {
				mainScreenBackground.setImage(help);
			} else {
				mainScreenBackground.setImage(mainBack);
			}
		});

		exitButton = new Button("EXIT");
		exitButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Exit button used.");
			spaceExplorationEngine.stopGamePlayLoop();
			System.exit(1);
		});

		exitSaveButton = new Button("EXIT AND SAVE");
		exitSaveButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitSaveButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Exit and Save button used.");
			saveDataToPlayerData();
			spaceExplorationEngine.savePlayerData(playerData);
			spaceExplorationEngine.stopGamePlayLoop();
			System.exit(1);
		});

		horizontalButtonBox.getChildren().addAll(playButton, helpButton, exitButton, exitSaveButton);

		mainScreenBackground = new ImageView();
		mainScreenBackground.setImage(mainBack);
	}

	private void addNodesToMainScreen() {
		rootGroup.getChildren().add(mainScreenBackground);
		rootGroup.getChildren().add(horizontalButtonBox);

	}

	private void saveDataToPlayerData() {
		playerData.setShipFuel(playerShip.getFuel());
		playerData.setShipLevel(playerShip.getLevel());
		playerData.setShipLife(playerShip.getLife());
	}

	public void handleEscape() {
		if (spaceExplorationEngine.isEscape()) {
			mainScreenBackground.setImage(mainBack);
			mainScreenBackground.toFront();
			horizontalButtonBox.setVisible(true);
			horizontalButtonBox.toFront();
		}
	}

	public Scene getScene() {
		return scene;
	}

	public PlayerShip getPlayerShip() {
		return playerShip;
	}
}
