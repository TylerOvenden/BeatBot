package highscore;

import java.io.IOException;

import gui.GUIApplication;
import mainGame.components.Song;

public class HighscoreGui extends GUIApplication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HighscoreScreen highscore;
	
	public static void main(String[] args) 
	{
		HighscoreGui g = new HighscoreGui(960,540);
		Thread runner = new Thread(g);
		runner.start();
	}
	public HighscoreGui(int width, int height) {
		super(width, height);
		setVisible(true);
		
	}

	@Override
	public void initScreen() {
		Song a=new Song("resources/realMaps/DreadnoughtMastermind(xi+nora2r)-HD.csv");
		a.addScoreAndAccuracy(444, (float) 4.53);
		a.addScoreAndAccuracy(999999, (float) 43);
		a.addScoreAndAccuracy(999299, (float) 43);
		highscore = new HighscoreScreen(getWidth(), getHeight(), false, 999999, 49.27, a, a);
		setScreen(highscore);
		
	}

}
