package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gui.GUIApplication;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.*;
import mainGame.screens.ShopScreen;
import screens.interfaces.Options;

public class Test extends GUIApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557376208612089301L;
	
	private Screen currentScreen;
	
	public static Test test;
	
	public static StartScreenG start;
	public static MainMenuScreenG mainMenu;
	//public static LevelSelectG level;
	public static ShopScreen shop;
	public static OptionsContainer options;
	
	int x;
	
	public static String[] keys;
	public static int volume;
	//options [VOLUME,KEY1,KEY2,KEY3,KEY4]
	
	public Test(int width, int height) {
		super(width, height);
		setVisible(true);
		
		//keys = new String[4];
		String[] temp = {"D","F","J","K"};
		keys = temp;
		
		volume = 2;
		
		options = new OptionsContainer(getWidth(), getHeight(), (Options) mainMenu);
		
		Timer time = new Timer(); x = 0;
		time.scheduleAtFixedRate(new TimerTask() {
			
			private Screen previousScreen = currentScreen;

			@Override
			public void run() {
				//System.out.println(x+"s");
				x++;
				
				//Need to connect this with setScreen so the options is connected to the right screen
				if(currentScreen instanceof Options && currentScreen != previousScreen) {
					options = new OptionsContainer(getWidth(), getHeight(), (Options) currentScreen);
				}else {
					currentScreen = previousScreen;
				}
			}
		}, 0, 1);
	}

	@Override
	public void initScreen() {
		start = new StartScreenG(getWidth(),getHeight());
		mainMenu = new MainMenuScreenG(getWidth(),getHeight());
		//level = new LevelSelectG(getWidth(),getHeight());
		shop = new ShopScreen(getWidth(),getHeight());

		setScreen(start);
		start.scrollIn();
	}

	public static void main(String[] args) {
		test = new Test(960,540);
		Thread s = new Thread(test);
		s.run();
	}
	
	//testing
}
