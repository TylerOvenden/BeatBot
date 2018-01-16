package shop;

import java.awt.Color;
import java.util.List;


import gui.components.*;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
public class ShopScreen extends FullFunctionScreen
{
	private Graphic background;	
	private TextLabel currency;
	private CustomRectangle songArea;
	


	public ShopScreen(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{
		background = new Graphic(0,0,getWidth(),getHeight(),"resources//sample_bg.gif");
//		viewObjects.add(background);
		
		String credits = "Credits: "; // add method "getCredits()" later
		currency = new TextLabel(200,60,200,200, credits);
		currency.setForeground(Color.blue);
		viewObjects.add(currency);
		
		songArea = new CustomRectangle(200, 80);
		viewObjects.add(songArea);
		
	}	

}
