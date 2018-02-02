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
	private CustomRectangle songArea;
	
	private Graphic background;	
	private Graphic songBanner;
	
	private TextLabel currency;	
	private TextLabel banner;
	private TextLabel credit;
	private TextLabel purchased;
	
	private Font bannerFont;
	private Font creditFont;
	private Font warningFont;

	private ArrayList<Song> songs;
	private ArrayList<Button> buttons;
	
	private Button songButton1;
	private Button songButton2;
	private Button songButton3;
	private Button songButton4;
	private Button songButton5;
	private Button yes;
	private Button no;
	private Button clickedButton;
	
	private int credits;
//	private int index;
	private int price;
	
	private TextArea text;
	
	private boolean clicked;
	
	public ShopScreen(int width, int height) 
	{
		super(width, height);
	}
	
	
	
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{

		credits = 25000;
		clicked = false;
		
		buttons = new ArrayList<Button>();

		/*
		songButton1 = new Button(0,0,100,70,"Song 1 | 1500 Credits", new Action() {
			
			@Override
			public void act() {
				index = buttons.indexOf(songButton1);
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
				
			}
		});
		
		songButton2 = new Button(0,50,100,70,"Song 2 | 1500 Credits", new Action() {
			
			@Override
			public void act() {
				index = buttons.indexOf(songButton2);
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
				
			}
		});

		songButton3 = new Button(0,100,100,70,"Song 3 | 1500 Credits", new Action() {
			
			@Override
			public void act() {
				index = buttons.indexOf(songButton3);	
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
				
			}
		});
		songButton4 = new Button(0,150,100,70,"Song 4 | 1500 Credits", new Action() {
			
			@Override
			public void act() {
				index = buttons.indexOf(songButton4);
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
				
			}
		});
		songButton5 = new Button(0,200,100,70,"Song 5 | 1500 Credits", new Action() {
			
			@Override
			public void act() {
				index = buttons.indexOf(songButton5);
				System.out.println(index);
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
				
			}
		});
		
		
		
		buttons.add(songButton1);
		buttons.add(songButton2);
		buttons.add(songButton3);
		buttons.add(songButton4);
		buttons.add(songButton5);
				
		
		*/
		bannerFont = new Font("resources//slkscr.ttf", Font.ITALIC, 25);
		creditFont = new Font("Verdana", Font.BOLD, 20);
		warningFont = new Font("Verdana", Font.BOLD, 18);
		//graphics
		background = new Graphic(40,0,getWidth(),getHeight(),"resources//shop_bg.png");
		viewObjects.add(background);
		songBanner = new Graphic(170,100,120,120,"resources//SongsBanner.png");
		viewObjects.add(songBanner);
		
		//background for credits, make new component later
		CustomRectangle creditBG = new CustomRectangle(135,60,190,40, Color.white,5);
		viewObjects.add(creditBG);
		
		String s = "Credits: "+credits; // add method "getCredits()" later
		credit = new TextLabel(150,60,200,200, s);
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
		ScrollablePane scroll = new ScrollablePane(this, 130,175,220,300);		
		
		//when user clicks yes to buy song
		yes = new Button(430,205,45,30, "Yes",Color.gray, new Action() 
		{			
			@Override
			public void act() 
			{
				int x = (credits - 1500);
				if (x >= 0)
				{	
					new Thread()
					{
						public void run()
						{
							try
							{
								credits -= 1500;
								credit.setText("Credits: "+ credits);
								credit.update();
								yes.setVisible(false);				
								text.setVisible(false);
								no.setVisible(false);
								purchased = new TextLabel(350,60,450,200, "Song Purchased! Added to Your Library");
								purchased.setFont(warningFont);
								purchased.setCustomTextColor(Color.white);
								viewObjects.add(purchased);
								Thread.sleep(1500);
								purchased.setVisible(false);
								
								scroll.remove(clickedButton);		
								buttons.remove(clickedButton);

								scroll.update();							
							}
							catch (InterruptedException e)
							{
								
							}
							//add transfer of song later
						}
					} .start();
				}
				else
				{
					new Thread()
					{
						public void run()
						{
							try
							{
								TextLabel warning = new TextLabel(350,60,450,200, "You Do Not Have Enough Credits");
								warning.setFont(warningFont);
								warning.setCustomTextColor(Color.red);
								viewObjects.add(warning);
								Thread.sleep(1500);
								warning.setVisible(false);			
							}
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
							
						}
					} .start();
				}
			}
		});
		viewObjects.add(yes);
		yes.setVisible(false);
		
		text = new TextArea(380,175,200,200,"Do You Want to Buy This Song?");				
		viewObjects.add(text);	
		text.setVisible(false);
		
		no = new Button(480,205,45,30, "No", Color.gray, new Action()
		{
			@Override
			public void act() 
			{
				yes.setVisible(false);				
				text.setVisible(false);
				no.setVisible(false);
			}
		});
		viewObjects.add(no);
		no.setVisible(false);
			
		for(int i = 0; i < 5; i++)
		{ 
			final int z = i;
			Button b = new Button(5,30*i,100,25,i+"",null);
			b.setAction(new Action() 
			{
				
				@Override
				public void act() 
				{
					clickedButton = b;				
					text.setVisible(true);
					yes.setVisible(true);				
					no.setVisible(true);				
				}
			});
			buttons.add(b);
		}
		
		for (int i = 0; i < buttons.size(); i++)
		{
			scroll.addObject(buttons.get(i));
		}
		
		scroll.update();
		viewObjects.add(scroll);
				
	}	

	public Action songAction()
	{
		Action a  = new Action() 
		{		
			@Override
			public void act() 
			{	
				
				text.setVisible(true);
				yes.setVisible(true);				
				no.setVisible(true);
			}						
		};

		return a;
		
	}
}


/*KEVIN'S CODE________________________________________________________________________________________________________________

Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
	
	@Override
	public void act() {
		// go back to main screen
		
	}
});
ScrollablePane charScroll = new gui.components.ScrollablePane(this, 130,500,220,300);
charScroll.setBorderWidth(3);
for(int i=0; i < 10; i++){
	charScroll.addObject(new Button(5,30*i,100,25,"Label "+(i+1), new Action() {
		
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
	}));
}
charScroll.update();

viewObjects.add(backButton);
viewObjects.add(charScroll);

*/