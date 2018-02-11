package mainGame.components;

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import mainGame.components.interfaces.JustinPlaySongInterface;
import mainGame.screens.GameScreen;
 

public class PlaySong implements JustinPlaySongInterface {
	
	
    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 1024;
    
    private boolean pause;
    private boolean cancel;
     
    public PlaySong() {
        pause = false;
        cancel = false;
    }
    
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     * Tyler
     */
    Long audioPosition;
    Clip clip;
    
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            
            audioLine.open(format);
 
            audioLine.start();
             
           // System.out.println("Playback started.");
             
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            try {
            
            	while(GameScreen.game.timePass() <= GameScreen.game.calculateTotalFallTime()) {
    				Thread.sleep(0);
            	}
            	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1 && !cancel) {
            	while(pause) {
            		sleep(0);
            	}
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
             
            audioLine.drain();
            audioLine.close();
            audioStream.close();
            
           // System.out.println("Playback completed.");
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
    }

    /*
     *      // Adjust the volume on the output line.
            if (audioLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            	System.out.println(true);
                FloatControl volume = (FloatControl) audioLine.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-15.0F);
            }
     */
    
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args) {
        String audioFilePath = "resources/maps/DreadnoughtMastermind(xi+nora2r)/DreadnoughtMastermind(xi+nora2r).wav";
        PlaySong player = new PlaySong();
        player.play(audioFilePath);
    }
	@Override
	public void pauseSong() {
	
		pause = true;
		
	}

	public void resumeSong() {

		pause = false;
		
	}

	public void stopSong() {
		
		cancel = true;
		
	}
 
}

	