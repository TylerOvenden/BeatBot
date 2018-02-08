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

public class ShopScreen extends FullFunctionScreen
{

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
	
	private static ArrayList<Song> songs;
	private ArrayList<ImageButton> buttons;
	private ArrayList<CustomText> customText;

	private ImageButton yes;
	private ImageButton no;
	private ImageButton clickedButton;
	
	private int credits;
	private int price;
	
	private CustomText text;

	
	//kevin
	private TextLabel textKev;
	private TextLabel unlock;
	
	private ArrayList<Button> buttonList;
	private ArrayList <Button> confirmButton;
	private ArrayList<Button> yesButton;
	private ArrayList<Integer> indexList;
	private String[] imageNames;
	private ArrayList<ImageButton> images;
	
	private CustomRectangle border; //for the text
	private CustomRectangle border2; //for the unlock
	
	private ScrollablePane charScroll;
	private Button noButton;
	private int numChars;
	private int index;

	private Graphic creditBorder;

	
	
	public ShopScreen(int width, int height) 
	{
		super(width, height);

	}
	
	
	
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{

		credits = 3000;
		
		songs = new ArrayList<Song>();
		buttons = new ArrayList<ImageButton>();
		customText = new ArrayList<CustomText>();
		
		songs.add(new Song("resources//DreadnoughtMastermind(xi+nora2r).csv"));

		//graphics
		
		
		
 		background = new Graphic(0,0,getWidth(),getHeight(),"resources//backgrounds//shop_background.png");
 		viewObjects.add(background);
		
 		creditBorder = new Graphic(90,40,260,195,"resources//TransparentButtonA.png");
		viewObjects.add(creditBorder);
		
		songBanner = new CustomText(123,135,200,150,"Songs",true);
		viewObjects.add(songBanner);
		
		
		String s = "Credits:"+credits; // add method "getCredits()" later
		credit = new CustomText(110,58,200,200, s,true);
		viewObjects.add(credit);
		
		
		//scroll bar, contains the songs
		scroll = new ScrollablePane(this, 110,175,220,300);		
		
		//when user clicks yes to buy song
		yes = new ImageButton(360,235,125,50, "resources\\ui\\buttons\\buttonwithrivet.png");
		yesText = new CustomText(390,238,60,60, "yes",false); 	
		
		yes.setAction(new Action() 
		{			
			@Override
			public void act() 
			{
				int x = (credits - 1500);
				if (x >= 0)
				{	
					new Thread()
					{
						CustomText purchasedTextLine1;
						CustomText purchasedTextLine2;

						public void run()
						{
							try
							{
								credits -= 1500;
								credit.setText("Credits: "+ credits);
								credit.update();
								
								setInvis(false);
								
								purchasedTextLine1 = new CustomText(350,90,300,430, "Song Purchased!",true);
								purchasedTextLine2 = new CustomText(342,130,300,420, "Added to Library",true);
								viewObjects.add(purchasedTextLine1);
								viewObjects.add(purchasedTextLine2);
								
								Thread.sleep(1000);
								
								purchasedTextLine1.setVisible(false);
								purchasedTextLine2.setVisible(false);
								
								removeButton();
								
								scroll.update();							
							}
							catch (InterruptedException e)
							{
								
							}
							//add transfer of song later
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
								CustomText warningLine1 = new CustomText(342,90,300,430, "You Do Not Have",true);
								CustomText warningLine2 = new CustomText(342,130,300,420, "Enough Credits",true);
								
								viewObjects.add(warningLine1);
								viewObjects.add(warningLine2);
								Thread.sleep(1000);
								warningLine1.setVisible(false);		
								warningLine2.setVisible(false);	
								setInvis(false);
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
		
		textLine1 = new CustomText(370,175,250,280, "Do you want to",true); 			
		textLine2 = new CustomText(370,200,250,280,"buy this song?",true);
		viewObjects.add(textLine1);
		viewObjects.add(textLine2);
		textLine1.setVisible(false);
		textLine2.setVisible(false);
		
		no = new ImageButton(500,235,125,50, "resources\\ui\\buttons\\buttonwithrivet.png");
		noText = new CustomText(540,238,50,40, "no",false);
		no.setAction(new Action()
		{
			@Override
			public void act() 
			{
				setInvis(false);
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
				
	
		// kevin
		
		//
		buttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		confirmButton = new ArrayList<Button>();
		indexList = new ArrayList<Integer>();
		images = new ArrayList<ImageButton>();
		imageNames = new String[] {"resources/sprites/redGuy.png", "resources/sprites/greenGuy.png", "resources/sprites/whiteGuy.png"};
		for(int i = 0; i < imageNames.length; i ++) {
			images.add(new ImageButton(290, 180, 200, 300, imageNames[i]));
		}
		//the ten should be number chars that the player should unlock
		numChars = 3;
		//create the back button
		createIntList(numChars);
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(MainGUI.test.mainMenu);
			
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
					
					setThings1VisTrue();
					setYesButVisExceptThis(j);
					index = indexList.indexOf(j);
				}
			}));
			charScroll.addObject(buttonList.get(i));
		}
		
		//create arrayList of YesButtons
		for(int k = 0; k < numChars; k++) {
			final int z = k;
			yesButton.add(new Button(300, 250, 50, 50, "yes "+ k, Color.GREEN, new Action() {
				int j = z;
				@Override
				public void act() {
					// i guess to have the images, and set the a boolean to true and a textfield says unlock
					 setThings2VisTrue();
					 setThings1VisFalse();
					 setAllYesButVisFalse();
					 setConfButVisExceptThis(j);
					 
						
				}
			}));
		}
		//create arrayList of confirmButtons
		for(int l = 0; l < numChars; l ++) {
			final int y = l;
			confirmButton.add(new Button(370, 400, 50, 50, "Okay" + l, Color.blue, new Action() {
				int a = y;
				@Override
				public void act() {
					setThings2VisFalse();
					setAllConfButVisFalse();
					buttonList.get(a).setVisible(false);
					indexList.remove(index);
					for (int i = index; i < buttonList.size(); i++)
					{
						if (buttonList.get(i).getY() != 0)
						{
							buttonList.get(i).move(buttonList.get(i).getX(), (buttonList.get(i).getY()-30), 10);
							charScroll.update();
						}

					}
					
				}
			}));
		}
		
		 //create all the things 
		 border = new CustomRectangle(280, 180, 220, 120, Color.BLACK, 3);
		 textKev = new TextLabel(300, 200, 200, 100, "Do you wish to unlock this?");
		 border2 = new CustomRectangle(280, 80, 220, 420, Color.BLACK, 3);
		 unlock = new TextLabel(300, 100, 200, 400, "You have unlocked this. Enjoy");
		 
		 noButton = new Button(400, 250, 50, 50, "no", Color.RED, new Action() {
			
			@Override
			public void act() {
				 setThings1VisFalse();
				 setAllYesButVisFalse();
			}
		});
		 
		 
		 //visible all the things
		 setThings1VisFalse();
		 setAllYesButVisFalse();
		 setThings2VisFalse();
		 setAllConfButVisFalse();
		 
		 
		
		//add the objects
		viewObjects.add(backButton);
		viewObjects.add(border);
		viewObjects.add(textKev);
		charScroll.update();
		viewObjects.add(charScroll);
		viewObjects.add(noButton);
		for(int a = 0; a < numChars; a++) {
			viewObjects.add(yesButton.get(a));
			viewObjects.add(confirmButton.get(a));
			viewObjects.add(images.get(a));
		}
		viewObjects.add(border2);
		viewObjects.add(unlock);
	}	
	//daniel methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void removeButton()
	{
		int index = buttons.indexOf(clickedButton);

		scroll.remove(clickedButton);				
		buttons.remove(clickedButton);
		scroll.remove(customText.get(index));
		customText.remove(index);
		scroll.update();
		
		for (int i = index; i < buttons.size(); i++)
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
		
	}
	public void addButtons()
	{
		String[] texts = {"Adrenaline","Neptune","Blow Out","Hime Hime","Hitorigoto"};
		
		for(int i = 0; i < texts.length; i++)
		{ 

				ImageButton b = new ImageButton(10,(i*52)+5,200,70,"resources\\ui\\buttons\\buttonwithrivet.png");
				b.setAction(new Action() 
				{
					
					@Override
					public void act() 
					{
						clickedButton = b;				
						setInvis(true);				
					}
				});
				
				b.setEnabled(true);
				buttons.add(b);
				customText.add(new CustomText(0 + getWidth()*55/960, (i*52) + getHeight()*17/540, 200 - 200*100/399, 120, texts[i],false));
			
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
			scroll.addObject(customText.get(i));
		}
	}




	public void setInvis(boolean b)
	{			
		if (b)
		{
			noText.setVisible(true);
			yesText.setVisible(true);
			textLine1.setVisible(true);
			textLine2.setVisible(true);
			yes.setVisible(true);				
			no.setVisible(true);
		}
		else
		{
			noText.setVisible(false);
			yesText.setVisible(false);
			textLine1.setVisible(false);
			textLine2.setVisible(false);
			yes.setVisible(false);				
			no.setVisible(false);
		}
	}
	
	
	
	
	
	
	// kevin methods
	public void createIntList(int a) {
		for(int i = 0; i < a; i++) {
			indexList.add(i);
		}
	}
	//helper methods

	//things that are not buttons doesnt not consist of the unlock and confirm button and soon images, visibility = false
	public void setThings1VisFalse() {
		 border.setVisible(false);
		 textKev.setVisible(false);
		 noButton.setVisible(false);
	}
	//same things as Things1false but, visibility = true
	public void setThings1VisTrue() {
		 border.setVisible(true);
		 textKev.setVisible(true);
		 noButton.setVisible(true);
	}
	
	//things that things1 doesnt consist of, visibility = false
	public void setThings2VisFalse() {
		 border2.setVisible(false);
		 unlock.setVisible(false);
		 //confirmButton.setVisible(false);
	}
	//things that things1 doesnt consist of, visibility = false
	public void setThings2VisTrue() {
		 border2.setVisible(true);
		 unlock.setVisible(true);
		 //confirmButton.setVisible(true);
	}
	
	 //turn all yesButtons, visibility = false;
	public void setAllYesButVisFalse() {
		for(int i = 0; i < yesButton.size(); i ++) {
			yesButton.get(i).setVisible(false);
		}
	}
	//turn all  yes button visibility that are false except for the  chosen one
	public void setYesButVisExceptThis(int i) {
		for(int x = 0; x < yesButton.size(); x++) {
			if(x != i) {
				yesButton.get(x).setVisible(false);
			}
			yesButton.get(i).setVisible(true);
		}
	}
	
	 //turn all confirmButtons with the images, visibility = false;
	public void setAllConfButVisFalse() {
		for(int i = 0; i < confirmButton.size(); i ++) {
			confirmButton.get(i).setVisible(false);
			images.get(i).setVisible(false);
		}
	}
	//turn all  confirm button and respected image visibility that are false except for de chosen one
	public void setConfButVisExceptThis(int i) {
		for(int x = 0; x < confirmButton.size(); x++) {
			if(x != i) {
				confirmButton.get(x).setVisible(false);
				images.get(x).setVisible(false);
			}
			confirmButton.get(i).setVisible(true);
			images.get(i).setVisible(true);
		}
	}
	public ArrayList<Song> getSongs()
	{
		return songs;
	}
}


