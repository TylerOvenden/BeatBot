package mainGame.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.components.AnimatedComponent;

/*
 * 
 * This class will be mainly coded by Justin Yau
 * PERIOD 4 & 5 - AP Computer Science Java
*/

/*
 * PLANNING: 
 * - Incorporate a method that will enable us to update y position
 * - MAYBE - Incorporate a method that will enable us to update y position
 * - Override the update method to update y position (i.e. downward scrolling)
 * - Have another thread running that'll check the y position and remove the object upon passing the stroke location
 * - Connect this to Steven's class that'll handle the accuracy decider and whether the stroke was good, bad, or etc.
 */

/**
 * This component is the stroke indicator that will be on display throughout the game
 * Players will have to time their strokes such that it almost matches the time when this indicator passes through the stroke location
 * @author Justin Yau
 */
public class Keystroke extends AnimatedComponent {

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

}
