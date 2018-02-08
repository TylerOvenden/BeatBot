package mainGame.components.interfaces;

import mainGame.components.Holdstroke;
import mainGame.components.Keystroke;

/**
 * This interface will contain all the essential methods used by Justin's Code for registering the pressing and release of strokes
 * Meant for STEVEN
 * 
 * @author Justin Yau
 *
 */
public interface JustinTimingInterface {

	/**
	 * Calculate the accuracy based on the stroke's current position and the stroke's target area
	 * 
	 * @param stroke - The stroke that you would need to perform calculations on 
	 */
	public void calculateAccuracy(Keystroke stroke);
	
	/**
	 * Calculate the accuracy of the first part of the hold stroke based on current positions
	 * 
	 * @param stroke - The stroke that you would need to perform calculations on
	 */
	public void calculateFirstAccuracy(Holdstroke stroke);
	
	/**
	 * Calculate the accuracy of the release part of the hold stroke based on positions
	 * 
	 * @param stroke - The stroke that you would need to perform calculations on
	 */
	public void calculateEndAccuracy(Holdstroke stroke);
	
	/**
	 * Calculate the new accuracy of the player given a new miss 
	 */
	public void missAccuracy();
	
}
