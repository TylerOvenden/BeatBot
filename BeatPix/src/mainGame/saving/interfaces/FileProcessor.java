package mainGame.saving.interfaces;

import java.util.ArrayList;

/**
 * Interface that the class holding the methods that will enable us to save, load, and retrieve csv files
 * @author Justin Yau
 */
public interface FileProcessor {

	/**
	 * This method will create a CSV file named after the title and artist.
	 * 
	 * @param title - Name of the map 
	 * @param BPM - Beats per minute
	 * @param artist - Arist that created the map
	 * @param offSet - Offset 
	 * @param list - Integer Array that will 
	 * 
	 * @author Justin Yau
	 */
	public void save(String title, int BPM, String artist, int offSet, ArrayList<Integer[]> list);
	
	/**
	 * This method will load a CSV file and save it in a instance of File.
	 * DO NOT ATTEMPT TO LOAD A OSU FILE WITH THIS METHOD.
	 * 
	 * @param fileName - The path of the file that you're looking to load
	 * 
	 * @author Justin Yau
	 */
	public void load(String fileName);
	
	public void OSUconvert(String fileName);
	
	/**
	 * This method will return the title of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the title of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	public String getTitle();
	
	/**
	 * This method will return the beats per minute of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the BPM of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	public int getBPM();
	
	/**
	 * This method will return the artist of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the artist of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	public String getArtist();
	
	/**
	 * This method will return the offSet of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the offSet of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	public int getOffSet();
	
	/**
	 * This method will return the beats of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the beats of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Integer[]> getBeats();
	
}
