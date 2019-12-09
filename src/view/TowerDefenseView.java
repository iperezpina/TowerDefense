
package view;

import java.io.File;

import controller.Player;
import controller.TowerDefenseController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Enemy;
import model.EnemySpawner;
import model.FileReader;
import model.RoundManager;
import model.TileMap;
import model.TimerAll;
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

	// Variables here are in charge of various view stuff
	public static Canvas canvas;
	public static TowerDefenseController tdc = new TowerDefenseController();
	private static TowerHolder towers;
	private final int MAX_X = 800, MAX_Y = 600;
	private TileMap tm;
	private FileReader fr;
	private RoundManager rm;
	private Background bgd2 = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
	private Background bgd3 = new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	private Media media = new Media(new File("src/Sounds/whoosh.wav").toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(media);
	private static Player currPlayer = new Player();
	private Label rightLabel;
	private Label roundLabel;
	private BorderPane bpRightButtons;
	
	
	//Variables here relate to the gui elements
	FlowPane fp = new FlowPane();
	Label towerNameLabel = new Label("Tower Name Here");
	Label killCountLabel = new Label("Enemies Destroyed: 666");
	Button sellButton = new Button("Sell for $150");
	
	BorderPane bpUpgrade = new BorderPane();
	Label upgradeNameLabel = new Label("Cool upgrade name");
	Label upgradeDetailLabel = new Label("Description of the upgrade here, does not have to be very specific.");
	Label upgradeCostLabel = new Label("$123");
	
	Button goButton = new Button("GO");
	BorderPane bpBottom = new BorderPane();
	
	
  // Other variables below
	// Essentially the map of the level
	private int[][] tileMap = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 } };

	public static void main(String args[]) {
		launch(args);
	}

	/**
	 * The basic setup of the application
	 */
	public void start(Stage mainStage) throws Exception {
		// Add the view to the controller class
		currPlayer.setTdv(this);
		tdc.setTdv(this);

		TimerAll.pause();

		// Setting up title and icon for app
		mainStage.setTitle("Dragon Force Defense");
		mainStage.getIcons().add(new Image("Images/Fireball.png"));

		// Creating a borderpane
		BorderPane bp = new BorderPane();

		// Applying an hbox that contains a canvas that will draw everything
		HBox hbox = new HBox();
		hbox.setPrefWidth(640);
		canvas = new Canvas(640, 480);
		// GraphicsContext gc = canvas.getGraphicsContext2D();
		hbox.getChildren().add(canvas);
		setupMainGrid(hbox, canvas);

		// Rightpane will have the info about the player and where the available towers
		// will be located
		rightLabel = new Label("Money: " + currPlayer.getCoins() + "\nHealth: " + currPlayer.getHP());
		VBox rightPane = new VBox(rightLabel);
		// VBox rightPane = new VBox(new Label("Health: " + currPlayer.getHP() +
		// "\nCoins: " + currPlayer.getCoins()));

		rightPane.resize(160, 480);
		rightPane.setPrefWidth(160);
		rightPane.setBackground(bgd2);
		drawRightPane(rightPane);
		
		//Bottompane will show information on a selected tower, upgrades for that tower and a pause/start/2x speed button
		roundLabel = new Label("Round 0");
		VBox bottomPane = new VBox(roundLabel);
		bottomPane.resize(800, 120);
		bottomPane.setPrefHeight(120);
		bottomPane.setBackground(bgd3);
		drawBottomPane(bottomPane);

		// Add the nodes to the borderpane
		bp.setCenter(hbox);
		bp.setRight(rightPane);
		bp.setBottom(bottomPane);

		// Add to a scene and show the stage
		Scene scene = new Scene(bp, MAX_X, MAX_Y);
		mainStage.setScene(scene);
		mainStage.show();

		// TODO below doesnt work
		/*mainStage.setOnCloseRequest(closeEvent -> {
		       TimerAll.cancel();  
		});*/

		//Loops the music for forever

		mediaPlayer.setAutoPlay(true);
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

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, currPlayer.placeTower);
		// canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.debug);
		tm = new TileMap(tileMap);
		towers = new TowerHolder(tm);

		Enemy e = new Enemy("ghost", tm.GetTile(0, 1), 32, 32, 4, 5, tm);
		fr = new FileReader(tm, tm.GetTile(0, 1));
		fr.read("src/level1.txt");
		rm = new RoundManager(fr.getEnemies(), 5f, this);
		
		tm.update();
		towers.update();
		rm.update();
	}

	/**
	 * Adds the tower imgs to the rightPane. Only 8 towers at the moment.
	 * 
	 * @param rightPane
	 */
	private void drawRightPane(VBox rightPane) {
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(50, 0, 0, 20));
		gp.setHgap(50);
		gp.setVgap(50);
		int row = 0;
		for (int i = 0; i < 8; i++) {
			Image towerImg = new Image("Images/tower" + (i + 1) + ".png");
			ImageView img = new ImageView(towerImg);
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, currPlayer.chooseTower);
			if (i % 2 == 0) {
				gp.add(img, 0, row);
			} else {
				gp.add(img, 1, row);
				row++;
			}
		}
		rightPane.getChildren().add(gp);
	}

	public Label getRightLabel() {
		return rightLabel;
	}

	/**
	 * Adds the tower info and play button to the bottomPane.
	 * 
	 * @param bottomPane
	 */
	private void drawBottomPane(VBox bottomPane) {
		BorderPane bpBottom = new BorderPane();

		// Left part that has some info about the tower
		FlowPane fp = new FlowPane();
		Label towerNameLabel = new Label("Tower Name Here");
		Label killCountLabel = new Label("Enemies Destroyed: 666");
		Button sellButton = new Button("Sell for $150");
		fp.getChildren().add(towerNameLabel);
		fp.getChildren().add(killCountLabel);
		fp.getChildren().add(sellButton);
		fp.setOrientation(Orientation.VERTICAL);
		fp.setPrefWidth(200);
		fp.setStyle("-fx-background-color: Yellow; -fx-border-radius: 5px; -fx-border-width: 5px;"
				+ "-fx-border-color: Gold;");

		// The center part that has the available upgrade
		BorderPane bpUpgrade = new BorderPane();
		Label upgradeNameLabel = new Label("Cool upgrade name");
		Label upgradeDetailLabel = new Label("Description of the upgrade here, does not have to be very specific.");
		upgradeDetailLabel.setPrefWidth(440);
		Label upgradeCostLabel = new Label("$123");
		// Add the items to the bp
		bpUpgrade.setTop(upgradeNameLabel);
		bpUpgrade.setCenter(upgradeDetailLabel);
		bpUpgrade.setBottom(upgradeCostLabel);
		// Setting their alignment
		bpUpgrade.setAlignment(bpUpgrade.getTop(), Pos.TOP_LEFT);
		bpUpgrade.setAlignment(bpUpgrade.getCenter(), Pos.CENTER_LEFT);
		bpUpgrade.setAlignment(bpUpgrade.getBottom(), Pos.BOTTOM_RIGHT);
		bpUpgrade.setStyle("-fx-background-color: CadetBlue; -fx-border-radius: 5px; -fx-border-width: 5px;"
				+ "-fx-border-color: black;");
		bpUpgrade.setPrefSize(200, 100);

		
		bpRightButtons = new BorderPane();
		
		//Go button that will start the round, will turn into a x2 and then a pause.

		drawGoButton();

		
		//Add the three big items into the borderpane
		bpBottom.setLeft(fp);
		bpBottom.setCenter(bpUpgrade);
		bpBottom.setRight(bpRightButtons);
		
		
		//Adding to bottomPane
		bottomPane.getChildren().add(bpBottom);
		}
	
	public BorderPane getBPRight() {
		return bpRightButtons;
	}
	
	public void drawExtraButtons() {
		Button pause = new Button("Pause");
		pause.setPrefSize(150, 50);
		pause.setStyle("-fx-background-color:tomato; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
				"-fx-border-color:red;");
		bpRightButtons.setTop(pause);
		pause.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.pause);
		
		Button fast = new Button("x2");
		fast.setPrefSize(150, 50);
		fast.setStyle("-fx-background-color:cornflowerblue; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
				"-fx-border-color:blue;");
		bpRightButtons.setBottom(fast);
		
	}
	
	public void drawGoButton() {
		Button goButton = new Button("GO");
		goButton.setPrefSize(150, 50);
		goButton.setStyle("-fx-background-color:springgreen; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
				"-fx-border-color: Green;");
		goButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.resume);
		
		bpRightButtons.setTop(goButton);
		bpRightButtons.setBottom(null);
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

	public Label getRoundLabel() {
		return roundLabel;
	}

	public void setRoundLabel(Label roundLabel) {
		this.roundLabel = roundLabel;
	}
	
	public void play() {
		//TimerAll.play();
		TimerAll.run();
	}
	public void pause() {
		TimerAll.pause();
		//TimerAll.run();
	}


}
