package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main12 extends Application {


	Stage window;
	BorderPane layout;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		
		Menu fileMenu = new Menu("File");
		
		fileMenu.getItems().add(new MenuItem("new project"));

		fileMenu.getItems().add(new MenuItem("new module"));

		fileMenu.getItems().add(new MenuItem("import project"));
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		

		
		layout = new BorderPane();
		layout.setTop(menuBar);
		Scene scene = new Scene(layout,300,250);
		window.setScene(scene);
		window.show();
	}
}
