package mainGame.components.interfaces;

import java.util.ArrayList;

/**
 * Interface holding the must have methods for the Song class
 * 
 * @author Justin Yau
 *
 */
public interface SongInterface {

	/**
	 * This method will return the title of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the title of the beatmap
	 * 
	 * @author Justin Yau
	 */
	public String getTitle();
	
	/**
	 * This method will return the beats per minute of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the BPM of the beatmap
	 * 
	 * @author Justin Yau
	 */
	public int getBPM();
	
	/**
	 * This method will return the artist of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the artist of the beatmap
	 * 
	 * @author Justin Yau
	 */
	public String getArtist();
	
	/**
	 * This method will return the offSet of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the offSet of the beatmap
	 * 
	 * @author Justin Yau
	 */
	public int getOffSet();
	
	/**
	 * This method will return the beats of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the beats of the beatmap
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<int[]> getBeats();
	
}
