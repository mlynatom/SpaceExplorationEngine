package cz.cvut.fel.pjv.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main graphic class.
 */
public class SpaceExplorationEngine extends Application {

	private static final double WIDTH = 1024, HEIGHT = 600;
	private static final boolean MAXIMIZED = true;

	private Group rootGroup;
	private static double maxWidth;
	private static double maxHeight;


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Space exploration engine");
		rootGroup = new Group();
		maxWidth = primaryStage.getMaxWidth();
		maxHeight = primaryStage.getMaxHeight();
		Scene scene = new Scene(rootGroup, WIDTH, HEIGHT, Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setMaximized(MAXIMIZED);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
