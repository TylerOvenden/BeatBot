package shop;

import gui.GUIApplication;

public class ShopKevinGUI extends GUIApplication {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1943768449913434317L;
	private ShopScreenKevin shop;

	public static void main(String[] args) 
	{
		ShopKevinGUI s = new ShopKevinGUI(960,540);
		Thread runner = new Thread(s);
		runner.start();
	}
	
	public ShopKevinGUI(int width, int height) 
	{
		super(width, height);
		setVisible(true);
	}
	public void initScreen() 
	{
		shop = new ShopScreenKevin(getWidth(), getHeight());
		setScreen(shop);
	}

}
