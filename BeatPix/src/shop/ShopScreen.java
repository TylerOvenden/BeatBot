package shop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.GUIApplication;
import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.CustomText;
import mainGame.components.Song;
import screens.Test;
import screens.components.ImageButton;

public class ShopScreen extends FullFunctionScreen implements CreditChanger
{
	//Daniel Fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private static final long serialVersionUID = 1L;

	private CustomRectangle songArea;
	
	private Graphic background;	
	
	private ScrollablePane scroll;
	
	private CustomText songBanner;
	private CustomText credit;
	private CustomText textLine1;
	private CustomText textLine2;
	private CustomText noText;
	private CustomText yesText;
	private CustomText purchasedTextLine1;
	private CustomText purchasedTextLine2;
	
	private static ArrayList<Song> songs;
	private ArrayList<ImageButton> buttons;
	private ArrayList<CustomText> customText;
	private ArrayList<MultiLineCustomText> multiText;

	private ImageButton yes;
	private ImageButton no;
	private ImageButton clickedButton;
	
	private int credits;
	private int price;
	private int idx;
	
	private CustomText text;
	private Graphic creditBorder;

	private Graphic scrollBorder;

	private Graphic textBorder;
	
	private String[] texts; 

	
	//Kevin Fields-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private ArrayList<Button> buttonList;
	private ArrayList <Button> confirmButton;
	private ArrayList<Button> yesButton;
	private ArrayList<Integer> indexList;
	private String[] imageNames;
	private ArrayList<ImageButton> images;
	
	private Graphic border; //for the text
	private Graphic border2; //for the unlock
	
	private ScrollablePane charScroll;
	private Button noButton;
	private int numChars;
	private int index;
	
	private CustomText back;
	private CustomText yesK;
	private CustomText noK;
	private CustomText textKev;
	private CustomText unlockedText;
	private CustomText okay;
	
	private Graphic backBorder;
	

