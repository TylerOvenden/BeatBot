package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import gui.components.Component;
import mainGame.components.interfaces.JustinPlaySongInterface;
import mainGame.saving.resources.FFT;
import mainGame.saving.resources.WavDecode;
import mainGame.screens.GameScreen;

/**
 * Audio Visualizer is a visual that helps you picture the frequencies of a given audio file at the given moment
 * 
 * @see https://www.youtube.com/watch?v=2O3nm0Nvbi4&t=182s
 * 
 * @author Justin Yau
 */
public class AudioVisualizer extends Component implements JustinPlaySongInterface {

	private float[] spectrums; //All the band amplitudes of the audio will be stored here
	private final Color[] colors = {Color.RED, Color.GRAY, Color.YELLOW, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.WHITE}; 
	//All the colors the visualizer can be will be stored here
	private Color color; //The final color of the visualizer will be stored here
	private boolean pause; //This boolean will track whether or not the visualizer is paused
	private boolean cancel; //The boolean will track whether or not to cancel the visualizer processes
	private int bandWidth; //The widths of the band will be stored here
	
	/**
	 * Constructor creates an audio visualizer that helps the user visualize what is happening to the frequencies at a given time
	 * @param x - The x coordinate of the visualizer
	 * @param y - The y coordinate of the visualizer
	 * @param w - The width of the visualizer
	 * @param h - The height of the visualizer
	 */
	public AudioVisualizer(int x, int y, int w, int h) {
		super(x, y, w, h);
		spectrums = new float[0];
		bandWidth = 3;
		pause = false;
		cancel = false;
		color = getRandomColor();
		update();
	}
	
	/**
	 * This method selects a random color from the preset color array and returns it
	 * @return - A random color from the preset color array
	 * 
	 * @author Justin Yau
	 */
	public Color getRandomColor() {
		return colors[getRandomNumber(0, colors.length - 1)];
	}
	
	/**
	 * This method generates a number between the specified low and highs of the set
	 * @param low - The lowest number to generate
	 * @param high - The highest number to generate
	 * @return - A random number between the specified set
	 * 
	 * @author Justin Yau
	 */
	public int getRandomNumber(int low, int high) {
		return low + (int)(Math.random() * ((high - low) + 1));
	}
	
	/**
	 * This method makes the boolean responsible for pausing operations true
	 * 
	 * @author Justin Yau
	 */
	public void pauseSong() {
		pause = true;
	}
	
	/**
	 * This method makes the boolean responsible for pausing operations false
	 * 
	 * @author Justin Yau
	 */
	public void resumeSong() {
		pause = false;
	}
	
	/**
	 * This method makes the boolean responsible for canceling operations true
	 * 
	 * @author Justin Yau
	 */
	public void stopSong() {
		cancel = true;
	}
	
	/**
	 * This method loads the song and it gets analyzed over time to visualize the frequencies 
	 * @param audioPath - The path of the audio file to be analyzed
	 * 
	 * @author Justin Yau
	 */
	public void loadSong(String audioPath) {
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(audioPath)));
			WavDecode waveInputStream = new WavDecode(new FileInputStream(audioPath));
			AudioFormat af = audioInputStream.getFormat();
			
			FFT fft = new FFT(1024, af.getSampleRate());
	    	while(GameScreen.game.timePass() <= GameScreen.game.calculateTotalFallTime()) { //Wait until song begins
				Thread.sleep(0);
	    	}
	    	
	    	float[] samples = new float[1024];
    		float percentageOfASecond = 1024 / af.getSampleRate();
    		long sleepTime = (long) (percentageOfASecond * 1000);
	    	while(waveInputStream.readSamples(samples) > 0 && !cancel) {
	    		while(pause) {
	    			Thread.sleep(0);
	    		}
	    		fft.forward(samples);
	    		spectrums = fft.getSpectrum();
	    		update();
	    		Thread.sleep(sleepTime);
	    	}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Graphics2D g) {
		super.clear();
		g.scale(.8, .8);
		g.setColor(color);
		g.drawLine(0, 0, getWidth(), 0);
		for(int i = 0; i < spectrums.length; i++) {
			float amplitude = spectrums[i];
			if(i != 0) {
				amplitude = (0.9f) * spectrums[i-1] + (.1f) * spectrums[i];
			}
			g.setColor(color);
			Rectangle band = new Rectangle((i * bandWidth), 0, bandWidth, (int) amplitude);
			g.draw(band);
			g.fill(band);
			g.setColor(Color.BLACK);
			g.draw(band);
			//g.drawLine(i, 0, i, (int) amplitude);
		}
	}

}
