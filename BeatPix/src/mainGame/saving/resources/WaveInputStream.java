package mainGame.saving.resources;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class WaveInputStream {

	private final EndianInputStream in;
	
	public WaveInputStream(InputStream stream) {
		
		if(stream == null ) {
			throw new IllegalArgumentException("Input stream cannot be null!");
		}
		
		in = new EndianInputStream(new BufferedInputStream(stream, 1024*1024));

	}

}
