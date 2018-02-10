package mainGame.screens;

import java.util.List;

import gui.interfaces.Visible;
import mainGame.MainGUI;
import mainGame.components.CustomText;
import mainGame.screens.interfaces.ResizableScreen;

public class ImportScreen extends ResizableScreen {
	
	public ImportScreen(int width, int height) {
		super(MainGUI.screenWidth, MainGUI.screenHeight);

		startResize(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
		CustomText txt = new CustomText(200, 50, 300, 100, "Import", true);
		viewObjects.add(txt);
		
	}

}
