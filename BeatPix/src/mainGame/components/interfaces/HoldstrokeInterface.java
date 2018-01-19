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
	 * This method returns the list of visible objects that were created to represent the stroke. 
	 * 
	 * @return Returns the list of visible objects that were created to represent the stroke.
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Visible> getStrokes();
	
	/**
	 * This method calculates and applies the height of the stroke, for visuals. 
	 * 
	 * @author Justin Yau
	 */
	public void determineHeight();
	
}
