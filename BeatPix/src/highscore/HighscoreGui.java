package highscore;

import gui.GUIApplication;
import Highscore.HighscoreGUI;

public class HighscoreGui extends GUIApplication{

	private HighscoreScreen highscore;
	
	public static void main(String[] args) 
	{
		HighscoreGui s = new HighscoreGui(1000,1300);
		Thread runner = new Thread(s);
		runner.start();
	}
	public HighscoreGui(int width, int height) {
		super(width, height);
		
	}

	@Override
	public void initScreen() {
		
		
	}

}
