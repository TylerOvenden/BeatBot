package mainGame;

import gui.GUIApplication;
import screens.GameScreen;
import screens.LevelScreenG;

public class MainGUI extends GUIApplication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LevelScreenG game;

	public static void main(String[] args) {
		MainGUI s = new MainGUI(960,540);
		Thread runner = new Thread(s);
		runner.start();
	}
	
	public MainGUI(int width, int height) {
		super(width, height);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void initScreen() {
		game = new LevelScreenG(getWidth(), getHeight());
		setScreen(game);
	}

}
