package mainGame.components.interfaces;

/**
 * This interface will contain the essential methods for the keystroke indicator class
 * 
 * @author Justin Yau
 *
 */
public interface KeystrokeIndicatorInterface {

	/**
	 * This method makes the program sleep for the inputted amount of time
	 * 
	 * @param time - The time that you would like the the program to sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time);
	
	/**
	 * This method makes the stroke indicator play an animation to show that the key was hit
	 * 
	 * @author Justin Yau
	 */
	public void animate();
	
	/**
	 * This method makes the stroke indicator resume as a normal indicator
	 * 
	 * @author Justin Yau
	 */
	public void normalize();
	
}
