package mainGame.components.interfaces;

import java.util.ArrayList;

import gui.interfaces.Visible;

/**
 * This interface contains all the essential methods that the Holdstroke class needs to work.
 * 
 * @author Justin Yau
 *
 */
public interface HoldstrokeInterface {
	
	/**
	 * This method will update the sleep time that is called in each fall interval. <br>
	 * Default Fall Time - 10 ms <br>
	 * Decreasing this value will make the stroke fall faster. 
	 * @param speed - Speed, in ms, that you want between each fall call.
	 * 
	 * @author Justin Yau
	 */
	public void updateFallSpeed(int speed);
	
	/**
	 * This method returns the delay between each call to make the Keystroke fall
	 * @return Returns the delay between each call to make the Keystroke fall
	 * @author Justin Yau
	 */
	public int getFallSpeed();
	
	/**
	 * This method will make the stroke stop falling 
	 * 
	 * @author Justin Yau
	 */
	public void cancelFall();
	
	/**
	 * This method will make the stroke stop falling
	 * 
	 * @author Justin Yau
	 */
	public void pauseFall();
	
	/**
	 * This method will make the stroke continue falling
	 * 
	 * @author Jusitn Yau
	 */
	public void resumeFall();
	
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time);
	
	/**
	 * This method makes the stroke move down a y value and resizes the animated image accordingly
	 */
	public void moveOneDown();
	
	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void holdstrokeFall();
	
	/**
	 * This method will be utilized to determine whether or not the stroke has made it past the goal
	 * 
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the stroke has made it past the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal);
	
}
