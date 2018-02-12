package screens.components;

import java.util.ArrayList;

import gui.userInterfaces.FullFunctionScreen;

public class BottleClick {

	FullFunctionScreen parentScreen;
	ArrayList<ImageButton> bottleStack;
	ImageButton bottleOnSill;
	
	
	public BottleClick(FullFunctionScreen parentScreen) {
		this.parentScreen = parentScreen;
		addObjects();
	}

	public void addObjects() {
		parentScreen.addObject(bottleOnSill);
	}
	
	public void createBottleOnSill() {
		bottleOnSill = new ImageButton(0,
											0,
												0, 
													0, 
														"resources\\backgrounds\\bottle.jpg");
	}
	
}
