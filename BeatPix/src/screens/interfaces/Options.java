package screens.interfaces;

import gui.interfaces.Visible;
import screens.components.ScalablePixelBack;

public interface Options{

	/**
	 * Disables or enables all the clickables of the screen
	 * so the option pop up is the only enabled clickables
	 * @param b
	 */
	void toggleButtons(boolean b);

	void addObject(Visible a);

	void remove(Visible a);
	
	
}
