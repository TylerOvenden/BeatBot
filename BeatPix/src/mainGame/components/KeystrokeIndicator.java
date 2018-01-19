package mainGame.components;

import gui.components.Graphic;

/**
 * This class will be used to display where the target press area is.
 * 
 * @author Justin Yau
 *
 */
public class KeystrokeIndicator extends Graphic {

	private String path; //This will store the img path of the indicator
	
	/**
	 * Create a indicator at the given x and y coordinate with the img retrieved from the given path.
	 * 
	 * @param x - The X coordinate of the indicator
	 * @param y - The Y coordinate of the indicator
	 * @param path - The image path file of the indicator
	 * 
	 * @author Justin Yau
	 */
	public KeystrokeIndicator(int x, int y, String p) {
		super(x, y, p);
		update();
	}
	
	/**
	 * This method makes the program sleep for the inputted amount of time
	 * 
	 * @param time - The time that you would like the the program to sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
