package screens;

import java.util.List;

import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;

public class LevelScreenG extends FullFunctionScreen{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6292718307842016425L;

	public LevelScreenG(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
	}
	
	private CustomPaneG keyIndicators;

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		Graphic backgroundImg = new Graphic(0, 0, "resources/puppy.jpg");
		keyIndicators = new CustomPaneG(this, 20, 450);
		keyIndicators.update();
		viewObjects.add(backgroundImg);
		viewObjects.add(keyIndicators);
	}

	
}
