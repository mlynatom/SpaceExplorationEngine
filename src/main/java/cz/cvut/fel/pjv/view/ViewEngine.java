package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.controller.SpaceExplorationEngine;
import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import cz.cvut.fel.pjv.model.PlayerShip;
import cz.cvut.fel.pjv.model.Projectile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.*;

public class ViewEngine {
	private static final Logger LOGGER = Logger.getLogger(ViewEngine.class.getName());

	private Image background, help, mainBack;
	private Image shipImage0, shipImage1, projectileImage, obstacleImage, enemyImage;
	private HBox horizontalButtonBox, horizontalUpperBox, fuelBox, lifeBox, levelBox;
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
	private ProgressBar fuelProgressBar, lifeProgressBar;
	private Text fuelLabel, lifeLabel, levelLabel, levelText;
	private Font upperBarFont;


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
		try {
			mainBack = new Image("/main_back.png", WIDTH, HEIGHT, true, false, true);
			background = new Image(levelData.getBackgroundImagePath(), WIDTH, HEIGHT, true, false, true);
			help = new Image("/help.png", WIDTH, HEIGHT, true, false, true);
			shipImage0 = new Image(levelData.getShipImagePath(), SHIP_DIMENSIONS, SHIP_DIMENSIONS, true, false, true);
			shipImage1 = new Image(levelData.getShipImageEnginesOnPath(), SHIP_DIMENSIONS, SHIP_DIMENSIONS, true, false, true);
			projectileImage = new Image("projectile.png", 100, 10, true, false, true);
			obstacleImage = new Image("obstacle.png", 100, 50, true, false, true);
			enemyImage = new Image("enemy.png", SHIP_DIMENSIONS, SHIP_DIMENSIONS, true, false, true);
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Loading of one of images failed. Error: " + e);
			System.err.println("Please check entered image names. Exiting application...");
			System.exit(100);
		}

	}

	private void createGameActors() {
		//Attention to projectile!!
		playerProjectile = new Projectile(-10, -10,
				"M 6,246 L 76,213 287,214 462,148 489,216 491,283 460,348 289,283 74,286 Z", 20, projectileImage);
		playerShip = new PlayerShip(spaceExplorationEngine, DEFAULT_SHIP_X_POSITION, WIDTH - SHIP_DIMENSIONS,
				"M 192,4 L 153,67 140,106 141,249 110,290 132,299 133,352 253,352 254,300 275,289 250,250 250,101 231,67 Z",
				playerProjectile, playerData, levelData.getGravity(), shipImage0, shipImage1);
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
			restartGame();
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

		createUpperBox();

	}

	private void createUpperBox() {
		upperBarFont = new Font("impact", 17);

		lifeBox = new HBox(5);
		lifeLabel = new Text();
		lifeLabel.setText("LIFE:");
		lifeLabel.setFill(Color.WHITE);
		lifeLabel.setFont(upperBarFont);

		lifeProgressBar = new ProgressBar(playerShip.getLife() / 100);
		lifeProgressBar.setStyle("-fx-accent: #fc0808");

		lifeBox.getChildren().addAll(lifeLabel, lifeProgressBar);

		fuelLabel = new Text();
		fuelLabel.setText("FUEL:");
		fuelLabel.setFill(Color.WHITE);
		fuelLabel.setFont(upperBarFont);

		fuelProgressBar = new ProgressBar(playerShip.getFuel() / 100);
		fuelProgressBar.setStyle("-fx-accent: #e0a80d");

		fuelBox = new HBox(5);
		fuelBox.getChildren().addAll(fuelLabel, fuelProgressBar);

		levelLabel = new Text();
		levelLabel.setText("LEVEL:");
		levelLabel.setFill(Color.WHITE);
		levelLabel.setFont(upperBarFont);

		levelText = new Text();
		levelText.setText(String.valueOf(playerShip.getLevel()));
		levelText.setFill(Color.WHITE);
		levelText.setFont(upperBarFont);

		levelBox = new HBox(5);
		levelBox.getChildren().addAll(levelLabel, levelText);

		horizontalUpperBox = new HBox(20);
		horizontalUpperBox.setAlignment(Pos.TOP_LEFT);
		horizontalUpperBox.getChildren().addAll(lifeBox, fuelBox, levelBox);
	}

	private void addNodesToMainScreen() {
		rootGroup.getChildren().add(horizontalUpperBox);
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

	private void restartGame() {
		playerShip.setPositionX(DEFAULT_SHIP_X_POSITION);
		playerShip.setPositionY(WIDTH - SHIP_DIMENSIONS);
		playerShip.setFuel(playerData.getShipFuel());
		playerShip.setLevel(playerData.getShipLevel());
		playerShip.setLife(playerData.getShipLife());
		updateFuelBar();
		updateLifeBar();
		updateLevelText();
	}

	public void updateFuelBar() {
		fuelProgressBar.setProgress(playerShip.getFuel() / 100);
	}

	public void updateLifeBar() {
		lifeProgressBar.setProgress(playerShip.getLife() / 100);
	}

	public void updateLevelText() {
		levelText.setText(String.valueOf(playerShip.getLevel()));
	}

	public Scene getScene() {
		return scene;
	}

	public PlayerShip getPlayerShip() {
		return playerShip;
	}
}
