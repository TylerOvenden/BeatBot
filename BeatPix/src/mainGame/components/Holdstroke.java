package mainGame.components;

import java.util.ArrayList;

import gui.interfaces.Visible;
import mainGame.components.interfaces.HoldstrokeInterface;

/**
 * This class essentially makes the scroll keystroke that the user will have to follow to maintain or increase accuracy.
 * 
 * @author Justin Yau
 *
 */
public class Holdstroke implements HoldstrokeInterface {
	
	private int fallSpeed; //The speed at which you would like the stroke to fall at
	private int holdTime; //The time the user has to hold down a particular key for this stroke
	private int height; //The height of the stroke for visuals
	private ArrayList<Visible> list; //The list of visible objects, consisting of the 2 keystrokes and 1 rectangle that make up this stroke

	/**
	 * Constructor creates 2 keystrokes and 1 rectangle that make up this stroke based on the properties given. 
	 * 
	 * @param x - X Coordinate of the indicator
	 * @param y - Y Coordinate of the indicator 
	 * @param path - Image file path (Ex: "resources/arrows/darrow.png")
	 * @param holdTime - The time the user has to hold down a particular key for this stroke
	 * @param fallSpeed - The speed at which you would like the stroke to fall at
	 * 
	 * @author Justin Yau
	 */
	public Holdstroke(int x, int y, int stime, String path, int holdTime, int fallSpeed) {
		this.holdTime = holdTime;
		this.fallSpeed = fallSpeed;
		determineHeight();
		list = new ArrayList<Visible>(0);
		Keystroke frontStroke = new Keystroke(x, y, stime, path);
		frontStroke.updateFallSpeed(fallSpeed);
		frontStroke.setHold(true);
		Keystroke backStroke = new Keystroke(x, y - height, stime, path);
		backStroke.setHold(true);
		backStroke.updateFallSpeed(fallSpeed);
		Rectanglu rect = new Rectanglu(x, y-height+25, 65, height);
		rect.updateFallSpeed(fallSpeed);
		list.add(backStroke);
		list.add(rect);
		list.add(frontStroke);
	}
	
	/**
	 * This method returns the list of visible objects that were created to represent the stroke. 
	 * 
	 * @return Returns the list of visible objects that were created to represent the stroke.
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Visible> getStrokes() {
		return list;
	}
	
	/**
	 * This method calculates and applies the height of the stroke, for visuals. 
	 * 
	 * @author Justin Yau
	 */
	public void determineHeight() {
		height = (int) (holdTime / fallSpeed);
	}

}
