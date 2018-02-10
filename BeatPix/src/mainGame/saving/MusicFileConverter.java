package mainGame.saving;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class will be able to process music files and detect beats and place them into an array list for processing by the file saver
 * 
 * @author Justin Yau
 */
public class MusicFileConverter {

	//Algorithm is derived by http://archive.gamedev.net/archive/reference/programming/features/beatdetection/index.html
	/*
	 * Instant Energy Formula: 
	 * 
	 * Local Average Energy Formula:
	 * 44032 - About one second - Call the .getFrameRate() to be 100 percent sure
	 * 
	 * (New Samples/Samples That System Remember) SUMMATION FROM 0 TO NEW SAMPLES 
	 * 
	 */
	
	private int sampleSize;
	private int channelsNum;
	private byte[] data;
	private int[] previousData;
	private long framesCount;
	private AudioInputStream audioInputStream;
	private AudioFormat audioFormat;
	private ArrayList<int[]> beats;
	
	public MusicFileConverter() {
		File file = new File("resources/maps/adrenaline!!! -TV Ver-TrySail/adrenaline!!! -TV Ver-TrySail.wav");
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			audioFormat = audioInputStream.getFormat();
			sampleSize = audioFormat.getSampleSizeInBits()/8;
		    channelsNum = audioFormat.getChannels();
		    
		    framesCount = audioInputStream.getFrameLength();
		    
	        long dataLength = framesCount * audioFormat.getSampleSizeInBits() * audioFormat.getChannels() / 8;

	        data = new byte[(int) dataLength];
	        audioInputStream.read(data);
	        System.out.println(audioFormat.getFrameRate());
	        
	        addBeats();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MusicFileConverter p = new MusicFileConverter();
	}
	
	//Frame rate is the number of samples per second
	
	public void addBeats() {
		beats = new ArrayList<int[]>();
		previousData = new int[2];
        for (int i = 0; i < framesCount; i++) {
        	float d = getSampleFloat(i);
        	//int time = (int) ((i / audioFormat.getFrameRate()) * 1000); //Divides the current frame by the frame rate to get the time in seconds and then multiply by 1000 to convert to milliseconds
        }
	}
	
	public int getRandomNumber(int low, int high) {
		return low + (int)(Math.random() * ((high - low) + 1));
	}
	
    public float getSampleFloat(int sampleNumber) {

        if (sampleNumber < 0 || sampleNumber >= data.length / sampleSize) {
            throw new IllegalArgumentException("WARNING: sample number can't be < 0 or >= data.length/" + sampleSize);
        }

        byte[] sampleBytes = new byte[4]; 
        
        for (int i = 0; i < sampleSize; i++) {
            sampleBytes[i] = data[sampleNumber * sampleSize * channelsNum + i];
        }

        int sample = ByteBuffer.wrap(sampleBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
        return sample;
    }
	
}
