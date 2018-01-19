package screens;

import java.util.ArrayList;
import java.util.List;

import gui.components.AnimatedComponent;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;

public class LevelScreenG extends FullFunctionScreen{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6292718307842016425L;
	private ArrayList columns;
	AnimatedComponent robot;
	private CustomPaneG keyIndicators;
	private FightPane scene;
	
	public LevelScreenG(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		keyIndicators = new CustomPaneG(this, 20, 450);
		keyIndicators.update();
		viewObjects.add(keyIndicators);
		
		scene = new FightPane(this, 500, 200);
		scene.update();
		scene.setVisible(true);
		scene.robotHit(true);
		viewObjects.add(scene);
	}

	
}
