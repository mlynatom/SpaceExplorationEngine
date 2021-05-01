package cz.cvut.fel.pjv.editor;

import cz.cvut.fel.pjv.fileIO.Coordinate2D;
import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.YamlIO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.*;

public class Editor extends Application {
	private static final Logger LOGGER = Logger.getLogger(Editor.class.getName());

	private Canvas canvas;
	private Button obstacleButton, enemyButton, fuelBarrelButton, lifeAdderButton, levelEnhancerButton;
	private HBox horizontalButtonBox;
	private LevelData levelData;
	private List<Coordinate2D> obstaclesPositions, enemiesPositions, fuelBarrelsPositions, lifeAddersPositions, levelEnhancerPositions;
	private Image obstacleImage, enemyImage, fuelBarrelImage, lifeAdderImage, levelEnhancerImage, backgroundImg;
	private boolean isObstacle, isEnemy, isFuelBarrel, isLifeAdder, isLevelEnhancer;
	private Stage secondaryStage;

	@Override
	public void start(Stage primaryStage) {
		canvas = new Canvas(WIDTH, HEIGHT);

		levelData = new LevelData();
		initLevelData();
		loadImages();
		obstaclesPositions = new ArrayList<>();
		enemiesPositions = new ArrayList<>();
		fuelBarrelsPositions = new ArrayList<>();
		lifeAddersPositions = new ArrayList<>();
		levelEnhancerPositions = new ArrayList<>();

		BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);

		Pane pane = new Pane(canvas);
		pane.setBackground(background);

		pane.setOnMousePressed(event -> {
			LOGGER.log(Level.INFO, "Mouse pressed");
			if (isObstacle) {
				addObstacle(event.getX(), event.getY());
				obstaclesPositions.add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isEnemy) {
				addEnemy(event.getX(), event.getY());
				enemiesPositions.add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isFuelBarrel) {
				addFuelBarrel(event.getX(), event.getY());
				fuelBarrelsPositions.add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isLevelEnhancer) {
				addLevelEnhancer(event.getX(), event.getY());
				levelEnhancerPositions.add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isLifeAdder) {
				addLifeAdder(event.getX(), event.getY());
				lifeAddersPositions.add(new Coordinate2D(event.getX(), event.getY()));
			}

		});

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Space exploration engine - level editor");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		primaryStage.setOnCloseRequest(event -> Platform.exit());

		createSecondaryStage();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void stop() {
		levelData.setObstaclesPositions(obstaclesPositions);
		levelData.setEnemiesPositions(enemiesPositions);
		levelData.setLevelEnhancersPositions(levelEnhancerPositions);
		levelData.setFuelBarrelsPositions(fuelBarrelsPositions);
		levelData.setLifeAddersPositions(lifeAddersPositions);
		YamlIO.saveLevelDataYaml(levelData);
	}

	private void addObstacle(double positionX, double positionY) {
		canvas.getGraphicsContext2D().drawImage(obstacleImage, positionX, positionY);
	}

	private void addEnemy(double positionX, double positionY) {
		canvas.getGraphicsContext2D().drawImage(enemyImage, positionX, positionY);
	}

	private void addFuelBarrel(double positionX, double positionY) {
		canvas.getGraphicsContext2D().drawImage(fuelBarrelImage, positionX, positionY);
	}

	private void addLifeAdder(double positionX, double positionY) {
		canvas.getGraphicsContext2D().drawImage(lifeAdderImage, positionX, positionY);
	}

	private void addLevelEnhancer(double positionX, double positionY) {
		canvas.getGraphicsContext2D().drawImage(levelEnhancerImage, positionX, positionY);
	}

	private void loadImages() {
		try {
			backgroundImg = new Image("/background.png", WIDTH, HEIGHT, true, false, true);
			obstacleImage = new Image("/obstacle.png", 100, 50, true, false, true);
			enemyImage = new Image("/enemy.png", ENEMY_DIMENSION, ENEMY_DIMENSION, true, false, true);
			fuelBarrelImage = new Image("/fuel_barrel.png", INTERACT_THING_DIMENSION, INTERACT_THING_DIMENSION, true, false, true);
			levelEnhancerImage = new Image("/level_enhancer.png", INTERACT_THING_DIMENSION, INTERACT_THING_DIMENSION, true, false, true);
			lifeAdderImage = new Image("/life_adder.png", INTERACT_THING_DIMENSION, INTERACT_THING_DIMENSION, true, false, true);
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Loading of one of images failed. Error: " + e);
			System.err.println("Please check entered image names. Exiting application...");
			System.exit(100);
		}
	}

	private void createSecondaryStage() {
		secondaryStage = new Stage();
		Group group = new Group();
		Scene scene = new Scene(group, 750, 50, Color.GRAY);
		createSecondaryScreenNodes();
		group.getChildren().add(horizontalButtonBox);
		secondaryStage.setTitle("Choose which to add");
		secondaryStage.setScene(scene);
		secondaryStage.setResizable(false);
		secondaryStage.show();
	}

	private void createSecondaryScreenNodes() {
		horizontalButtonBox = new HBox(30);
		horizontalButtonBox.setAlignment(Pos.BOTTOM_CENTER);

		obstacleButton = new Button("obstacle");
		obstacleButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		obstacleButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "obstacle button was used");
			isObstacle = true;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = false;

		});

		enemyButton = new Button("enemy");
		enemyButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		enemyButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "enemy button used.");
			isObstacle = false;
			isEnemy = true;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = false;
		});

		fuelBarrelButton = new Button("fuel barrel");
		fuelBarrelButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		fuelBarrelButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "fuel barrel button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = true;
			isLifeAdder = false;
			isLevelEnhancer = false;
		});

		lifeAdderButton = new Button("life adder");
		lifeAdderButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		lifeAdderButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "life adder button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = true;
			isLevelEnhancer = false;
		});

		levelEnhancerButton = new Button("level enhancer");
		levelEnhancerButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		levelEnhancerButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "level enhancer button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = true;

		});

		horizontalButtonBox.getChildren().addAll(obstacleButton, enemyButton, fuelBarrelButton, lifeAdderButton, levelEnhancerButton);

	}

	private void initLevelData() {
		levelData.setGravity(0.6);
		levelData.setBackgroundImagePath("background.png");
		levelData.setShipImagePath("player_ship_0.png");
		levelData.setShipImageEnginesOnPath("player_ship_1.png");
		levelData.setEnemyStrength(0.1);
		levelData.setEnemyProjectileDamage(10);
		levelData.setEnemyLife(10);
	}
}