	public ShopScreen(int width, int height) 
	{
		super(width, height);

	}
	
	
	
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{
	
		
		changeCredits(getCredits()+1500);
		credits = getCredits();
		//Daniel components~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	
		buttons = new ArrayList<ImageButton>();
		customText = new ArrayList<CustomText>();
		multiText = new ArrayList<MultiLineCustomText>();
		
		

		//graphics
			
 		background = new Graphic(0,0,getWidth(),getHeight(),"resources//backgrounds//shop_bg1.png");
 		viewObjects.add(background);
		
 		creditBorder = new Graphic(90,40,260,195,"resources//TransparentButtonA.png");
		viewObjects.add(creditBorder);
		
		
		
		
		songBanner = new CustomText(123,135,200,150,"Songs",true);
		viewObjects.add(songBanner);
		
		
		String s = "Credits:"+getCredits(); 
		credit = new CustomText(110,58,200,200, s,true);
		viewObjects.add(credit);
		
		
		textLine1 = new CustomText(370,195,250,280, "Do you want to",true); 			
		textLine2 = new CustomText(370,220,250,280,"buy this song?",true);
		
		purchasedTextLine1 = new CustomText(417,200,150,120, "Song",true);
		purchasedTextLine2 = new CustomText(355,245,276,276, "Purchased",true);
		
		//scroll bar, contains the songs
		scroll = new ScrollablePane(this, 110,195,220,260);		
		scroll.setBackground(new Color(0,0,0));
		scroll.update();
		textBorder = new Graphic(340,160,305,405,"resources//shop//TransparentButtonB.png");	
		viewObjects.add(textBorder);
		//when user clicks yes to buy song
		yes = new ImageButton(360,255,125,50, "resources\\ui\\buttons\\buttonwithrivet.png");
		yesText = new CustomText(390,258,60,60, "yes",false); 	
		
		yes.setAction(new Action() 
		{			
			@Override
			public void act() 
			{
				int x = (getCredits() - 1500);
				if (x >= 0)
				{	
					new Thread()
					{

						public void run()
						{
							try
							{
								//purchase of song
								credits -= 1500;
								credit.setText("Credits: "+ credits);
								credit.update();
								
								//set components invisible after pressing button
								setInvis(false);
								
								
								viewObjects.add(purchasedTextLine1);
								viewObjects.add(purchasedTextLine2);
								
								Thread.sleep(1000);
								textBorder.setVisible(false);
								purchasedTextLine1.setVisible(false);
								purchasedTextLine2.setVisible(false);
								
								removeButton();
								
								scroll.update();	
								// transfer of song
									
							}
							catch (InterruptedException e)
							{
								
							}
							
						}

						//
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
								
								CustomText warningLine1 = new CustomText(363,200,260,260, "Not Enough",true);
								CustomText warningLine2 = new CustomText(372,240,246,246, "Credits",true);
								
								viewObjects.add(warningLine1);
								viewObjects.add(warningLine2);
								
								setInvis(false);
								
								Thread.sleep(1000);
								
								textBorder.setVisible(false);
								warningLine1.setVisible(false);		
								warningLine2.setVisible(false);
								
								
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
		yes.setEnabled(true);
		viewObjects.add(yes);
		viewObjects.add(yesText);
		yes.setVisible(false);		
		yesText.setVisible(false);
		
		
		
		
		viewObjects.add(textLine1);
		viewObjects.add(textLine2);
		
		textBorder.setVisible(false);
		textLine1.setVisible(false);
		textLine2.setVisible(false);
		
		no = new ImageButton(500,255,125,50, "resources\\ui\\buttons\\buttonwithrivet.png");
		noText = new CustomText(540,258,50,40, "no",false);
		no.setAction(new Action()
		{
			@Override
			public void act() 
			{
				setInvis(false);
				textBorder.setVisible(false); //
			}
		});
		no.setEnabled(true);
		viewObjects.add(no);
		viewObjects.add(noText);
		no.setVisible(false);
		noText.setVisible(false);
			
		addButtons();
		
		
			
		scroll.update();
		viewObjects.add(scroll);
				
	
//		scrollBorder = new Graphic(90,175,350,325,"resources//shop//border.png");
//		viewObjects.add(scrollBorder);
		
		//Kevin Components------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//
		buttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		confirmButton = new ArrayList<Button>();
		
		indexList = new ArrayList<Integer>();
		images = new ArrayList<ImageButton>();
		back = new CustomText(750, 60, 95, 50, "Back", true);
		
		yesK = new CustomText(405,405,50,75,"Yes", true);
		noK = new CustomText(507,405,45,50,"No", false);
		okay = new CustomText(460, 270, 40, 100,"Okay",true);
		
		imageNames = new String[] {"resources/sprites/redGuy.png", "resources/sprites/greenGuy.png", "resources/sprites/whiteGuy.png"};
		
		for(int i = 0; i < imageNames.length; i ++) {
			images.add(new ImageButton(390, 180, 190, 300, imageNames[i]));
		}
		backBorder = new Graphic(700,45,200,200,"resources//TransparentButtonA.png");
		//the ten should be number chars that the player should unlock
		numChars = 3;
		//create the back button
		createIntList(numChars);
		Button backButton = new Button(705, 50, 190, 30, null, Color.GRAY, new Action() {
			
			@Override
			public void act() {
				
				MainGUI.test.setScreen(MainGUI.mainMenu);
				setButEnable(true);
				MainGUI.mainMenu.changeIdle();
				setThings1Vis(false);
				setAllYesButVisFalse();
				setThings2Vis(false);
				setAllConfButVisFalse();
			
			}
		});
		
		//create the panel 
		charScroll = new ScrollablePane(this, 650, 100, 250, 400);
		charScroll.setBorderWidth(3);
		
		//create the list of Buttons and add it to panel
		for(int i=0; i < numChars; i++){ 
			//got the index number
			final int x = i;
			buttonList.add(new Button(5,30*i,100,25,"Button "+i, new Action() {
				int j = x;
				@Override
				public void act() {
					
					setThings1Vis(true);
					setYesButVisExceptThis(j);
					index = indexList.indexOf(j);
					setButEnable(false);
				}
			}));
			charScroll.addObject(buttonList.get(i));
		}
		
		//create arrayList of YesButtons
		for(int k = 0; k < numChars; k++) {
			final int z = k;
			yesButton.add(new Button(400, 400, 55, 35, "", Color.GRAY, new Action() {
				int j = z;
				@Override
				public void act() {
					// i guess to have the images, and set the a boolean to true and a textfield says unlock
					 setThings2Vis(true);
					 setThings1Vis(false);
					 setAllYesButVisFalse();
					 setConfButVisExceptThis(j);
					 
						
				}
			}));
		}
		//create arrayList of confirmButtons
		for(int l = 0; l < numChars; l ++) {
			final int y = l;
			confirmButton.add(new Button(450, 265, 55, 35, "", Color.GRAY, new Action() {
				int a = y;
				@Override
				public void act() {
					setThings2Vis(false);
					setAllConfButVisFalse();
					for (int i = index; i < buttonList.size(); i++)
					{
						if (buttonList.get(i).getY() != 0)
						{
							buttonList.get(i).move(buttonList.get(i).getX(), (buttonList.get(i).getY()-30), 10);
							charScroll.update();
						}
					setButEnable(true);

					}
					charScroll.remove(buttonList.get(a));
					//buttonList.get(a).setVisible(false);
					charScroll.update();
					indexList.remove(index);
					
				}
			}));
		}
		
		 //create all the things 
		 border2 = new Graphic(337, 180, 305,305,"resources//shop//TransparentButtonB.png");
		 unlockedText = new CustomText(390, 210, 200, 370, "UnLocked",false);
		 
		 
		 border = new Graphic(380, 80, 230, 430, "resources//TransparentButtonC.png");
		 textKev = new CustomText(390, 110, 200, 370, "Unlock" ,true);

		 noButton = new Button(500, 400, 55, 35, "", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				 setThings1Vis(false);
				 setAllYesButVisFalse();
				 setButEnable(true);
			}
		});
		 
		 
		 //visible all the things
		 setThings1Vis(false);
		 setAllYesButVisFalse();
		 setThings2Vis(false);
		 setAllConfButVisFalse();
		 
		 
		
		//add the objects
		viewObjects.add(backButton);
		viewObjects.add(backBorder);
		viewObjects.add(back);
		viewObjects.add(border);
		viewObjects.add(border2);
		viewObjects.add(textKev);
		charScroll.update();
		viewObjects.add(charScroll);
		viewObjects.add(noButton);
		for(int a = 0; a < numChars; a++) {
			viewObjects.add(confirmButton.get(a));
			viewObjects.add(images.get(a));
			viewObjects.add(yesButton.get(a));
		}
		viewObjects.add(unlockedText);
		viewObjects.add(yesK);
		viewObjects.add(noK);
		viewObjects.add(okay);
	}	
	//daniel methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void removeButton()
	{
		
		idx = buttons.indexOf(clickedButton);

		scroll.remove(clickedButton);				
		buttons.remove(clickedButton);
		scroll.remove(customText.get(idx));
		customText.remove(idx);
		scroll.update();
		
		for (int i = idx; i < buttons.size(); i++)
		{
			
			if (buttons.get(i).getY() != 0)
			{
				buttons.get(i).move(buttons.get(i).getX(), (buttons.get(i).getY()-50), 10);
				scroll.update();
				customText.get(i).move(customText.get(i).getX(), (customText.get(i).getY()-50), 10);
				scroll.update();
			}
			scroll.update();
		}
		
		MainGUI.test.mySongs.get(idx).setUnlock(true);
	}
	public void addButtons()
	{
		String[] texts = {"Adrenaline","Blow Out","Carribean Pirates","Summer Vibes","Waiting for Love","Fairy Tail Theme","One Piece Theme","Shingeki No Kyogin","Hitorigoto","I Love You","Hime Hime","Neptune"};
		
		for(int i = 0; i < texts.length; i++)
		{ 

			final int j = i;
				ImageButton b = new ImageButton(0,(i*52)+5,220,70,"resources\\ui\\buttons\\buttonwithrivet.png");
				b.setAction(new Action() 
				{
					
					@Override
					public void act() 
					{
						clickedButton = b;				
						setInvis(true);	
						buttonList.get(j).setEnabled(false);
					}
				});
				
				b.setEnabled(true);
				buttons.add(b);
				multiText.add(new MultiLineCustomText(-20 + getWidth()*55/960, (i*52) + getHeight()*17/540, 150, 50,texts[i],scroll, 12));
		//		customText.add(new CustomText(-20 + getWidth()*55/960, (i*52) + getHeight()*17/540, 200 - 210*100/399, 120, texts[i],false));
			
				buttons.get(i).setUnhoverAction(new Action()
				{
					public void act() 
					{
						GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				});
				
				buttons.get(i).setHoverAction(new Action()
				{
					public void act() 
					{
						GUIApplication.mainFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
		}
		
		for (int i = 0; i < buttons.size(); i++)
		{
			scroll.addObject(buttons.get(i));
		//	scroll.addObject(customText.get(i));
			multiText.get(i).addToScreen();
		}
	}




	public void setInvis(boolean b)
	{			
		if (b)
		{
			textBorder.setVisible(true);
			noText.setVisible(true);
			yesText.setVisible(true);
			textLine1.setVisible(true);
			textLine2.setVisible(true);
			yes.setVisible(true);				
			no.setVisible(true);
		}
		else
		{
		//	textBorder.setVisible(true);
			noText.setVisible(false);
			yesText.setVisible(false);
			textLine1.setVisible(false);
			textLine2.setVisible(false);
			yes.setVisible(false);				
			no.setVisible(false);
		}
	}
	
	
	
	
	
	
	// kevin methods----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void createIntList(int a) {
		for(int i = 0; i < a; i++) {
			indexList.add(i);
		}
	}
	//helper methods

	//things that are not buttons doesnt not consist of the unlock and confirm button and soon images, visibility = b
	public void setThings1Vis(boolean b) {
		 border.setVisible(b);
		 textKev.setVisible(b);
		 noButton.setVisible(b);
		 yesK.setVisible(b);
		 noK.setVisible(b);
	}
	
	//things that things1 doesnt consist of, visibility = b
	public void setThings2Vis(boolean b) {
		 border2.setVisible(b);
		 unlockedText.setVisible(b);
		 okay.setVisible(b);
	}
	
	 //turn all yesButtons, visibility = false;
	public void setAllYesButVisFalse() {
		for(int i = 0; i < yesButton.size(); i ++) {
			yesButton.get(i).setVisible(false);
			images.get(i).setVisible(false);
		}
	}
	//turn all  yes button visibility that are false except for the  chosen one
	public void setYesButVisExceptThis(int i) {
		for(int x = 0; x < yesButton.size(); x++) {
			if(x != i) {
				yesButton.get(x).setVisible(false);
				images.get(x).setVisible(false);
			}
			yesButton.get(i).setVisible(true);
			images.get(i).setVisible(true);
		}
	}
	
	 //turn all confirmButtons with the images, visibility = false;
	public void setAllConfButVisFalse() {
		for(int i = 0; i < confirmButton.size(); i ++) {
			confirmButton.get(i).setVisible(false);
		}
	}
	//turn all  confirm button and respected image visibility that are false except for de chosen one
	public void setConfButVisExceptThis(int i) {
		for(int x = 0; x < confirmButton.size(); x++) {
			if(x != i) {
				confirmButton.get(x).setVisible(false);
			}
			confirmButton.get(i).setVisible(true);
		}
	}
	public void setButEnable(boolean b) {
		for(int i = 0; i < buttons.size(); i ++) {
			buttons.get(i).setEnabled(b);
		}
		for(int z = 0; z < buttonList.size(); z++) {
			buttonList.get(z).setEnabled(b);		
		}
	}
	
	
	public ArrayList<Song> getSongs()
	{
		return songs;
	}
	
	public int getCredits()
	{
		return credits;
	}
	
	public void changeCredits(int c)
	{
		credits = c;
	}


}