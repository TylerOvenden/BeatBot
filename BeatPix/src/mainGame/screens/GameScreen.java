package mainGame.screens;

import java.util.List;

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
		Keystroke key = new Keystroke(100, 100, "resources/arrows/darrow.png");
		viewObjects.add(key);
	}

}
