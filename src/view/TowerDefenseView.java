package view;

import controller.TowerDefenseController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TowerDefenseView extends Application {

	private TowerDefenseController tdc = new TowerDefenseController(this);
	public static Stage stage;
	
	@Override
	public void start(Stage mainStage) throws Exception {
		BorderPane bp = new BorderPane();
		StackPane sp = new StackPane();
		//Group group = new Group();
		//group.getChildren().add(sp);
		setupStartGame(bp);
		Scene scene = new Scene(bp, 800,600);
		mainStage.setScene(scene);
		mainStage.setTitle("Dragon Force Defense");
		mainStage.getIcons().add(new Image("Images\\Fireball.png"));
		stage = mainStage;
		stage.show();
	}
	
	public void setupStartGame(Pane pane) {
		ImageView imgView = new ImageView();
		imgView.setImage(new Image("Images\\startscreen.png"));
		
		pane.getChildren().add(imgView);
	
		FlowPane fp = new FlowPane();
		makeButton(fp, "Start Game", tdc.startGame);
		makeButton(fp, "Options", tdc.options);
		makeButton(fp, "Quit", tdc.quit);

		fp.setHgap(10);
		fp.setVgap(50);
		fp.setPadding(new Insets(200,10,10,800));
		fp.setAlignment(Pos.BASELINE_CENTER);;
		
		pane.getChildren().add(fp);
		
	}
	
	
	
	public void makeButton(GridPane gp, int x, int y, String info) {
		Button tempButton = new Button(info);
		gp.add(tempButton, x, y);
	}
	
	public void makeButton(FlowPane fp, String info, EventHandler<ActionEvent> eventHandler) {
		Button tempButton = new Button(info);
		tempButton.setMinSize(200, 50);
		tempButton.setStyle("-fx-background-color: #7CB558; -fx-font-size: 2em");
		
		if (eventHandler != null)
			tempButton.addEventHandler(ActionEvent.ACTION, eventHandler);
		
		fp.getChildren().add(tempButton);
	}

}
