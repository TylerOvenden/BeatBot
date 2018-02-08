package mainGame.components;

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import mainGame.components.interfaces.JustinPlaySongInterface;
import mainGame.screens.GameScreen;
 

public class PlaySong extends Application {

    @Override
    public void start(Stage primaryStage) {

        Media pick = new Media("resources/audio.mp3"); // replace this with your own audio file
        MediaPlayer player = new MediaPlayer(pick);

        // Add a mediaView, to display the media. Its necessary !
        // This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);

 

        // Play the media once the stage is shown
        player.play();
    }

    public static void main(String[] args) {
         launch(args);
    }


}