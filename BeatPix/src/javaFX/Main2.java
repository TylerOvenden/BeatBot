package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main2 extends Application {


	Stage window;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		
		HBox topMenu = new HBox();
		Button buttonA = new Button("file");
		Button buttonB = new Button("edit");
		Button buttonC = new Button("view");
		topMenu.getChildren().addAll(buttonA,buttonB,buttonC);
		
		VBox leftMenu = new VBox();
		Button buttonD = new Button("d");
		Button buttonE = new Button("e");
		Button buttonF = new Button("f");
		leftMenu.getChildren().addAll(buttonD,buttonE,buttonF);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setLeft(leftMenu);
		
		Scene scene = new Scene(borderPane,300,250);
		window.setScene(scene);
		window.show();
	}
}
