package mainGame.components;

import java.util.ArrayList;

import gui.components.AnimatedComponent;
import gui.interfaces.Visible;
import mainGame.components.interfaces.HoldstrokeInterface;

/**
 * This class essentially makes the scroll keystroke that the user will have to follow to maintain or increase accuracy.
 * 
 * @author Justin Yau
 *
 */
public class Holdstroke extends AnimatedComponent implements HoldstrokeInterface {
	
	private int fallSpeed; //The speed at which you would like the stroke to fall at
	private int holdTime; //The time the user has to hold down a particular key for this stroke
	private int height; //The height of the stroke for visuals

	public Holdstroke(int x, int y, int h, String path) {
		super(x,y,64,h);
		addSequence(path, 100, 0, 420, 64, h + 64, 4);
		Thread animation = new Thread(this);
		animation.start();
		update();
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
