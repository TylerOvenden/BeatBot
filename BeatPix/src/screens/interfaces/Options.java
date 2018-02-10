package screens.interfaces;

import java.awt.event.KeyEvent;

import gui.interfaces.Visible;
import screens.components.ScalablePixelBack;

public interface Options{

	/**
	 * Disables or enables all the clickables of the screen
	 * so the option pop up is the only enabled clickables
	 * @param b
	 */
	void toggleButtons(boolean b);

	/*
	 * Returns if the options is being used on the screen
	 * 
	 * !!!THIS IS OPTIONAL!!!
	 * Only if keyPressed isn't used elsewhere on the screen
	 */
	boolean inOptions();
	
	/**Method Called by Other Screens**
	 * 
	 * Method called by other screens whenever
	 * kepPress/keyReleased is called in order
	 * to determine which key the user chose
	 * 
	 * Example:
	   	public void keyPressed(KeyEvent e) {
			passKeyCodeIntoOptions(e);
		}
	 * 
	 * Checks if key pressed is not in use:
	 * -If in unavailable, it will stay phase 0
	 *  where it will wait for another key press
	 * -If available, it will auto set it and
	 *  go into the non selecting phase
	 */
	void passKeyCodeIntoOptions(KeyEvent e);
	
	//FullFunctionScreen Methods that are used
	void addObject(Visible a);

	void remove(Visible a);
	
	
}
