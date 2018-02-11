package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
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
import mainGame.saving.resources.FFT;
import mainGame.saving.resources.WavDecode;
import mainGame.screens.GameScreen;

public class AudioVisualizer extends Component {

	private float[] spectrums;
	private boolean pause;
	private boolean cancel;
	
	public AudioVisualizer(int x, int y, int w, int h) {
		super(x, y, w, h);
		spectrums = new float[0];
		pause = false;
		cancel = false;
	}
	
	public void pauseSong() {
		pause = true;
	}
	
	public void resumeSong() {
		pause = false;
	}
	
	public void stopSong() {
		cancel = true;
	}
	
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
		g.setColor(Color.CYAN);
		g.drawLine(0, 0, getWidth(), 0);
		for(int i = 0; i < spectrums.length; i++) {
			float amplitude = spectrums[i];
			g.drawLine(i, 0, i, (int) amplitude);
		}
	}

}
