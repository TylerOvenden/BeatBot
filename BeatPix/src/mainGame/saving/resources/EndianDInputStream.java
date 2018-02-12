package mainGame.saving.resources;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class will be used to read the next line in the stream and then use bit shifting to make it into the data type we want
 * 
 * @author Justin Yau
 */
public class EndianDInputStream extends DataInputStream {

	/**
	 * Creates an input stream that allows one to convert the next byte into the given data value 
	 * @param in - The data stream you would like to read
	 * 
	 * @author Justin Yau
	 */
	public EndianDInputStream(InputStream in) {
		super(in);
	}

	//Byte (8 bits) -> Char (16 bits) -> Short (16 bits) -> Int (32 bits) -> Long (64 bits) -> Float (32 bits) -> Double (64 bits) 
	
	/**
	 * This method reads the next 4 bytes from the stream and converts them into a string 
	 * Each byte can hold one character in the ASCII encoding format
	 * @return The 4 bytes as a string
	 * 
	 * @see Conversion was retrieved from https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/ 
	 * 
	 * @throws Exception
	 * 
	 * @author Justin Yau
	 */
	public String readNext4BytesString() throws Exception {
		byte[] b = new byte[4];
		readFully(b);
		return new String(b, "US-ASCII");
	}
	
	/**
	 * This method converts the specified byte to little endian short where less significant bytes come before significant ones
	 * WAV files are typically written in this format
	 * @return - The next byte in the stream as a little endian short (All 8 bits of the byte shifted 8 zeros to the left with zeros in their place) 
	 * @throws Exception 
	 * 
	 * @see http://vojtechruzicka.com/bit-manipulation-java-bitwise-bit-shift-operations/
	 * 
	 * @author Justin Yau
	 */
    public short shortReadLittleEndian() throws Exception {
    	int r = readUnsignedByte(); 
			r |= readUnsignedByte() << 8;
    	return (short)r;
    }
    
    /**
     * This method converts the specified bytes to little endian int where less signficant bytes come before signficant ones
     * WAV files are typically written in this format
     * @return - The next byte in the stream as a little endian int  
     * @throws Exception 
     * 
     * @see Conversion was retrieved from https://stackoverflow.com/questions/3790250/after-byte-shifitng-a-int-to-4-bytes-how-can-i-shift-back-to-int-c
     * 
     * @author Justin Yau <br> 
     */
	public int intReadLittleEndian( ) throws Exception
	{
		int r = readUnsignedByte();
		r |= readUnsignedByte() << 8; 
		r |= readUnsignedByte() << 16; 
		r |= readUnsignedByte() << 24;
		return r;		
	}
	
}
