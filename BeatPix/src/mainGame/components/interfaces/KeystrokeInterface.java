package mainGame.components.interfaces;

/**
 * This interface will have all the must have methods for the Keystroke Class
 * @author Justin Yau
 *
 */
public interface KeystrokeInterface {

	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear
	 * 
	 * @author Justin Yau
	 */
	public void keystrokeFall();
	
	/**
	 * This method will check the current y position of the stroke and see if it has made it past the target area
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the stroke has passsed the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal);
	
}
