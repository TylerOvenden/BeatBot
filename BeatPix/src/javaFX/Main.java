package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends  Application{

	Stage window;
	Scene scene1,scene2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		Label labell = new Label("first scene");
		Button button1 = new Button("click to scene2");
		button1.setOnAction(e -> window.setScene(scene2));
	
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(labell,button1);
		scene1 = new Scene(layout1,200,200);
		
		Button button2 = new Button("click to get back to scene1");
		button2.setOnAction(e -> window.setScene(scene1));
		
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2,600,300);
		
		window.setScene(scene1);
		window.setTitle("title");
		window.show();
	}

}

