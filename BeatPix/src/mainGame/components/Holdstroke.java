package mainGame.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.components.AnimatedComponent;
import mainGame.components.interfaces.HoldstrokeInterface;
import mainGame.components.interfaces.Stroke;
import mainGame.screens.GameScreen;

/**
 * This class essentially makes the scroll keystroke that the user will have to follow to maintain or increase accuracy.
 * 
 * @author Justin Yau
 *
 */
public class Holdstroke extends AnimatedComponent implements HoldstrokeInterface, Stroke {
	
	private int fallSpeed; //The speed at which you would like the stroke to fall at
	private int holdTime; //The time the user has to hold down a particular key for this stroke
	private int height; //The height of the stroke for visuals
	private int currentHeight; //The current height of the stroke
	private boolean tooBig; //This boolean will be utilized to track whether the stroke was too big or not
	private int prevHeight; //This integer will store the value of the previous height from resizing the image
	private boolean cancel; //This boolean will track whether or not the stroke was canceled by the screen
	private boolean pause; //This boolean will track whether or not the game was paused
	private boolean switchEnd; //This boolean will track whether or not the resize using the front or end methods
	private int startingTime; //This int will track the time the stroke spawned in
	private boolean currentBeingHeld; //This boolean will track whether or not the holdstroke is being held or not
	private ArrayList<BufferedImage> frames; //The frames of the animation image will be stored here for resizing purposes
	private String path; //The image path file of the animation

	/**
	 * This constructor constructs a animated hold stroke that "should" render in and out of the game. 
	 * 
	 * @param x - The starting x coordinate of the stroke
	 * @param y - The starting y coordinate of the stroke
	 * @param h - The height of the hold stroke
	 * @param sTime - The starting time in the song of the stroke
	 * @param path - The image path file of the animated stroke
	 * 
	 * @author Justin Yau
	 */
	public Holdstroke(int x, int y, int h, int sTime, String path) {
		super(x,y,64, h + 65);
		switchEnd = false;
		startingTime = sTime;
		tooBig = false;
		fallSpeed = 5;
		height = h + 64;
		prevHeight = 0;
		this.path = path;
		update();
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
		return startingTime;
	}
	
	/**
	 * This method calculates the time, in ms, the first stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the first stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getFirstClickTime() {
		return startingTime + (GameScreen.columnHeight * fallSpeed);
	}
	
	/**
	 * This method calculates the time, in ms, the end stroke should've been pressed since the game started 
	 * @return Returns the time, in ms, the end stroke should've been pressed since the game started 
	 * @author Justin Yau
	 */
	public int getEndClickTime() {
		return startingTime + ((GameScreen.columnHeight + height - (GameScreen.distanceAAfterGoal)) * fallSpeed);
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
		fallSpeed = speed;
	}
	
	/**
	 * This method returns the delay between each call to make the Keystroke fall
	 * @return Returns the delay between each call to make the Keystroke fall
	 * @author Justin Yau
	 */
	public int getFallSpeed() {
		return fallSpeed;
	}
	
