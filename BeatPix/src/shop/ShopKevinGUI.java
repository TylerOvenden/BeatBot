package shop;

import gui.GUIApplication;

public class ShopKevinGUI extends GUIApplication {

	
	/**
	 * 
	 */
	public static ShopKevinGUI  s;
	private static final long serialVersionUID = -1943768449913434317L;
	public CharacterSelectionScreen shop;
	public ShopScreen shop2;

	public static void main(String[] args) 
	{
		s = new ShopKevinGUI(960,540);
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
		shop = new CharacterSelectionScreen(getWidth(), getHeight());
		shop2 = new ShopScreen(getWidth(),getHeight());
		setScreen(shop);
	}

}
