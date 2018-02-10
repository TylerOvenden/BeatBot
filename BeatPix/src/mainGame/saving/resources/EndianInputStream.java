package mainGame.saving.resources;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EndianInputStream extends DataInputStream {

	public EndianInputStream(InputStream in) {
		super(in);
	}

	/**
	 * This method converts the specified byte to little endian where less significant bytes come before significant ones
	 * WAV files are typically written in this format
	 * @return
	 * @throws Exception 
	 */
    public short readLittleEndian() throws Exception {
    	int result = readUnsignedByte(); 
			result |= readUnsignedByte() << 8;
    	return (short)result;
    }
}
