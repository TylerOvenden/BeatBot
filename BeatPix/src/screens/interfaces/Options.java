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
	 */
	boolean inOptions();
	
	/**
	 * Will give the keycode to Test.test.options.readKey();
	 * 
	 */
	void passKeyCodeIntoOptions(KeyEvent e);
	
	//FullFunctionScreen Methods that are used
	void addObject(Visible a);

	void remove(Visible a);
	
	
}
