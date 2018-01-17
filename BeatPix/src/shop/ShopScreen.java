package shop;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


import gui.components.*;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
public class ShopScreen extends FullFunctionScreen
{
	private Graphic background;	
	private TextLabel currency;
	private CustomRectangle songArea;
	private TextLabel banner;
	private TextLabel credit;
	private Font bannerFont;
	private Font creditFont;
	private ArrayList songs;
	public ShopScreen(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{
	//	Font.createFont(1, "resources//slkscr.ttf");
		bannerFont = new Font("Verdana", Font.ITALIC, 25);
		creditFont = new Font("Verdana", Font.BOLD, 20);
		background = new Graphic(0,0,getWidth(),getHeight(),"resources//sample_bg.gif");
		viewObjects.add(background);
		
		//background for credits, make new component later
		CustomRectangle creditBG = new CustomRectangle(135,60,190,40, Color.white);
		viewObjects.add(creditBG);
		
		String credits = "Credits: "; // add method "getCredits()" later
		credit = new TextLabel(150,60,200,200, credits);
		credit.setCustomTextColor(Color.white);
		credit.setFont(creditFont);
		viewObjects.add(credit);
		
		// where the new songs will be placed
		songArea = new CustomRectangle(130, 150, 225, 350, Color.gray);
		viewObjects.add(songArea);
		
		//area where "SONGS" will be displayed
		banner = new TextLabel(160, 100,200,200, "Songs");
		banner.setCustomTextColor(Color.white);
		banner.setFont(bannerFont);
		viewObjects.add(banner);
	//	ScrollablePane a = new ScrollablePane(null, null, 50, 50, 20, 30);
	//	viewObjects.add(a);
	}	

}
