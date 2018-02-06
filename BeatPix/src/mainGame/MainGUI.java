package mainGame;

import java.util.ArrayList;

import gui.GUIApplication;
import gui.interfaces.Visible;
import gui.userInterfaces.Screen;
import screens.*;
import screens.StartScreenG;
import screens.MainMenuScreenG;
import screens.Test;
import mainGame.components.Song;
import mainGame.saving.FileP;
import mainGame.screens.*;

public class MainGUI extends GUIApplication {
	
private static final long serialVersionUID = 6557376208612089301L;
	
	public static MainGUI test;
	public static ArrayList<Screen> screens;
	public static int START = 0;
	public static int MENU = 1;
	public static int CHARACTER = 1;
	
	private MainMenuScreenG menu;
	
	public static int[] options;
	//options [VOLUME,KEY1,KEY2,KEY3,KEY4]

	public Visible optionScreen;
	
	public MainGUI(int width, int height) {
		super(width, height);
		setVisible(true);
		options = new int[5];
	}

	@Override
	public void initScreen() {
		setScreen(new StartScreenG(getWidth(),getHeight()));
		//setScreen(new MainMenuScreenG(getWidth(),getHeight()));
	}

	public static void main(String[] args) {
		test = new MainGUI(960,540);
		Thread s = new Thread(test);
		s.run();
	}
	
	public void saveMenuScreen(MainMenuScreenG s) {
		menu = s;
	}
	
	public MainMenuScreenG getMenu() {
		return menu;
	}
	
	public void changeScreen(Screen s) {
		this.setScreen(s);
	}

}
