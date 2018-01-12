package mainGame;

import gui.GUIApplication;
import screens.GameScreen;

public class MainGUI extends GUIApplication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameScreen game;

	public static void main(String[] args) {
		MainGUI s = new MainGUI(1000,1000);
		Thread runner = new Thread(s);
		runner.start();
	}
	
	public MainGUI(int width, int height) {
		super(width, height);
		setVisible(true);
	}

	@Override
	public void initScreen() {
		game = new GameScreen(getWidth(), getHeight());
		setScreen(game);
	}

}
