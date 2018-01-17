package screens;

import java.util.ArrayList;
import java.util.List;

import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class LevelSelectG extends FullFunctionScreen{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6265786684466337399L;
	public ArrayList<ImageButton> buttons;
	
	public LevelSelectG(int width, int height) {
		super(width, height);
		}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
	}
	
	// change to game screen calling with the appropriate songs
}
