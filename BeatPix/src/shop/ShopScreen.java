package shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.Song;
public class ShopScreen extends FullFunctionScreen
{
	private Graphic background;	
	private Graphic songBanner;
	private TextLabel currency;
	private CustomRectangle songArea;
	private TextLabel banner;
	private TextLabel credit;
	private Font bannerFont;
	private Font creditFont;
	private ArrayList<Song> songs;
	private Button songButton1;
	
	public ShopScreen(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{
		
		try 
		{
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources//Munro.ttf")));
		}
		catch (IOException|FontFormatException e)
		{
			
		}
				
		
		bannerFont = new Font("resources//slkscr.ttf", Font.ITALIC, 25);
		creditFont = new Font("Verdana", Font.BOLD, 20);
		
		//graphics
		background = new Graphic(40,0,getWidth(),getHeight(),"resources//shop_bg.png");
		viewObjects.add(background);
		songBanner = new Graphic(170,100,120,120,"resources//SongsBanner.png");
		viewObjects.add(songBanner);
		
		//background for credits, make new component later
		CustomRectangle creditBG = new CustomRectangle(135,60,190,40, Color.white,5);
		viewObjects.add(creditBG);
		
		String credits = "Credits: "; // add method "getCredits()" later
		credit = new TextLabel(150,60,200,200, credits);
		credit.setCustomTextColor(Color.white);
		credit.setFont(creditFont);
		viewObjects.add(credit);
		
		// where the new songs will be placed
		songArea = new CustomRectangle(110, 150, 260, 350, Color.CYAN,25);
		viewObjects.add(songArea);
		
		//area where "SONGS" will be displayed
		banner = new TextLabel(190,100,200,200, "Songs");
		banner.setCustomTextColor(Color.white);
		banner.setFont(bannerFont);
	//	viewObjects.add(banner);

		//scroll bar, contains the songs
		ScrollablePane a = new ScrollablePane(this, 130,175,220,300);		
		
			a.addObject(songButton1 = new Button(5,60,100,25,"Song                        Cost: 1000         ", new Action() {
				
				@Override
				public void act() {
					TextArea a = new TextArea(370,175,200,200,
							"Do You Want to Buy This Song?"
							);
					viewObjects.add(a);
					
					Button yes = new Button(320,125,200,200, "Yes", null);
					viewObjects.add(yes);
					
					Button no = new Button(360,125,200,200, "No", null);
					viewObjects.add(no); // 
				}
			})); // change to custom buttons that can access song
		
		
		a.update();
		viewObjects.add(a);
	}	

}
