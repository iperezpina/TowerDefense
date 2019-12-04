package view;

import java.io.File;

import controller.TowerDefenseController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Enemy;
import model.EnemySpawner;
import model.TileMap;
import model.TowerHolder;

/**
 * 
 * @author Alberto Villareal, Laura [Last Name], Ivan [Last Name], and Marissa
 *         [Last Name]
 * 
 *         Summary: [Summary goes here]
 *
 */
public class TowerDefenseView extends Application {
	public static Canvas canvas;
	public static TowerDefenseController tdc = new TowerDefenseController();
	private static TowerHolder towers;

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

	private final int MAX_X = 800, MAX_Y = 600;
	private TileMap tm;
	private Background bgd2 = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
	private Background bgd3 = new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	
	private Media media = new Media(new File("src/Sounds/whoosh.wav").toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(media);

	public static void main(String args[]) {
		launch(args);
	}

	/**
	 * The basic setup of the application
	 */
	public void start(Stage mainStage) throws Exception {
		tdc.setTdv(this);
		
		// Setting up title and icon for app
		mainStage.setTitle("Dragon Force Defense");
		mainStage.getIcons().add(new Image("Images/Fireball.png"));
		
		BorderPane bp = new BorderPane();
		
		// Applying an hbox that contains a canvas that will draw everything
		HBox hbox = new HBox();
		hbox.setPrefWidth(640);
		canvas = new Canvas(640, 480);
		//GraphicsContext gc = canvas.getGraphicsContext2D();
		hbox.getChildren().add(canvas);
		setupMainGrid(hbox, canvas);
		
		VBox rightPane = new VBox(new Label("Money: $1000\nHealth: 100"));
		rightPane.resize(160, 480);
		rightPane.setPrefWidth(160);
		rightPane.setBackground(bgd2);
		drawRightPane(rightPane);
		
		VBox bottomPane = new VBox(new Label("Tower Info/Upgrades"));
		bottomPane.resize(800, 120);
		bottomPane.setPrefHeight(120);
		bottomPane.setBackground(bgd3);
		
		bp.setCenter(hbox);
		bp.setRight(rightPane);
		bp.setBottom(bottomPane);
		
		Scene scene = new Scene(bp, MAX_X, MAX_Y);
		mainStage.setScene(scene);
		mainStage.show();
		
		
		mediaPlayer.setAutoPlay(true);
		
		//Loops the music for forever
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}

	/**
	 * Used to test the basic gameplay stuff, so far spawning enemies and rendering
	 * the level, and enemies
	 * 
	 * @param hbox
	 * @param canvas
	 */
	public void setupMainGrid(HBox hbox, Canvas canvas) {

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.debug);
		tm = new TileMap(tileMap);
		towers = new TowerHolder(tm);
		Enemy e = new Enemy(new Image("Images/enemy.png"), tm.GetTile(0, 1), 32, 32, 1, tm);
		EnemySpawner es = new EnemySpawner(5, 5f, e);

		tm.update();
		towers.update();
		es.update();

	}
	
	/**
	 * Adds the tower imgs to the rightPane.  Only 8 towers at the moment.
	 * @param rightPane
	 */
	private void drawRightPane(VBox rightPane) {
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(50,0,0,20));
		gp.setHgap(50);
		gp.setVgap(50);
		int row = 0;
		for(int i = 0; i < 8; i++) {
			Image towerImg = new Image("Images/tower" + (i+1) + ".png");
			ImageView img = new ImageView(towerImg);
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.chooseTower);
			if (i % 2 == 0) {
				gp.add(img, 0, row);
			}
			else {
				gp.add(img, 1, row);
				row++;
			}
		}
		rightPane.getChildren().add(gp);
	}

	public TileMap getTm() {
		return tm;
	}

	public void setTm(TileMap tm) {
		this.tm = tm;
	}

	public static TowerHolder getTowers() {
		return towers;
	}

	public static void setTowers(TowerHolder towers) {
		TowerDefenseView.towers = towers;
	}
	
	

}