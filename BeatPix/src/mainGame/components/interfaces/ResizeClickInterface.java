package mainGame.components.interfaces;

/**
 * This interface will contain the essential methods to enable the rescaling of the clickable objects on the screen <br>
 * when the window is resized.
 * @author Justin Yau
 *
 */
public interface ResizeClickInterface {
	
	/**
	 * This method calculates and sets the new scaled widths and heights based on the inputted scales
	 * @param xScale - The x scale that was applied to the button
	 * @param yScale - The y scale that was applied to the button
	 * 
	 * @author  Justin Yau
	 */
	public void updateScales(double xScale, double yScale);
	
	
}
