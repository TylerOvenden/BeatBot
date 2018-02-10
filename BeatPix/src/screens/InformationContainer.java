package screens;

import java.util.ArrayList;

import gui.components.Component;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.CustomText;
import screens.interfaces.Options;

public class InformationContainer {

	Options parentScreen; // Screen for popup 
	private Component blackBack;
	private ArrayList<CustomText> information;
	
	
	public InformationContainer(Options parentScreen) {
		this.parentScreen = (Options) parentScreen;
	}


	public void addObjects() {
		// TODO Auto-generated method stub
		
	}
}
