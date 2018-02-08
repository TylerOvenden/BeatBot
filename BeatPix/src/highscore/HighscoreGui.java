package highscore;

import java.io.IOException;

import gui.GUIApplication;

public class HighscoreGui extends GUIApplication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TempSongSelect highscore;
	
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
		try {
			highscore = new TempSongSelect(getWidth(), getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setScreen(highscore);
		
	}

}
