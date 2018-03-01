package javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main4 extends Application {


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
		
		TextField nameInput = new TextField("");
		button = new Button("click me");
		button.setOnAction(e -> isInt(nameInput,nameInput.getText()));
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10,20,20,20));
		layout.getChildren().addAll(nameInput, button);

	
		Scene scene = new Scene(layout,300,200);
		window.setScene(scene);
		
		window.show();
	}

	private boolean isInt(TextField input, String message) {
		try {
			int age = Integer.parseInt(input.getText());
			System.out.println("user is: " + age);
			return true;
		}
		catch(NumberFormatException e){
			System.out.println("error: " + message + " is not a number");
			return false;
		}
	}
}
