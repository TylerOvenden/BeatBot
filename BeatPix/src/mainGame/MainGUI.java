package mainGame;

import gui.GUIApplication;
import screens.GameScreen;

public class MainGUI extends GUIApplication {
	
	private GameScreen game;

	public static void main(String[] args) {
		MainGUI game = new MainGUI(1920,1080);
		Thread runner = new Thread(game);
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
