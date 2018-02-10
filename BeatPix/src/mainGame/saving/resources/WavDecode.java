package mainGame.saving.resources;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Due to the nature of audio files, different audio types will require different things. <br>
 * This also includes variance in genre where some are more noisy than the others. <br>  
 * This stream will be able to read 16 bit-signed stereo files and mono (1 channel) wav files with a sampling rate of 44100.
 * 
 * @author Justin Yau
 *
 */
public class WavDecode {

	//Max value as a short and inversed
	private final float MAX_VALUE = 1.0f / Short.MAX_VALUE;
	
	//The input stream we will read information from
	private final EndianDInputStream in;
	
	//The number of channels the stream has
	private final int channels;
	
	//The sample rate in HZ
	private final float sampleRate;
	
	/**
	 * Constructor creates a new stream capable of reading samples for a 16-bit stereo or a mono wav file with a sampling rate of 44100
	 * @param stream - The data input stream you would like to read information from
	 * 
	 * @see See how waves are formatted at http://soundfile.sapp.org/doc/WaveFormat/ 
	 * 
	 * @author Justin Yau
	 * @throws Exception 
	 */
	public WavDecode(InputStream stream) throws Exception {
		
		if(stream == null ) {
			throw new IllegalArgumentException("Input stream cannot be null!");
		}
		
		in = new EndianDInputStream(new BufferedInputStream(stream, 1024*1024));

		if(!in.readNext4BytesString().equals("RIFF")) { //Determines whether or not the file is a RIFF, a different type of file
			throw new IllegalArgumentException( "This file is not a WAV!" ); 
		}
		
		in.intReadLittleEndian(); //Skip the next 4 bytes we do not need chunk size
		
		if(!in.readNext4BytesString().equals("WAVE")) { //Determines whether or not the file is a WAV, the file we want
			throw new IllegalArgumentException( "Expected a wave identifier" );
		}
		
		if(!in.readNext4BytesString().equals("fmt ")) { //FMT specifies the format of the data chunk that we NEED
			throw new IllegalArgumentException( "Expected a fmt identifier" );
		}
		
		if(in.intReadLittleEndian() != 16) { //Checks for a sub chunk size of 16
			throw new IllegalArgumentException( "Expected wave chunk size 16");
		}
		
		if(in.shortReadLittleEndian() != 1) { //As seen on the reference above
			throw new IllegalArgumentException( "Expected format 1" );
		}
		
		channels = in.shortReadLittleEndian(); //Retrieves the channel numbers
		
		sampleRate = in.intReadLittleEndian(); //Retrieves the sample rate
		if(sampleRate != 44100) {
			throw new IllegalArgumentException("Wav File does not have 44100 sampling rate");
		}
		
		in.intReadLittleEndian(); //We don't need byte rate
		in.shortReadLittleEndian(); //We don't need block align
		
		int fmt = in.shortReadLittleEndian(); //Retrieve the number of bits
		if(fmt != 16) {//Checks and makes sure WAV is 16 bits 
			throw new IllegalArgumentException("This decoder only supports 16-bit signed");
		}
		
		if(!in.readNext4BytesString().equals("data")) { //Next subchunk in the wav file formatting
			throw new RuntimeException("Expected data subchunk");
		}
		
		in.intReadLittleEndian(); //We don't need the Subchunk2Size
		
		//We are now at the samples
		
	}
	
	/**
	 * This method reads the input stream for bytes which are converted into float values 
	 * @param samps - The samples you would like the reader to stream to
	 * @return  - The number of samples successfully read
	 * 
	 * @see http://soundfile.sapp.org/doc/WaveFormat/ 
	 * 
	 */
	public int readSamples(float[] samps) {
		int sampsRead = 0; //Initialize the number of samples read
		for( int i = 0; i < samps.length; i++ ) //For all the samples
		{
			float samp = 0; //Initialize sample
			try //Keep reading until you get an exception in which there are no more samples to be read from the stream
			{
				for( int j = 0; j < channels; j++ ) //For each channel
				{
					int shortValue = in.shortReadLittleEndian( ); //Convert the specified byte into a short and turn it into an int
					samp += (shortValue * MAX_VALUE);
				}
				samp /= channels;
				samps[i] = samp;
				sampsRead++;
			}
			catch( Exception ex )
			{
				break;
			}
		}
		return sampsRead; 
	}

}
