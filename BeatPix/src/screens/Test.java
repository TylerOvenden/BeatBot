package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gui.GUIApplication;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.*;

public class Test extends GUIApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557376208612089301L;
	
	public static Test test;
	public static ArrayList<Screen> screens;
	public static int START = 0;
	public static int MENU = 1;
	public static int CHARACTER = 1;
	
	int x;
	
	public static int[] options;
	//options [VOLUME,KEY1,KEY2,KEY3,KEY4]

	public Visible optionScreen;
	
	public Test(int width, int height) {
		super(width, height);
		setVisible(true);
		options = new int[5];

		Timer time = new Timer(); x = 0;
		time.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(x+"s");
				x++;
			}
		}, 0, 1);
	}

	@Override
	public void initScreen() {
		setScreen(new StartScreenG(getWidth(),getHeight()));
		//setScreen(new MainMenuScreenG(getWidth(),getHeight()));
	}

	public static void main(String[] args) {
		test = new Test(960,540);
		Thread s = new Thread(test);
		s.run();
	}
	
	//testing
}
