package javaFX;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main8 extends Application {


	Stage window;
	Scene scene;
	Button button;
	ComboBox<String> comboBox;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		button = new Button("click me");
		
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll(
				"hello",
				"wqdwe",
				"okay"
				);
		comboBox.setPromptText("test");
		comboBox.setEditable(true);
		
		comboBox.setOnAction(e -> System.out.println("user put in: " + comboBox.getValue()));
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(comboBox,button);
		
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

	

	}





}
