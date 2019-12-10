
package view;

import java.io.File;
import java.util.ArrayList;

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
	private int levelDifficulty = 0;
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


	// Variables here relate to the gui elements
	private ArrayList<Enemy> enemiesList;
	FlowPane fp = new FlowPane();
	Label towerNameLabel = new Label();
	Label killCountLabel = new Label();
	Label rangeLabel = new Label();
	Button sellButton = new Button("Sell for $150");

	BorderPane bpUpgrade = new BorderPane();
	Label upgradeNameLabel = new Label("Cool upgrade name");
	Label upgradeDetailLabel = new Label("Description of the upgrade here, does not have to be very specific.");
	Label upgradeCostLabel = new Label();

	Button goButton = new Button("GO");
	BorderPane bpBottom = new BorderPane();
	
	Label moneyLabel = new Label();
	Label healthLabel = new Label();
	Label towerLabel = new Label();
	FlowPane playerFP = new FlowPane();
	
	

	// Other variables below
	// Essentially the map(s) of the level(s)
	private int[][] easyMap = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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

	private int[][] mediumMap = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private int[][] hardMap = { { 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2 },
			{ 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 } };

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

		TimerAll.play();

		// Setting up title and icon for app
		mainStage.setTitle("Highway outta HELL");
		mainStage.getIcons().add(new Image("Images/logo.png"));

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
		playerFP.setOrientation(Orientation.VERTICAL);
		playerFP.setPrefHeight(70);
		moneyLabel.setStyle("-fx-text-fill:gold; -fx-font: 14px Tahoma;");
		healthLabel.setStyle("-fx-text-fill:springgreen; -fx-font: 14px Tahoma;");
		towerLabel.setStyle("-fx-text-fill:crimson");
		playerFP.getChildren().add(moneyLabel);
		playerFP.getChildren().add(healthLabel);
		playerFP.getChildren().add(towerLabel);
		//rightLabel = new Label("Money: " + currPlayer.getCoins() + "\nHealth: " + currPlayer.getHP());
		updatePlayerInfo(currPlayer.getCoins(), currPlayer.getHP());
		VBox rightPane = new VBox(playerFP);

		rightPane.resize(160, 480);
		rightPane.setPrefWidth(160);
		rightPane.setBackground(bgd2);
		drawRightPane(rightPane);

		// Bottompane will show information on a selected tower, upgrades for that tower
		// and a pause/start/2x speed button
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
		/*
		 * mainStage.setOnCloseRequest(closeEvent -> { TimerAll.cancel(); });
		 */

		// Loops the music for forever
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
		
		//The start tile for easyMap is (0, 1), mediumMap is (10, 7), hardMap is (0, 5)
		//This choose which map to go on
		if (levelDifficulty == 0) {
			tm = new TileMap(easyMap);
			towers = new TowerHolder(tm);
			fr = new FileReader(tm, tm.GetTile(0, 1));
		}
		else if(levelDifficulty == 1) {
				tm = new TileMap(mediumMap);
				towers = new TowerHolder(tm);
				fr = new FileReader(tm, tm.GetTile(10, 7));
		}
		else if(levelDifficulty == 2) {
			tm = new TileMap(hardMap);
			towers = new TowerHolder(tm);
			fr = new FileReader(tm, tm.GetTile(0, 5));
	}
		
		fr.read("src/level1.txt");
		rm = new RoundManager(fr.getEnemies(), 7f, this);
		tdc.setRm(rm);
		
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

	public void setTowerSpecification(String name, int enemies, int cost, int range, int sell) {
		towerNameLabel.setText("Tower Name: " +name);
		killCountLabel.setText("Enemies Destroyed: " + enemies);
		upgradeCostLabel.setText("$"+ cost + " ");
		rangeLabel.setText("Current Range: " + range);
		sellButton.setText("Sell for $" + sell);
	}
	
	public void setAllBlank() {
		towerNameLabel = new Label();
		killCountLabel = new Label();
		rangeLabel= new Label();
		sellButton = new Button("Sell for $  " );
		upgradeCostLabel = new Label();

	}

	/**
	 * Adds the tower info and play button to the bottomPane.
	 * 
	 * @param bottomPane
	 */
	private void drawBottomPane(VBox bottomPane) {

		bpBottom = new BorderPane();

		// Left part that has some info about the tower
		fp = new FlowPane();
		fp.setVgap(5);
		towerNameLabel = new Label();
		towerNameLabel.setStyle("-fx-font: 15 arial;");
		killCountLabel = new Label();
		killCountLabel.setStyle("-fx-font: 15 arial;");
		rangeLabel= new Label();
		rangeLabel.setStyle("-fx-font: 15 arial;");
		sellButton = new Button("Sell for $  " );
		fp.setHgap(20);

		fp.getChildren().add(towerNameLabel);
		fp.getChildren().add(killCountLabel);
		fp.getChildren().add(rangeLabel);
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

		upgradeCostLabel = new Label();
		upgradeCostLabel.setPrefWidth(70);
		upgradeCostLabel.setStyle("-fx-font: 24 arial;");
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

		// Go button that will start the round, will turn into a x2 and then a pause.

		drawGoButton();

		// Add the three big items into the borderpane
		bpBottom.setLeft(fp);
		bpBottom.setCenter(bpUpgrade);
		bpBottom.setRight(bpRightButtons);

		// Adding to bottomPane
		bottomPane.getChildren().add(bpBottom);
	}

	public BorderPane getBPRight() {
		return bpRightButtons;
	}

	public void drawExtraButtons() {
		Button pause = new Button("Pause");
		pause.setPrefSize(150, 50);
		pause.setStyle("-fx-background-color:tomato; -fx-border-radius: 2px; -fx-border-width: 2px;"
				+ "-fx-border-color:red;");
		bpRightButtons.setTop(pause);
		pause.setOnAction(tdc.pause);

		Button fast = new Button("x2");
		fast.setPrefSize(150, 50);
		fast.setStyle("-fx-background-color:cornflowerblue; -fx-border-radius: 2px; -fx-border-width: 2px;"
				+ "-fx-border-color:blue;");
		fast.setOnAction(tdc.timeTwo);
		bpRightButtons.setBottom(fast);

	}

	public void drawGoButton() {
		Button goButton = new Button("GO");
		goButton.setPrefSize(150, 50);

		goButton.setStyle("-fx-background-color:springgreen; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
				"-fx-border-color: Green;");
		goButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.go);
		

		bpRightButtons.setTop(goButton);
		bpRightButtons.setBottom(null);
	}
	
	public void drawPlayButton() {
		Button play = new Button("Play");
		play.setPrefSize(150, 50);
		play.setStyle("-fx-background-color:springgreen; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
				"-fx-border-color: Green;");
		play.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.resume);
		
		bpRightButtons.setTop(play);
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


	public void startPlay() {
		TimerAll.runTimer();
		//rm.startES();
	}
	
	public void play() {
		
		TimerAll.play();
	
		//rm.playES();
		
		/*for(Enemy e:enemiesList) {
			System.out.println(e);
			e.play();
		}*/

	}

	public void pause() {
		/*for(Enemy e:enemiesList) {
			e.pause();
		}*/
		TimerAll.pause();

		rm.pauseES();
		
	
	}
	
	public void updatePlayerInfo(int money, int health) {
		moneyLabel.setText("Money:\t" + money);
		healthLabel.setText("Health:\t" + health);
		updateTowerLabel("");
	}
	
	public void updateTowerLabel(String newText) {
		towerLabel.setText(newText);
	}
	

}
