package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
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
		
		MenuItem newFile = new MenuItem("file");
		newFile.setOnAction(e -> System.out.println("create a new file"));
		fileMenu.getItems().add(newFile);
		
		
		fileMenu.getItems().add(new MenuItem("new "));
		fileMenu.getItems().add(new MenuItem("open"));
		fileMenu.getItems().add(new MenuItem("save"));
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(new MenuItem("settings"));
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(new MenuItem("exit"));
		
		Menu editMenu = new Menu("edit");
		editMenu.getItems().add(new MenuItem("cut"));
		editMenu.getItems().add(new MenuItem("copy"));
	//	editMenu.getItems().add(new MenuItem("paste"));
		
		MenuItem paste = new MenuItem("paste");
		paste.setOnAction(e -> System.out.println("pasting"));
		editMenu.getItems().add(paste);
		
		Menu helpMenu = new Menu("help");
		CheckMenuItem showLines = new CheckMenuItem("show line number");
		showLines.setOnAction(e ->{
			if(showLines.isSelected())
				System.out.println("program will now display line");
			else
				System.out.println("hiding line number");
		});
		helpMenu.getItems().addAll(showLines);
		
		Menu difficultyMenu = new Menu("difficulty");
		ToggleGroup difficultyToggle = new ToggleGroup();
		
		RadioMenuItem easy = new RadioMenuItem("easy");
		RadioMenuItem medium = new RadioMenuItem("medium");
		RadioMenuItem hard = new RadioMenuItem("hard");
		
		easy.setToggleGroup(difficultyToggle);
		medium.setToggleGroup(difficultyToggle);
		hard.setToggleGroup(difficultyToggle);
		difficultyMenu.getItems().addAll(easy,medium,hard);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, difficultyMenu);
		

		
		layout = new BorderPane();
		layout.setTop(menuBar);
		Scene scene = new Scene(layout,300,250);
		window.setScene(scene);
		window.show();
	}
}
