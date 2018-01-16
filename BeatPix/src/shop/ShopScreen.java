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
		viewObjects.add(background);
		
		//background for credits, make new component later
		CustomRectangle creditBG = new CustomRectangle(185,60,90,33, Color.white);
		viewObjects.add(creditBG);
		
		String credits = "Credits: "; // add method "getCredits()" later
		currency = new TextLabel(200,60,200,200, credits);
		viewObjects.add(currency);
		
		// where the new songs will be placed
		songArea = new CustomRectangle(130, 150, 225, 350, Color.white);
		viewObjects.add(songArea);
		
		//area where "SONGS" will be displayed, new component
	//	songBanner = new TextLabel(130, 120, 200,200, "Songs");
	//	ScrollablePane a = new ScrollablePane(null, null, 50, 50, 20, 30);
	//	viewObjects.add(a);
	}	

}
