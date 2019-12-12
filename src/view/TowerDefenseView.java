
package view;

import java.io.File;
import java.util.ArrayList;
import controller.Player;
import controller.ResourceManager;
import controller.TowerDefenseController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.layout.StackPane;
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
import model.Upgrade;

/**
 * This is the View
 * 
 * 
 * @author Ivan, Marisa, Laura, Albert
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
	private Media intro = new Media(new File("src/Sounds/introsong.wav").toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(intro);
	private MediaPlayer mediaPlayer2 = new MediaPlayer(media);
	private static Player currPlayer = new Player();
	private Label rightLabel;
	private Label roundLabel;
	private BorderPane bpRightButtons;
	private boolean createStart = false;

	// Variables here relate to the gui elements
	private Stage mainStage;
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

	StackPane sp;

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
	// mediumMap
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
	// HardMap
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

	/**
	 * Launches the Game !
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void main(String args[]) {
		launch(args);
	}

	/**
	 * The basic setup of the application
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void start(Stage tempStage) throws Exception {
		mainStage = tempStage;
		Button newGame = new Button("New Game");
		sp = new StackPane();
		newGame.setOnAction((event) -> {
			newGame.setVisible(false);
			sp.getChildren().remove(0);
			createLevelSelect(sp);
		});
		sp.getChildren().add(new ImageView(ResourceManager.startScreenImg));
		sp.getChildren().add(newGame);
		sp.getChildren().get(1).toFront();

		// Setting up title and icon for app
		mainStage.setTitle("Highway outta HELL");
		mainStage.getIcons().add(new Image("Images/logo.png"));
		Scene tempScene = new Scene(sp, 800, 600);
		mainStage.setScene(tempScene);
		mainStage.show();
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

	}

	/**
	 * In charge of creating the three buttons that will choose the difficulty
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param sp, the stackpane that the buttons will be added on.
	 * @return n/a
	 * @throws n/a
	 */
	private void createLevelSelect(StackPane sp) {
		BorderPane bpSelected = new BorderPane();

		Button easyButton = new Button("EASY");
		easyButton.setPrefSize(70, 40);
		easyButton.setOnAction(buttonHandler);

		Button mediumButton = new Button("MEDIUM");
		mediumButton.setPrefSize(70, 40);
		mediumButton.setOnAction(buttonHandler);

		Button hardButton = new Button("HARD");
		hardButton.setPrefSize(70, 40);
		hardButton.setOnAction(buttonHandler);

		sp.getChildren().add(easyButton);
		sp.getChildren().add(mediumButton);
		sp.getChildren().add(hardButton);

		// Puts the image in the back of the stackpane
		sp.getChildren().get(0).toBack();

		// Adds the three buttons that when pressed create a start game button, and sets
		// a certain difficulty
		// EASY
		sp.getChildren().get(1).setTranslateX(-100);
		sp.getChildren().get(1).setLayoutX(0);
		sp.getChildren().get(1).setLayoutY(0);

		// MEDIUM
		sp.getChildren().get(2).setLayoutX(0);
		sp.getChildren().get(2).setLayoutY(0);

		// HARD
		sp.getChildren().get(3).setTranslateX(100);
		sp.getChildren().get(3).setLayoutX(0);
		sp.getChildren().get(3).setLayoutY(0);

	}

	/**
	 * Event handler for the three difficulty buttons, spawns a startGame button and
	 * sets difficulty
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent event
	 * @return n/a
	 * @throws n/a
	 */
	private EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button temp = (Button) event.getSource();
			if (temp.getText().equals("EASY")) {
				levelDifficulty = 0;
				spawnStartGameButton();
			} else if (temp.getText().equals("MEDIUM")) {
				levelDifficulty = 1;
				spawnStartGameButton();
			} else if (temp.getText().equals("HARD")) {
				levelDifficulty = 2;
				spawnStartGameButton();
			}
		}

	};

	/**
	 * The function for startGame event, loads the basic game.
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	protected void spawnStartGameButton() {
		if (!createStart) {
			createStart = true;
			if (sp != null) {
				Button startGame = new Button("START GAME");
				startGame.setOnAction((event) -> {
					startGame();
				});
				startGame.setPrefSize(100, 40);
				sp.getChildren().add(startGame);
				sp.getChildren().get(4).setTranslateY(200);

			}

		}
	}

	/**
	 * Starts the Game!
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void startGame() {
		mediaPlayer.stop();

		// Add the view to the controller class
		currPlayer.setTdv(this);
		tdc.setTdv(this);

		TimerAll.play();

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
		// rightLabel = new Label("Money: " + currPlayer.getCoins() + "\nHealth: " +
		// currPlayer.getHP());
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

		// Loops the music for forever
		mediaPlayer2.setAutoPlay(true);
		mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
	}

	/**
	 * Used to test the basic gameplay stuff, so far spawning enemies and rendering
	 * the level, and enemies
	 * 
	 * @param hbox
	 * @param canvas
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @return n/a
	 * @throws n/a
	 */
	public void setupMainGrid(HBox hbox, Canvas canvas) {

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, currPlayer.placeTower);
		// canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.debug);

		// The start tile for easyMap is (0, 1), mediumMap is (10, 7), hardMap is (0, 5)
		// This choose which map to go on
		if (levelDifficulty == 0) {
			tm = new TileMap(easyMap);
			towers = new TowerHolder(tm);
			fr = new FileReader(tm, tm.GetTile(0, 1));
		} else if (levelDifficulty == 1) {
			tm = new TileMap(mediumMap);
			towers = new TowerHolder(tm);
			fr = new FileReader(tm, tm.GetTile(10, 7));
		} else if (levelDifficulty == 2) {
			tm = new TileMap(hardMap);
			towers = new TowerHolder(tm);
			fr = new FileReader(tm, tm.GetTile(0, 5));
		}

		fr.read("src/level1.txt");
		System.out.println("EnemyList size: " + fr.getEnemies().size());
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
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @return n/a
	 * @throws n/a
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

	// Getter
	public Label getRightLabel() {
		return rightLabel;
	}

	/**
	 * Sets the Towers specification
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String name, int enemies, int cost, int range, int sell, Upgrade
	 *               currentUpgrade
	 * @return n/a
	 * @throws n/a
	 */
	public void setTowerSpecification(String name, int enemies, int cost, int range, int sell, Upgrade currentUpgrade) {
		// Tower Info stuff (Yellow panel)
		towerNameLabel.setText("Tower Name: " + name);
		killCountLabel.setText("Enemies Destroyed: " + enemies);
		upgradeCostLabel.setText("$" + cost + " ");
		rangeLabel.setText("Current Range: " + range);
		sellButton.setText("Sell for $" + sell);

		// here I sell them
		sellButton.setOnAction(Player.sellTower);

		// Upgrade Info stuff (Blue panel)
		upgradeNameLabel.setText(currentUpgrade.getUpgradeName());
		upgradeDetailLabel.setText(currentUpgrade.getUpgradeDetail());
		if (currentUpgrade.getUpgradeCost() <= 0) {
			upgradeCostLabel.setText("");
		} else {
			upgradeCostLabel.setText(currentUpgrade.getUpgradeCost() + "");
		}

	}

	/**
	 * Sets all the gui elements to blank to avoid bugs
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void setAllBlank() {
		// Tower Info stuff (Yellow panel)
		towerNameLabel.setText("");
		killCountLabel.setText("");
		rangeLabel.setText("");
		sellButton.setText("Sell for $");
		upgradeCostLabel.setText("");

		// Upgrade Info stuff (Blue panel)
		upgradeNameLabel.setText("");
		upgradeDetailLabel.setText("");
		upgradeCostLabel.setText("");

	}

	/**
	 * Adds the tower info and play button to the bottomPane.
	 * 
	 * @param bottomPane
	 * @author Ivan, Marisa, Laura, Albert
	 * @return n/a
	 * @throws n/a
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
		rangeLabel = new Label();
		rangeLabel.setStyle("-fx-font: 15 arial;");
		sellButton = new Button("Sell for $  ");
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
		bpUpgrade.addEventHandler(MouseEvent.MOUSE_CLICKED, currPlayer.upgradeTower);
		upgradeNameLabel.setText("");
		upgradeDetailLabel.setText("");
		upgradeDetailLabel.setPrefWidth(440);

		// Setting font for bpUpgrade labels to 15 arial
		upgradeNameLabel.setStyle("-fx-font: 15 arial;");
		upgradeDetailLabel.setStyle("-fx-font: 15 arial;");

		upgradeCostLabel.setText("");
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

	// Getter
	public BorderPane getBPRight() {
		return bpRightButtons;
	}

	/**
	 * Gets the draw Extra Buttons
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
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

	/**
	 * Draws the go button
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void drawGoButton() {
		Button goButton = new Button("GO");
		goButton.setPrefSize(150, 50);

		goButton.setStyle("-fx-background-color:springgreen; -fx-border-radius: 2px; -fx-border-width: 2px;"
				+ "-fx-border-color: Green;");
		goButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.go);

		bpRightButtons.setTop(goButton);
		bpRightButtons.setBottom(null);
	}

//	public void drawPlayButton() {
//		Button play = new Button("Play");
//		play.setPrefSize(150, 50);
//		play.setStyle("-fx-background-color:springgreen; -fx-border-radius: 2px; -fx-border-width: 2px;" + 
//				"-fx-border-color: Green;");
//		play.addEventHandler(MouseEvent.MOUSE_CLICKED, tdc.resume);
//		
//		bpRightButtons.setTop(play);
//		bpRightButtons.setBottom(null);
//		
//	}

	/**
	 * Calls an alert if the game is won
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void gameWon() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Won");
		alert.setHeaderText("Winner Winner Chicken Dinner");
		alert.setContentText("good job!");
		alert.setOnHidden(evt -> Platform.exit());

		alert.show();
	}

	/**
	 * Calls an alert if a game is lost
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void gameOver() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Try Again!");
		alert.setContentText("Shaking my Head");
		alert.setOnHidden(evt -> Platform.exit());

		alert.show();

	}

	// Getter
	public TileMap getTm() {
		return tm;
	}

	// Setter
	public void setTm(TileMap tm) {
		this.tm = tm;
	}

	// Getter
	public static TowerHolder getTowers() {
		return towers;
	}

	// Setter
	public static void setTowers(TowerHolder towers) {
		TowerDefenseView.towers = towers;
	}

	// Getter
	public Label getRoundLabel() {
		return roundLabel;
	}

	// Setter
	public void setRoundLabel(Label roundLabel) {
		this.roundLabel = roundLabel;
	}

	// Setter
	public void startPlay() {
		TimerAll.runTimer();
	}

	// Setter
	public void play() {
		TimerAll.play();
	}

	// Setter
	public void pause() {
		TimerAll.pause();
	}

	/**
	 * Updates the player information with the money and health
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void updatePlayerInfo(int money, int health) {
		moneyLabel.setText("Money:\t" + money);
		healthLabel.setText("Health:\t" + health);
		updateTowerLabel("");
	}

	/**
	 * Updates the Tower lable with all the right things
	 * 
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public void updateTowerLabel(String newText) {
		towerLabel.setText(newText);
	}

}
