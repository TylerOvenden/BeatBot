package javaFX;

import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Beeper extends Application {

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		Button btn = new Button("ding!");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				 Toolkit.getDefaultToolkit().beep();     
				
			}
		});
		root.getChildren().add(btn);
		Scene scene = new Scene(root,200,100);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
