package cz.cvut.fel.pjv.editor;

import cz.cvut.fel.pjv.fileIO.Coordinate2D;
import cz.cvut.fel.pjv.fileIO.LevelData;
import cz.cvut.fel.pjv.fileIO.YamlIO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.controller.Constants.*;


/**
 * This class is a separate application which makes possible creating positions of obstacles, enemies, fuel barrels,
 * life adders and level enhancers in levelData for use in main Application.
 */
public class Editor extends Application {
	private static final Logger LOGGER = Logger.getLogger(Editor.class.getName());

	private Canvas canvas;
	private VBox vBox;
	private LevelData levelData;
	private Image obstacleImage, enemyImage, fuelBarrelImage, lifeAdderImage, levelEnhancerImage, backgroundImg, buttonBackImage;
	private boolean isObstacle, isEnemy, isFuelBarrel, isLifeAdder, isLevelEnhancer;
	private Pane pane;

	@Override
	public void start(Stage primaryStage) {
		canvas = new Canvas(WIDTH, HEIGHT);

		//Initialize level data
		levelData = YamlIO.loadLevelDataYaml();
		assert levelData != null;
		levelData.getLifeAddersPositions().clear();
		levelData.getFuelBarrelsPositions().clear();
		levelData.getObstaclesPositions().clear();
		levelData.getLevelEnhancersPositions().clear();
		levelData.getEnemiesPositions().clear();

		//load all images
		loadImages();

		//set up background
		BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);

		pane = new Pane(canvas);
		pane.setBackground(background);

		handleMousePress();

		Scene scene = new Scene(pane);

		primaryStage.setTitle("Space exploration engine - level editor");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> Platform.exit());

		//create second windows for buttons
		createSecondaryStage();
	}

	public static void main(String[] args) {
		launch(args);
	}


	private void loadImages() {
		try {
			buttonBackImage = new Image("/button_back.png", 440, 130, true, false, true);
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

	private void handleMousePress() {
		pane.setOnMousePressed(event -> {
			LOGGER.log(Level.INFO, "Mouse pressed");
			if (isObstacle) {
				addObstacle(event.getX(), event.getY());
				levelData.getObstaclesPositions().add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isEnemy) {
				addEnemy(event.getX(), event.getY());
				levelData.getEnemiesPositions().add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isFuelBarrel) {
				addFuelBarrel(event.getX(), event.getY());
				levelData.getFuelBarrelsPositions().add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isLevelEnhancer) {
				addLevelEnhancer(event.getX(), event.getY());
				levelData.getLevelEnhancersPositions().add(new Coordinate2D(event.getX(), event.getY()));
			} else if (isLifeAdder) {
				addLifeAdder(event.getX(), event.getY());
				levelData.getLifeAddersPositions().add(new Coordinate2D(event.getX(), event.getY()));
			}
		});
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

	private void createSecondaryStage() {
		Stage secondaryStage = new Stage();
		Group group = new Group();
		Scene scene = new Scene(group, 380, 130, Color.GRAY);
		createSecondaryScreenNodes();
		ImageView backImageView = new ImageView();
		backImageView.setImage(buttonBackImage);
		group.getChildren().add(backImageView);

		group.getChildren().add(vBox);
		secondaryStage.setTitle("Choose which you want to add");
		secondaryStage.setScene(scene);
		secondaryStage.setResizable(false);
		secondaryStage.show();
	}

	private void createSecondaryScreenNodes() {
		Insets insets = new Insets(10);
		vBox = new VBox();
		HBox hBox1 = new HBox(30);
		hBox1.setPadding(insets);
		hBox1.setAlignment(Pos.TOP_LEFT);
		HBox hBox2 = new HBox(30);
		hBox2.setPadding(insets);
		hBox2.setAlignment(Pos.TOP_LEFT);

		Button obstacleButton = new Button("OBSTACLE");
		obstacleButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		obstacleButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Obstacle button was used");
			isObstacle = true;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = false;

		});

		Button enemyButton = new Button("ENEMY");
		enemyButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		enemyButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Enemy button used.");
			isObstacle = false;
			isEnemy = true;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = false;
		});

		Button fuelBarrelButton = new Button("FUEL");
		fuelBarrelButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		fuelBarrelButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Fuel barrel button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = true;
			isLifeAdder = false;
			isLevelEnhancer = false;
		});

		Button lifeAdderButton = new Button("LIFE");
		lifeAdderButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		lifeAdderButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Life adder button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = true;
			isLevelEnhancer = false;
		});

		Button levelEnhancerButton = new Button("LEVEL");
		levelEnhancerButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		levelEnhancerButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Level enhancer button used.");
			isObstacle = false;
			isEnemy = false;
			isFuelBarrel = false;
			isLifeAdder = false;
			isLevelEnhancer = true;

		});

		Button saveButton = new Button("SAVE");
		saveButton.setStyle("-fx-font: 22 impact; -fx-base: #a2a9fa;");
		saveButton.setOnAction(event -> {
			LOGGER.log(Level.INFO, "Save button used.");
			YamlIO.saveLevelDataYaml(levelData);
			Platform.exit();
		});

		hBox1.getChildren().addAll(obstacleButton, enemyButton, fuelBarrelButton);
		hBox2.getChildren().addAll(lifeAdderButton, levelEnhancerButton, saveButton);
		vBox.getChildren().addAll(hBox1, hBox2);
	}
}
