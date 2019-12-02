package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.POS;
import model.TowerDefenseModel;
import view.TowerDefenseView;

public class TowerDefenseController {

//	private static TowerDefenseModel model = new TowerDefenseModel();
//	private static TowerDefenseView view;
//	
//	private static BorderPane bp;
//	
//	public TowerDefenseController(TowerDefenseView view) {
//		this.view = view;
//	}
//	
//	//Event handlers for all of the GUI STUFF
//	
//	/**
//	 * Add the onclick for when a startgame button is pressed
//	 */
//	public EventHandler<ActionEvent> startGame = new EventHandler<ActionEvent>() {
//		/**
//		 * Starts the game
//		 */
//		@Override
//		public void handle(ActionEvent event) {
//			System.out.println("Starting new game");
//			loadLevel("level1");
//			drawEnemy(bp);
//		}
//	};
//	
//	/**
//	 * Add the onclick for when a options button is pressed
//	 */
//	public EventHandler<ActionEvent> options = new EventHandler<ActionEvent>() {
//		/**
//		 * Opens options menu
//		 */
//		@Override
//		public void handle(ActionEvent event) {
//			System.out.println("Opening options");
//			loadOptions("Options");
//
//		}
//	};
//	
//	/**
//	 * Add the onclick for when a quit button is pressed
//	 */
//	public EventHandler<ActionEvent> quit = new EventHandler<ActionEvent>() {
//		/**
//		 * Quits the application
//		 */
//		@Override
//		public void handle(ActionEvent event) {
//			System.out.println("Quitting the game");
//			Platform.exit();
//
//		}
//	};
//	
//	/**
//	 * Add the onclick for when a quit button is pressed
//	 */
//	public EventHandler<ActionEvent> backToMain = new EventHandler<ActionEvent>() {
//		/**
//		 * Quits the application
//		 */
//		@Override
//		public void handle(ActionEvent event) {
//			try {
//				view.start(view.stage);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	};
//	
//	/**
//	 * Add the onclick for when a quit button is pressed
//	 */
//	public EventHandler<MouseEvent> debug1 = new EventHandler<MouseEvent>() {
//
//		@Override
//		public void handle(MouseEvent event) {
//			// TODO Auto-generated method stub
//			System.out.print("X: " + event.getX());
//			System.out.print("\tY: " + event.getY() + "\n");
//		}
//	};
//	
//	private void loadLevel(String levelName) {
//		bp = new BorderPane();
//		bp.addEventHandler(MouseEvent.MOUSE_CLICKED, debug1);
//		loadLevelImages(levelName, bp);
//		Scene scene = new Scene(bp, 800,600);
//		view.stage.setScene(scene);
//		view.stage.setTitle("Dragon Force Defense: " + levelName);
//		view.stage.getIcons().add(new Image("Images\\Fireball.png"));
//		view.stage.show();
//	}
//	
//	public void loadLevelImages(String levelName, Pane pane) {
//		ImageView imgView = new ImageView();
//		imgView.setImage(new Image("Images\\" + levelName + ".png"));
//		pane.getChildren().add(imgView);
//		
//	}
//	
//	private void loadOptions(String levelName) {
//		bp = new BorderPane();
//		bp.addEventHandler(MouseEvent.MOUSE_CLICKED, debug1);
//		
//		Button back = new Button("BACK");
//		back.addEventHandler(ActionEvent.ACTION, backToMain);
//		loadLevelImages(levelName, bp);
//		bp.setBottom(back);
//		Scene scene = new Scene(bp, 800,600);
//		view.stage.setScene(scene);
//		view.stage.setTitle("Dragon Force Defense: " + levelName);
//		view.stage.getIcons().add(new Image("Images\\Fireball.png"));
//		view.stage.show();
//	}
//	
//	public void drawEnemy(Pane pane) {
//		Circle circle = new Circle(0, 120, 20);
//		circle.setFill(Color.GHOSTWHITE);
//		pane.getChildren().add(circle);
//		
//		//This is the path for level1
//		POS start = new POS(0, 5);
//		POS end = new POS(630, 5);
//		moveTo(start, end, circle, pane);
//		
//		start = new POS(630, 5);
//		end = new POS(630, 230);
//		moveTo(start, end, circle, pane);
//		
//		start = new POS(630, 230);
//		end = new POS(80, 230);
//		moveTo(start, end, circle, pane);
//		
//		start = new POS(80, 230);
//		end = new POS(80, 430);
//		moveTo(start, end, circle, pane);
//		
//		start = new POS(80, 430);
//		end = new POS(800, 430);
//		moveTo(start, end, circle, pane);
//
//	}
//	
//	public void moveTo(POS start, POS end, Circle circle, Pane pane) {
//		pane.getChildren().remove(circle);
//		Circle tempCircle = new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
//		
//		while (!start.isThere(end)) {
//			start.move(end);
//			tempCircle = new Circle(0 + start.getX(), 120 + start.getY(), 20);
//			pane.getChildren().add(tempCircle);
//
//		}
//	}
}
