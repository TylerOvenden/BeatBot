package screens;

import java.util.ArrayList;
import java.util.List;

import gui.components.FullFunctionPane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.ComponentContainer;
import screens.components.ScalablePixelBack;

public class OptionsContainer  {

	ScalablePixelBack background;
	
	
	public OptionsContainer(int w, int y, List<Visible> vObjects) {
		vObjects.add(new ScalablePixelBack(w/10,y/10,w*8/10,y*8/10,1.5));
	}

}
