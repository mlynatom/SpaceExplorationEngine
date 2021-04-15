package cz.cvut.fel.pjv.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private static final boolean MAXIMIZED = true;

	private Group rootGroup;
	private double maxWidth;
	private double maxHeight;
	private boolean up, left, right, space, escape;
	private Image background;
	private Scene scene;
	private HBox horizontalButtonBox;
	private Insets buttonBoxPadding;
	private Button playButton, helpButton, exitButton, exitSaveButton;
	private ImageView mainScreenBackground;


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

		createKeyHandlers();
		loadImages();
		createMainScreenNodes();
		addNodesToMainScreen();
	}

	public static void main(String[] args) {
		launch(args);
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
		background = new Image("/background.png", 1024, 600, true, false, true);
	}

	private void createMainScreenNodes() {
		horizontalButtonBox = new HBox(30);
		horizontalButtonBox.setLayoutY(HEIGHT-100);
		buttonBoxPadding = new Insets(0, 0, 10, 270);
		horizontalButtonBox.setPadding(buttonBoxPadding);

		playButton = new Button("PLAY");
		playButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		//playButton.setLayoutX(0);
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("play clicked");
			}
		});

		helpButton = new Button("HELP");
		helpButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("help clicked");

			}
		});

		exitButton = new Button("EXIT");
		exitButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("exit clicked");
				System.exit(1);
			}
		});

		exitSaveButton = new Button("EXIT AND SAVE");
		exitSaveButton.setStyle("-fx-font: 22 impact; -fx-base: #ffffff;");
		exitSaveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("exit and save clicked");
				System.exit(1);
			}
		});

		horizontalButtonBox.getChildren().addAll(playButton, helpButton, exitButton, exitSaveButton);

		mainScreenBackground = new ImageView();
		mainScreenBackground.setImage(background);
	}

	private void addNodesToMainScreen(){
		rootGroup.getChildren().add(mainScreenBackground);
		rootGroup.getChildren().add(horizontalButtonBox);
	}
}
