package mainGame.saving;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import mainGame.saving.resources.FFT;

/**
 * This class will be able to process music files and detect beats and place them into an array list for processing by the file saver
 * 
 * @author Justin Yau
 */
public class MusicFileConverter {
	
	private int sampleSize;
	private int channelsNum;
	private byte[] data;
	private int[] previousData;
	private long framesCount;
	private AudioInputStream audioInputStream;
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	private AudioFormat audioFormat;
	private ArrayList<float[]> samples;
	private ArrayList<int[]> beats;
	private FFT fft;
	
	public MusicFileConverter() {
		File file = new File("resources/maps/adrenaline!!! -TV Ver-TrySail/adrenaline!!! -TV Ver-TrySail.wav");
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			fileInputStream = new FileInputStream(file);
			dataInputStream = new DataInputStream(fileInputStream);

			audioFormat = audioInputStream.getFormat();
			fft = new FFT(1024, audioFormat.getSampleRate());
			
			sampleSize = audioFormat.getSampleSizeInBits()/8;
		    channelsNum = audioFormat.getChannels();
		    
		    framesCount = audioInputStream.getFrameLength();
		    
	        long dataLength = framesCount * audioFormat.getSampleSizeInBits() * audioFormat.getChannels() / 8;

	        data = new byte[(int) dataLength];
	        //System.out.println(audioFormat.getFrameRate());
	        
	        System.out.println(getSampleNumber(132000));
	        //addBeats();
	        
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
        for (int i = 1024; i < framesCount - 1024; i++) {
        	if(determineInstantEnergy(i) >= (determineAverageLocalEnergy(i) * 1.3)) {
        		System.out.println(true);
        	}
        	//float d = getSampleFloat(i);
        	//int time = (int) ((i / audioFormat.getFrameRate()) * 1000); //Divides the current frame by the frame rate to get the time in seconds and then multiply by 1000 to convert to milliseconds
        }
	}
	
	public float determineAverageLocalEnergy(int start) {
		float sum = 0;
		for(int i = 0; i < 43; i++) {
			sum += Math.pow(determineInstantEnergy(start - i), 2);
		}
		return sum / 43;
	}
	
	public float determineInstantEnergy(int start) {
		float value = 0;
		for(int i = start; i < 1024 + start; i++) {
			//value += Math.pow(getSampleNumber(i)[0], 2) + Math.pow(getSampleNumber(i)[1], 2);
		}
		return value;
	}
	
	public int getRandomNumber(int low, int high) {
		return low + (int)(Math.random() * ((high - low) + 1));
	}
	
    public void getSamples() {

    	samples = new ArrayList<float[]>();
    	
    	for(int i = 0; i < framesCount; i++) {
    		short leftChannel;
			try {
				leftChannel = Short.reverseBytes(dataInputStream.readShort());
	    		short rightChannel = Short.reverseBytes(dataInputStream.readShort());
	    		float leftC = leftChannel;
	    		float rightC = rightChannel;
	    		float[] temp = {leftC, rightC};
	    		samples.add(temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    public int getSampleNumber(int sampleNumber) {
        if (sampleNumber < 0 || sampleNumber >= data.length / sampleSize) {
            throw new IllegalArgumentException("Warning! Sample number can't be < 0 or >= data.length/" + sampleSize);
        }

        byte[] sampleBytes = new byte[4];

        for (int i = 0; i < sampleSize; i++) {
            sampleBytes[i] = data[sampleNumber * sampleSize * channelsNum + i];
        }
        
        int sample = ByteBuffer.wrap(sampleBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
        return sample;
    }
    
    public void getSample() throws Exception {
        byte[] samples = new byte[1024];
        float[] spectrum = new float[1024 / 2 + 1];
        float[] lastSpectrum = new float[1024 / 2 + 1];
        List<Float> spectralFlux = new ArrayList<Float>();

        while (audioInputStream.read(samples) > 0) {
            //fft.forward(samples);
            System.arraycopy(spectrum, 0, lastSpectrum, 0, spectrum.length);
            System.arraycopy(fft.getSpectrum(), 0, spectrum, 0, spectrum.length);

            float flux = 0;
            for (int i = 0; i < spectrum.length; i++)
                flux += (spectrum[i] - lastSpectrum[i]);
            spectralFlux.add(flux);
        }
    }
    
}
