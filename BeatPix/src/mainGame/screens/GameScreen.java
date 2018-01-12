package mainGame.screens;

import java.util.List;

import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.Keystroke;

public class GameScreen extends FullFunctionScreen {

	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
		/*
		 * Justin Yau's Notes/Plans:
		 * The object the is added later will appear on top of previous added objects
		 * key.getY() will return the Y-Coordinate of the component
		 * key.setY() will set the Y-Coordinate of the component
		 * Changing the Y through this will automatically update the component
		 * 
		 * - Read the map file in this class
		 * - Spawn the keystroke into the lanes in this class
		*/
		Keystroke key = new Keystroke(100, 100, "resources/arrows/darrow.png");
		viewObjects.add(key);
		
	}

}
