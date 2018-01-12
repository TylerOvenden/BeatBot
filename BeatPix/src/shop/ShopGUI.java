package shop;

import gui.GUIApplication;
import shop.ShopGUI;

public class ShopGUI extends GUIApplication {

	
	private ShopScreen shop;

	public static void main(String[] args) 
	{
		ShopGUI s = new ShopGUI(960,540);
		Thread runner = new Thread(s);
		runner.start();
	}
	
	public ShopGUI(int width, int height) 
	{
		super(width, height);
		setVisible(true);
	}
	public void initScreen() 
	{
		shop = new ShopScreen(getWidth(), getHeight());
		setScreen(shop);
	}

}
