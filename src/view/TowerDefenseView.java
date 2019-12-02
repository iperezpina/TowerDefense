package view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Enemy;
import model.EnemySpawner;
import model.TileMap;

public class TowerDefenseView extends Application {
	public static Canvas canvas;

	// Essentially the map of the level
	private int[][] tileMap = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 } };

	private final int MAX_X = 640, MAX_Y = 480;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// Setting up title and icon for app
		mainStage.setTitle("Dragon Force Defense");
		mainStage.getIcons().add(new Image("Images/Fireball.png"));

		// Applying an hbox that contains a canvas that will draw everything
		HBox hbox = new HBox();
		canvas = new Canvas(MAX_X, MAX_Y);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		hbox.getChildren().add(canvas);

		setupMainGrid(hbox, canvas);
		Scene scene = new Scene(hbox, MAX_X, MAX_Y);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void setupMainGrid(HBox hbox, Canvas canvas) {

		TileMap tm = new TileMap(tileMap);
		Enemy e = new Enemy(new Image("Images/enemy.png"), tm.GetTile(0, 1), 32, 32, 8, tm);
		EnemySpawner es = new EnemySpawner(5, 3f, e);
		//Enemy e2 = new Enemy(new Image("Images/enemy2.png"), tm.GetTile(0, 1), 32, 32, 4, tm);

		tm.update();
		es.update();
		//e.update();
		//e2.update();
		


	}

}