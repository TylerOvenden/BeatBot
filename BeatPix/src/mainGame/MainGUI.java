package mainGame;

import java.util.Timer;
import java.util.TimerTask;

import gui.GUIApplication;
import gui.userInterfaces.*;

import screens.InformationContainer;

import screens.MainMenuScreenG;
import screens.OptionsContainer;
import screens.StartScreenG;
import shop.CharacterSelectionScreen;
import shop.ShopScreen;

import screens.interfaces.Options;

public class MainGUI extends GUIApplication {

	/**
	 * 
	 */
	public static final int screenWidth = 960;
	public static final int screenHeight = 540;
	
	private static final long serialVersionUID = 6557376208612089301L;
	
	private Screen currentScreen;
	
	public static MainGUI test;
	
	public static StartScreenG start;
	public static MainMenuScreenG mainMenu;
	//public static LevelSelectG level;
	public static ShopScreen shop;
	public static OptionsContainer options;
	public static InformationContainer information;
	public static CharacterSelectionScreen character;
	
	public int x;
	
	private static String[] keys;
	private static int volume;
	
	public MainGUI(int width, int height) {
		super(width, height);
		setVisible(true);
		
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
		
		String[] temp = {"D","F","J","K"};
		keys = temp;
		
		setVolume(2);
		
		start = new StartScreenG(getWidth(),getHeight());
		mainMenu = new MainMenuScreenG(getWidth(),getHeight());
		//level = new LevelSelectG(getWidth(),getHeight());
		shop = new ShopScreen(getWidth(),getHeight());
		character = new CharacterSelectionScreen(getWidth(),getHeight());
		
		options = new OptionsContainer(getWidth(), getHeight(), (Options) mainMenu);
		information = new InformationContainer((Options) mainMenu);
		
		setScreen(start);
		start.start();
	}

	public static void main(String[] args) {
		test = new MainGUI(960,540);
		test.setTitle("BeatBot");
		Thread s = new Thread(test);
		s.run();
	}

	public void setFrameTitle(String s) {
		setTitle(s);
	}
	
	public static String[] getKeys() {
		return keys;
	}
	
	public static String getKeys(int idx) {
		return keys[idx];
	}
	
	public static void setKeys(int idx, String s) {
		MainGUI.keys[idx] = s;
	}
	
	public static int getVolume() {
		return volume;
	}

	public static void setVolume(int volume) {
		MainGUI.volume = volume;
	}
	
	//testing
}
