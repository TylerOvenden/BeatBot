package highscore;

import gui.GUIApplication;

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
		highscore = new HighscoreScreen(getWidth(), getHeight(),true,1000000,(float) 99.99);
		setScreen(highscore);
		
	}

}