	/**
	 * This method will make the stroke stop falling 
	 * 
	 * @author Justin Yau
	 */
	public void cancelFall() {
		this.cancel = true;
		update();
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
	 * This method returns the distance of the bottom part of the stroke from the goal
	 * @return Returns the distance of the bottom part of the stroke from the goal
	 * 
	 * @author Justin Yau
	 */
	public int distanceFromGoal() {
		return (GameScreen.columnHeight + GameScreen.columnY) - (getY() + currentHeight - 64) ;
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
	 * This method calculates the new height value depending on the "current" y value of the stroke
	 * 
	 * @return Returns the new height value depending on the "current" y value of the stroke
	 * 
	 * @author Justin Yau
	 */
	public int determineCurrentHeightFromY() {
		int topHeight = getY();
		if(!switchEnd) {
			topHeight = getY() + prevHeight;
		}
		int bottomHeightFromStart = topHeight - GameScreen.columnY;
		int bottomHeightFromBottom = GameScreen.columnY + GameScreen.columnHeight + GameScreen.distanceAAfterGoal - topHeight;
		if(bottomHeightFromStart <= 0) {
			return 1;
		}
		else if(height - 64 >= bottomHeightFromStart && !switchEnd) {
			if(height > GameScreen.columnHeight) {
				tooBig = true;
				return 64 + prevHeight++;
			}
			if(switchEnd) {
				return height;
			}
			return bottomHeightFromStart + 64;
		}
		//Above works
		else if(height >= bottomHeightFromBottom) {
			if(bottomHeightFromBottom <= -1 * GameScreen.distanceAAfterGoal ) {
				handleRemove();
				return 1;
			}
			return bottomHeightFromBottom - GameScreen.distanceAAfterGoal;
		}
		return height;
	}

	/**
	 * This method handles the self removal of the stroke if needed
	 * 
	 * @author Justin Yau
	 */
	public void handleRemove() {
		GameScreen.game.removeHoldStroke(this);
		//Place Scoring Here
		GameScreen.game.getTiming().missAccuracy();
		//Place Scoring Here
		cancel = true;
	}
	
	/**
	 * This method handles the start resizing of the stroke. <br> 
	 * By end, I mean when the stroke starts to appear. Also works for resizing when the stroke is completely spawned.
	 * 
	 * @param height - New height of the image
	 * 
	 * @author Justin Yau
	 */
	public void resizeFramesStart(int height) {
		if(height == this.height) {
			return;
		}
		for(int i = 0; i < frames.size(); i++) {
			BufferedImage fram = frames.get(i);
			int width = fram.getWidth();
			int h1 = fram.getHeight();
			BufferedImage dest = fram.getSubimage(0, h1 - height, width, height);
			BufferedImage nImg = new BufferedImage(width, this.height, dest.getType());
			Graphics2D g2d = nImg.createGraphics();
			g2d.drawImage(dest, 0, 0, width, height, null);
			g2d.dispose();
			prevHeight = height - 64;
			frameSet(i, nImg);
		}
	}
	
	/**
	 * This method handles the end resizing of the stroke. <br> 
	 * By end, I mean when the stroke's head starts to disappear. 
	 * 
	 * @param height - New height of the image
	 * 
	 * @author Justin Yau
	 */
	public void resizeFramesEnd(int height) {
		if(height == this.height) {
			return;
		}
		if(height <= 0) {
			height = 1;
		}
		for(int i = 0; i < frames.size(); i++) {
			BufferedImage fram = frames.get(i);
			int width = fram.getWidth();
			BufferedImage dest = fram.getSubimage(0, 0, width, height);
			BufferedImage nImg = new BufferedImage(width, this.height, dest.getType());
			Graphics2D g2d = nImg.createGraphics();
			g2d.drawImage(dest, 0, 0, null);
			g2d.dispose();
			frameSet(i, nImg);
		}
	}
	
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
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
	
	/**
	 * This method makes the stroke move down a y value and resizes the animated image accordingly
	 */
	public void moveOneDown() {
		setY(getY() + 1);
		if(determineCurrentHeightFromY() == (height) || (tooBig && prevHeight >= (GameScreen.columnHeight - 64 + GameScreen.distanceAAfterGoal))) {
			switchEnd = true;
		}
		int cHeight = determineCurrentHeightFromY();
		currentHeight = cHeight;
		if(switchEnd) {
			resizeFramesEnd(cHeight);
		}
		else {
			resizeFramesStart(cHeight);
			setY(GameScreen.columnY);
		}
	}
	
	/**
	 * This method sets whether or not the stroke is being held or not
	 * 
	 * @param h - Whether or not the stroke is being held or not
	 * 
	 * @author Justin Yau
	 */
	public void setHeld(boolean h) {
		currentBeingHeld = h;
	}
	
	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void holdstrokeFall() {
		addSequence(path, 100, 0, 420 - height, 64, height, 4);
		frames = new ArrayList<BufferedImage>(0);
		for(BufferedImage img: getFrames()) {
			frames.add(img);
		}
		cancel = false;
		currentBeingHeld = false;
		pause = false;
		while(!cancel) {
			while(pause) {
				sleep(0);
			}
			moveOneDown();
			if(isBeyondGoal(GameScreen.columnHeight + GameScreen.columnY + GameScreen.distanceAAfterGoal) && !currentBeingHeld) {
				handleRemove();
			}
			sleep(fallSpeed);
			update();
		}
	}
	
	/**
	 * This method will be utilized to determine whether or not the stroke has made it past the goal
	 * 
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the stroke has made it past the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal) {
		return (getY() + height - 64) > goal;
	}

	/**
	 * This method calculates the height of the stroke, for visuals. 
	 * 
	 * @author Justin Yau
	 */
	public static int determineHeight(int holdTime, int fallSpeed) {
		return (int) (holdTime / fallSpeed);
	}

}
