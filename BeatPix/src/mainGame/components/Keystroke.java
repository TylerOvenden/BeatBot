package mainGame.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.components.AnimatedComponent;
import mainGame.components.interfaces.KeystrokeInterface;
import mainGame.screens.GameScreen;

/*
 * 
 * This class will be mainly coded by Justin Yau
 * PERIOD 4 & 5 - AP Computer Science Java
*/

/*
 * PLANNING: 
 * - Incorporate a method that will enable us to update y position
 * - MAYBE - Incorporate a method that will enable us to update x position
 * - Have another thread running that'll check the y position and remove the object upon passing the stroke location
 * - Connect this to Steven's class that'll handle the accuracy decider and whether the stroke was good, bad, or etc.
 */

/**
 * This component is the stroke indicator that will be on display throughout the game
 * Players will have to time their strokes such that it almost matches the time when this indicator passes through the stroke location
 * @author Justin Yau
 */
public class Keystroke extends AnimatedComponent implements KeystrokeInterface {

	private int fallTime; //The speed at which the stroke falls down
	private int startTime; //The starting time in the map when this stroke spawned
	private boolean cancel; //This boolean is to keep track if the keystroke fall was canceled by the a key press
	private boolean pause; //This boolean is to keep track if the game is currently pause
	private int clickTime; //This int is to track the time, in ms, the stroke should've been pressed since the game started 
	private boolean isHold; //This boolean tracks whether or not the key press was a hold
	
	/**
	 * Create a stroke indicator at a specified location that is subject to change utilizing methods.
	 * This constructor will handle the animated image aspect of the indicator.
	 * 
	 * @author Justin Yau
	 * 
	 * @param x - X Coordinate of the indicator
	 * @param y - Y Coordinate of the indicator 
	 * @param path - Image file path (Ex: "resources/arrows/darrow.png")
	 */
	public Keystroke(int x, int y, int stime, String path) {
		super(x, y, 65, 65);
		isHold = false;
		fallTime = 10;
		startTime = stime;
		//Path should be inputted something like "resources/arrows/darrow.png"
		addSequence(path, 100, 0, 0, 64, 64, 4);
		//To make the component animated, we need to make a thread
		Thread animation = new Thread(this);
		animation.start();
		update();
	}

	/**
	 * This method sets the boolean responsible for determining whether or not the keystroke is part of a hold or not
	 * 
	 * @param value - Whether you want to set it be a hold stroke or not
	 * 
	 * @author Justin Yau
	 */
	public void setHold(boolean value) {
		isHold = value;
	}
	
	/**
	 * This method returns whether or not this stroke is part of a hold stroke
	 * @return Returns whether or not this stroke is part of a hold stroke
	 */
	public boolean getHold() {
		return isHold;
	}
	
	/**
	 * This method calculates the time, in ms, the stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getClickTime() {
		return startTime + (GameScreen.columnHeight * fallTime);
	}
	
	/**
	 * This method calculates the distance from the target optimal pressing area in Y 
	 * @return Returns the distance from the target optimal pressing area in Y 
	 * @author Justin Yau
	 */
	public int distanceFromGoal() {
		return (GameScreen.columnHeight + GameScreen.columnY) -  getY();
	}
	
	/**
	 * Overrides method to implement the pause feature. <br>
	 * This method makes keystroke stop being animated. 
	 * 
	 * @author Justin Yau
	 */
	public void run(){
		int posx= getX();
		int posy= getY();
		boolean running = true;
		long moveTime = System.currentTimeMillis();
		while(running){

			try {
				while(pause) {
					Thread.sleep(0);
				}
				Thread.sleep(REFRESH_RATE);
				checkBehaviors();
				update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * This method Returns the time that this beat spawned into the map
	 * @return  Returns the time that this beat spawned into the map
	 * @author Justin Yau
	 */
	public int getStartingTime() {
		return startTime;
	}
	
	/**
	 * This method converts the current x position and determines which "lane" the stroke is in
	 * @return Which lane the stroke is in
	 * @author Justin Yau
	 */
	public int getColumnLane() {
		int[] arr = GameScreen.arrowX;
		int xCoordinate = getX();
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == xCoordinate) {
				return i + 1;
			}
		}
		return 0;
	}
	
	/**
	 * This method will update the sleep time that is called in each fall interval. <br>
	 * Default Fall Time - 10 ms <br>
	 * Decreasing this value will make the stroke fall faster. 
	 * @param speed - Speed, in ms, that you want between each fall call.
	 * 
	 * @author Justin Yau
	 */
	public void updateFallSpeed(int speed) {
		fallTime = speed;
	}
	
	/**
	 * This method returns the delay between each call to make the Keystroke fall
	 * @return Returns the delay between each call to make the Keystroke fall
	 * @author Justin Yau
	 */
	public int getFallSpeed() {
		return fallTime;
	}
	
	/**
	 * This method will make the stroke stop falling 
	 * 
	 * @author Justin Yau
	 */
	public void cancelFall() {
		this.cancel = true;
	}
	
	/**
	 * This method will make the stroke stop falling
	 * 
	 * @author Justin Yau
	 */
	public void pauseFall() {
		pause = true;
	}
	
	/**
	 * This method will make the stroke continue falling
	 * 
	 * @author Jusitn Yau
	 */
	public void resumeFall() {
		pause = false;
	}
	
	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void keystrokeFall() {
		cancel = false;
		pause = false;
		while(!(isBeyondGoal(GameScreen.columnHeight + GameScreen.columnY)) && !cancel) {
			while(pause) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			setY(getY() + 1);
			try {
				Thread.sleep(fallTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update();
		}
		if(!cancel) {
			GameScreen.game.removeStroke(this);
			//Miss accuracy here
		}
		update();
	}
	
	/**
	 * This method will check the current y position of the stroke and see if it has made it past the target area
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the stroke has passsed the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal) {
		return getY() > goal;
	}
	
}
