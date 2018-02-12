package mainGame;

import gui.GUIApplication;
import mainGame.screens.ImportScreen;

public class Test extends GUIApplication {

	public Test(int width, int height) {
		super(width, height);
		setVisible(true);
	}

	@Override
	public void initScreen() {
		ImportScreen scrn = new ImportScreen(getWidth(), getHeight());
		setScreen(scrn);
	}

	public static void main(String[] args) {
		Test t = new Test(960, 540);
		Thread run = new Thread(t);
		t.run();
	}

}
