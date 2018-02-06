package mainGame.components.interfaces;

import java.awt.Rectangle;

/**
 * This interface contains the essential methods that the Rectanglu class needs to work
 * 
 * @author Justin Yau
 *
 */
public interface RectangluInterface {

	/**
	 * This method will make the rectangle stop falling 
	 * 
	 * @author Justin Yau
	 */
	public void cancelFall();
	
	/**
	 * This method will make the rectangle stop falling
	 * 
	 * @author Justin Yau
	 */
	public void pauseFall();
	
	/**
	 * This method will make the rectangle continue falling
	 * 
	 * @author Jusitn Yau
	 */
	public void resumeFall();
	
	/**
	 * This method will make the rectangle gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void rectangleFall();
	
	/**
	 * This method will check the current y position of the rectangle and see if it has made it past the target area
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the rectangle has passed the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal);
	
	/**
	 * This method will update the sleep time that is called in each fall interval. <br>
	 * Default Fall Time - 10 ms <br>
	 * Decreasing this value will make the rectangle fall faster. 
	 * @param speed - Speed, in ms, that you want between each fall call.
	 * 
	 * @author Justin Yau
	 */
	public void updateFallSpeed(int speed);
	
	/**
	 * Create a rectangle object on a fixed x coordinate and width
	 * @param y - Y coordinate of the rectangle relative to the original y coordinate
	 * @param height - Height of the rectangle 
	 * @return - Returns a rectangle with the given properties
	 * 
	 * @author Justin Yau
	 */
	 public Rectangle rectanglueo(int y, int height);
	
	/**
	 * This method will check the current y position of the bottom side of the rectangle and see if it has made it past the target area
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the bottom side of rectangle has passed the goal
	 * 
	 * @author Justin Yau
	 */	 
	 public boolean isBottomBeyondGoal(int goal);
	 
	/**
	 * This method will allow the program to know that the rectangle is being used in a hold press at the moment
	 * 
	 * @param b - Whether the rectangle is being held down or not
	 * 
	 * @author Justin Yau
	 */
	public void setCurrentHold(boolean b);
	
	/**
	 * This method determines the properties of the rectangle based on current Y position and game properties
	 * 
	 * @return - Returns rectangle with properties based on current Y position and game properties
	 * 
	 * @author Justin Yau
	 */
	public Rectangle determineRect();
	
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time);
	
}
