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
	
	/**
	 * This method handles the end resizing of the stroke. <br> 
	 * By end, I mean when the stroke's head starts to disappear. 
	 * 
	 * @param height - New height of the image
	 * 
	 * @author Justin Yau
	 */
	public void resizeFramesEnd(int height);
	
	/**
	 * This method handles the start resizing of the stroke. <br> 
	 * By end, I mean when the stroke starts to appear. Also works for resizing when the stroke is completely spawned.
	 * 
	 * @param height - New height of the image
	 * 
	 * @author Justin Yau
	 */
	public void resizeFramesStart(int height);
	
	/**
	 * This method handles the self removal of the stroke if needed
	 * 
	 * @author Justin Yau
	 */
	public void handleRemove();
	
	/**
	 * This method calculates the new height value depending on the "current" y value of the stroke
	 * 
	 * @return Returns the new height value depending on the "current" y value of the stroke
	 * 
	 * @author Justin Yau
	 */
	public int determineCurrentHeightFromY();
	
	/**
	 * This method returns the distance of the bottom part of the stroke from the goal
	 * @return Returns the distance of the bottom part of the stroke from the goal
	 * 
	 * @author Justin Yau
	 */
	public int distanceFromGoal();
	
	/**
	 * This method converts the current x position and determines which "lane" the stroke is in
	 * @return Which lane the stroke is in
	 * @author Justin Yau
	 */
	public int getColumnLane();
	
	/**
	 * This method Returns the time that this beat spawned into the map
	 * @return  Returns the time that this beat spawned into the map
	 * @author Justin Yau
	 */
	public int getStartingTime();
	
	/**
	 * This method calculates the time, in ms, the first stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the first stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getFirstClickTime();
	
	/**
	 * This method calculates the time, in ms, the end stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the end stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getEndClickTime();
	
	/**
	 * This method sets whether or not the stroke is being held or not
	 * 
	 * @param h - Whether or not the stroke is being held or not
	 * 
	 * @author Justin Yau
	 */
	public void setHeld(boolean h);
	
	/**
	 * This method determines the height based on the current circumstances
	 * @param bottomHeightFromStart - The calculated height from the start point of the stroke
	 * @return - The height the stroke should be rendered at 
	 * 
	 * @author Justin Yau
	 */
	public int determineBeginningHeight(int bottomHeightFromStart);
	
	/**
	 * This method determines the height based on the current circumstances
	 * @param bottomHeightFromBottom - The calculated height from the end point of the stroke
	 * @return - The height the stroke should be rendered at
	 * 
	 * @author Justin Yau
	 */
	public int determineEndHeight(int bottomHeightFromBottom);
	
}
