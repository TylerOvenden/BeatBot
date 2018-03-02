package javaFX;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main9 extends Application {


	Stage window;
	Scene scene;
	Button button;
	ListView<String> listView;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		button = new Button("click me");
		
		listView = new ListView<>();
		listView.getItems().addAll("iron man", "titantic", "eraserhead");
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		button.setOnAction( e-> buttonClicked());
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(listView,button);
		
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

	

	}

	private void buttonClicked() {
		String message = "";
		ObservableList<String> films;
		films = listView.getSelectionModel().getSelectedItems();
		for(String f: films) {
			message += f + "\n";
		}
		System.out.println(message);
	}



}
