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
	public Keystroke(int x, int y, String path) {
		super(x, y, 65, 65);
		//Path should be inputted something like "resources/arrows/darrow.png"
		addSequence(path, 100, 0, 0, 64, 64, 4);
		//To make the component animated, we need to make a thread
		Thread animation = new Thread(this);
		animation.start();
		update();
	}

	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear
	 * 
	 * @author Justin Yau
	 */
	public void keystrokeFall() {
		while(!(isBeyondGoal(GameScreen.columnHeight + GameScreen.columnY))) {
			setY(getY() + 1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update();
		}
		GameScreen.game.removeStroke(this);
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
