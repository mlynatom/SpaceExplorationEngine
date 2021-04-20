package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.controller.GamePlayLoop;
import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.PlayerData;
import cz.cvut.fel.pjv.fileIO.YamlIO;
import cz.cvut.fel.pjv.model.PlayerShip;
import cz.cvut.fel.pjv.model.Projectile;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main graphic class.
 */
public class SpaceExplorationEngine extends Application {

	private static final double WIDTH = 1024, HEIGHT = 600;

	private Group rootGroup;
	private double maxWidth;
	private double maxHeight;
	private boolean up, left, right, space, escape;
	private Image background, help, mainBack;
	private Image playerShip0, playerShip1;
	private Scene scene;
	private HBox horizontalButtonBox;
	private Insets buttonBoxPadding;
	private Button playButton, exitButton, exitSaveButton;
	private ToggleButton helpButton;
	private ImageView mainScreenBackground;
	private GamePlayLoop gamePlayLoop;
	private PlayerShip playerShip;
	private Projectile playerProjectile;
	private final double playerShipImageDimension = HEIGHT / 6;
	private LevelData levelData;
	private PlayerData playerData;


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Space exploration engine");
		rootGroup = new Group();
		maxWidth = primaryStage.getMaxWidth();
		maxHeight = primaryStage.getMaxHeight();
		scene = new Scene(rootGroup, WIDTH, HEIGHT, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		//primaryStage.setMaximized(MAXIMIZED);

		loadFiles();
		createKeyHandlers();
		loadImages();
		createGameActors();
		addGameActorsNodes();
		createMainScreenNodes();
		addNodesToMainScreen();
		startGamePlayLoop();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void update() {
		handleEscape();
	}

	private void loadFiles() {
		playerData = YamlIO.loadPlayerDataYaml();
		levelData = YamlIO.loadLevelDataYaml();
	}

	private void createKeyHandlers() {
		scene.setOnKeyPressed(event -> {
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

		scene.setOnKeyReleased(event -> {
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

	private void loadImages() {
		mainBack = new Image("/main_back.png", 1024, 600, true, false, true);
		background = new Image("/background.png", 1024, 600, true, false, true);
		help = new Image("/help.png", 1024, 600, true, false, true);
		playerShip0 = new Image("/player_ship_0.png", playerShipImageDimension, playerShipImageDimension, true, false, true);
		playerShip1 = new Image("/player_ship_1.png", playerShipImageDimension, playerShipImageDimension, true, false, true);
	}

	private void createGameActors() {
		//Attention!!
		playerProjectile = new Projectile(-10, -10, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 20, playerShip0);
		playerShip = new PlayerShip(this, 20, WIDTH - playerShipImageDimension, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 10, 1, true, playerProjectile, 1, 20, playerShip0, playerShip1);
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
			System.out.println("play clicked");
			mainScreenBackground.setImage(background);
			mainScreenBackground.toBack();
			horizontalButtonBox.setVisible(false);
		});

		helpButton = new ToggleButton("HELP");
		helpButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		helpButton.setOnAction(event -> {
			System.out.println("help clicked");
			if (helpButton.isSelected()) {
				mainScreenBackground.setImage(help);
			} else {
				mainScreenBackground.setImage(mainBack);
			}
		});

		exitButton = new Button("EXIT");
		exitButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitButton.setOnAction(event -> {
			System.out.println("exit clicked");
			gamePlayLoop.stop();
			System.exit(1);
		});

		exitSaveButton = new Button("EXIT AND SAVE");
		exitSaveButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitSaveButton.setOnAction(event -> {
			System.out.println("exit and save clicked");
			savePlayerData();
			gamePlayLoop.stop();
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

	private void startGamePlayLoop() {
		gamePlayLoop = new GamePlayLoop(this);
		gamePlayLoop.start();
	}

	private void handleEscape() {
		if (escape) {
			mainScreenBackground.setImage(mainBack);
			mainScreenBackground.toFront();
			horizontalButtonBox.setVisible(true);
			horizontalButtonBox.toFront();
		}
	}

	private void savePlayerData() {
		playerData.setShipFuel(playerShip.getFuel());
		playerData.setShipLevel(playerShip.getLevel());
		playerData.setShipLife(playerShip.getLife());

		YamlIO.savePlayerDataYaml(playerData);
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

	public PlayerShip getPlayerShip() {
		return playerShip;
	}

	public static double getWIDTH() {
		return WIDTH;
	}

	public static double getHEIGHT() {
		return HEIGHT;
	}

	public double getPlayerShipImageDimension() {
		return playerShipImageDimension;
	}
}
