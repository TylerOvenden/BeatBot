package mainGame.components.interfaces;

import mainGame.screens.GameScreen;

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
	 * This method will update the sleep time that is called in each fall interval. <br>
	 * Decreasing this value will make the stroke fall faster. 
	 * @param speed - Speed, in ms, that you want between each fall call.
	 * 
	 * @author Justin Yau
	 */
	public void updateFallSpeed(int speed);
	
	/**
	 * This method calculates the distance from the target optimal pressing area in Y 
	 * @return Returns the distance from the target optimal pressing area in Y 
	 * @author Justin Yau
	 */
	public int distanceFromGoal();
	
	/**
	 * This method calculates the time, in ms, the stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getClickTime();
	
	/**
	 * This method returns the delay between each call to make the Keystroke fall
	 * @return Returns the delay between each call to make the Keystroke fall
	 * @author Justin Yau
	 */
	public int getFallSpeed();

	/**
	 * This method sets the boolean responsible for determining whether or not the keystroke is part of a hold or not
	 * 
	 * @param value - Whether you want to set it be a hold stroke or not
	 * 
	 * @author Justin Yau
	 */
	public void setHold(boolean value);
	
	/**
	 * This method returns whether or not this stroke is part of a hold stroke
	 * @return Returns whether or not this stroke is part of a hold stroke
	 */
	public boolean getHold();
	
	/**
	 * This method will allow the program to know that the keystroke is being used in a hold press at the moment
	 * 
	 * @param b - Whether the keystroke is being held down or not
	 * 
	 * @author Justin Yau
	 */
	public void setCurrentHold(boolean b);
	
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time);
	
	/**
	 * This method calculates the y coordinate of the bottom corner of the keystroke
	 * @return - Returns the y coordinate of the bottom corner of the keystroke
	 * 
	 * @author Justin Yau
	 */
	public int getBottomY();
	
}
