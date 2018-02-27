package javaFX;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FirstFx extends Application implements EventHandler {

	Button button;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("title");
		
		button = new Button();
		button.setText("click");
		
		button.setOnAction(this);
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout,500,250);
		arg0.setScene(scene);
		arg0.show();
	}

	@Override
	public void handle(Event event) {
		if(event.getSource()==button) {
			button.setText("ok");
		}
		
	}

}