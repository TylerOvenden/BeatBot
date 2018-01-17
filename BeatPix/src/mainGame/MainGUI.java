package mainGame;

import gui.GUIApplication;
import mainGame.components.Song;
import mainGame.saving.FileP;
import mainGame.screens.*;

public class MainGUI extends GUIApplication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameScreen game;

	public static void main(String[] args) {
		MainGUI s = new MainGUI(960,540);
		Thread runner = new Thread(s);
		runner.start();
	}
	
	public MainGUI(int width, int height) {
		super(width, height);
		setVisible(true);
	}

	@Override
	public void initScreen() {
		Song song = new Song("DreadnoughtMastermind(xi+nora2r).csv");
		game = new GameScreen(getWidth(), getHeight(), song);
		setScreen(game);
	}

}
