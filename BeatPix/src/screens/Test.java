package screens;

import java.util.List;

import gui.GUIApplication;
import gui.interfaces.Visible;
import gui.userInterfaces.*;

public class Test extends GUIApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557376208612089301L;
	
	public static Test test;
	public static Screen testScreen;
	
	public Test(int width, int height) {
		super(width, height);
		setVisible(true);
	}

	@Override
	public void initScreen() {
		testScreen = new StartScreenG(getWidth(), getHeight());
		setScreen(testScreen);
	}

	public static void main(String[] args) {
		test = new Test(960,540);
		Thread s = new Thread(test);
		s.run();
	}
}
