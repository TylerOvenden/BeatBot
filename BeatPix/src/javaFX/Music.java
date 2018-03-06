package javaFX;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Music extends Application {

	MediaPlayer mediaPlayer;
	private Label time;
	Duration duration;
	Button fullScreenButton;
	Scene scene;
	Media media;
	double width;
	double height;
	MediaView mediaView;

	@Override
	public void start(Stage primaryStage) {

	scene = setScene(this.width, this.height);

	primaryStage.setTitle("Media Player!");
	primaryStage.setScene(scene);

	primaryStage.show();
	}

	public Scene setScene(double width, double height) {
		this.height = height;
		this.width = width;
		//Add your own path of the vidio that you want to play
		String path = "resources/music/keroacoustic.wav";

		media = new Media(new File(path).toURI().toString());

		mediaPlayer = new MediaPlayer(media);
		//AutoPlay set to false
		mediaPlayer.setAutoPlay(false);
		mediaView = new MediaView(mediaPlayer);

		// DropShadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetY(5.0);
		dropshadow.setOffsetX(5.0);
		dropshadow.setColor(Color.WHITE);

		mediaView.setEffect(dropshadow);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mediaView);
	

		borderPane.setStyle("-fx-background-color: Black");
		scene = new Scene(borderPane, 600, 600);
		scene.setFill(Color.BLACK);
		return scene;
		}
 }