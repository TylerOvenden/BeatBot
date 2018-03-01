package javaFX;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main5 extends Application {


	Stage window;
	Scene scene;
	Button button;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		
		CheckBox box1 = new CheckBox("a");
		CheckBox box2 = new CheckBox("b");
		
		
		button = new Button("click me");
		button.setOnAction(e -> handleOptions(box1,box2));
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10,20,20,20));
		layout.getChildren().addAll(box1, box2, button);
		
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

	

	}

	private void handleOptions(CheckBox box1, CheckBox box2) {
		String message = "users order:\n";
		
		if(box1.isSelected()) {
			message += "a\n";}
		if(box2.isSelected()) {
			message += "b\n";}
		
		System.out.println(message);
	
	}
}
