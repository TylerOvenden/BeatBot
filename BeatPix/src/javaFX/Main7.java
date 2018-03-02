package javaFX;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main7 extends Application {


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
		button = new Button("click me");
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		
		choiceBox.getItems().add("Apples");
		choiceBox.getItems().add("Bananas");
		choiceBox.getItems().addAll("bacon","ham","meatballs");
		choiceBox.setValue("Apples");
	/*
	 * both of these methods would display the same thing
	 * 
	 * 	
	 */
	//	choiceBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->System.out.println(oldValue) );
		choiceBox.setOnAction(e -> System.out.println(choiceBox.getValue()));
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(choiceBox,button);
		
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

	

	}



}
