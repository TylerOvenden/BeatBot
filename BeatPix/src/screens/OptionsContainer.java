package screens;

import java.util.ArrayList;
import java.util.List;

import gui.components.Button;
import gui.components.FullFunctionPane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.ComponentContainer;
import screens.components.ScalablePixelBack;

public class OptionsContainer  {

	ScalablePixelBack background;
	List<Visible> vObjects; int x; int y;
	
	public OptionsContainer(int x , int y, List<Visible> vObjects) {
		this.vObjects = vObjects;
		this.x = x;
		this.y = y;
	}
	
	public void addObjects() {
		vObjects.add(new ScalablePixelBack(x/10,y/10,x*8/10,y*8/10,1.5));
		vObjects.add(new Button(50, 50, 300, 300, "WORDS", null));
	}

}
