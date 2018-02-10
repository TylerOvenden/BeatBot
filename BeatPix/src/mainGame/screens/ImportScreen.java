package mainGame.screens;

import java.util.List;

import gui.interfaces.Visible;
import mainGame.MainGUI;
import mainGame.screens.interfaces.ResizableScreen;

public class ImportScreen extends ResizableScreen {
	
	public ImportScreen(int width, int height) {
		super(MainGUI.screenWidth, MainGUI.screenHeight);

		startResize(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub

	}

}
