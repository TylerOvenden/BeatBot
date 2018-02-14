package mainGame.components;

/**
 * Song bundle contains essential information for shop! <br />
 * Essentially is the folder that contains all the maps inside! <br />
 * 
 * @author Justin Yau
 */
public class SongBundle {

	private String title; //Title of the folder will be stored here
	private String path; //The path of the folder will be stored here
	private boolean unlocked; //Boolean to track whether the map bundle is unlocked or not will be stored here
	
	/**
	 * Construct a new bundle of songs for the other classes to use efficiently <br />
	 * Based on the folders in maps
	 * 
	 * @param name - Name of the bundle
	 * @param p - The path of the bundle
	 * 
	 * @author Justin Yau
	 */
	public SongBundle(String name, String p) {
		path = p;
		title = name;
		unlocked = false;
		if(title.contains("Dreadnought") || title.contains("Carribean")) {
			unlocked = true;
		}else {
			unlocked = false;
		}
	}
	
	/**
	 * Returns whether or not the bundle was unlocked
	 * @return - Whether or not the bundle was unlocked
	 * 
	 * @author Justin Yau
	 */
	public boolean isUnlock() {
		return unlocked;
	}

	/**
	 * Updates whether or not the bundle was unlocked
	 * @param unlock - Boolean to set unlocked to 
	 * 
	 * @author Justin Yau
	 */
	public void setUnlock(boolean unlock) {
		this.unlocked = unlock;
	}
	
	/**
	 * Returns the path of the folder this bundle represents
	 * @return - Returns the path of the folder this bundle represents
	 * 
	 * @author Justin Yau
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Returns the name of the folder this bundle represents
	 * @return - Returns the name of the folder this bundle represents
	 * 
	 * @author Justin Yau
	 */
	public String getTitle() {
		return title;
	}

}
