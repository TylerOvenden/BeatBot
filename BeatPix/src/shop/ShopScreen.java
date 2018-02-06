package shop;

import java.awt.Color;
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

import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.Song;
import screens.components.CustomText;
public class ShopScreen extends FullFunctionScreen
{
	private CustomRectangle songArea;
	
	private Graphic background;	
	
	private CustomText songBanner;
	private CustomText banner;
	private CustomText credit;
	private CustomText purchased;
	
	private ArrayList<Song> songs;
	private ArrayList<Button> buttons;
	

	private Button yes;
	private Button no;
	private Button clickedButton;
	
	private int credits;
	private int price;
	
	private TextArea text;

	
	//kevin
	private TextLabel textKev;
	private TextLabel unlock;
	
	private ArrayList<Button> buttonList;
	private ArrayList <Button> confirmButton;
	private ArrayList<Button> yesButton;
	private ArrayList<Integer> indexList;
	
	private CustomRectangle border; //for the text
	private CustomRectangle border2; //for the unlock
	
	private ScrollablePane charScroll;
	private Button noButton;
	private int numChars;
	private int index;
	
	public ShopScreen(int width, int height) 
	{
		super(width, height);
	}
	
	
	
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{

		credits = 1500;
		
		buttons = new ArrayList<Button>();

		
		//graphics
		
 		background = new Graphic(40,0,getWidth(),getHeight(),"resources//shop_bg.png");
		viewObjects.add(background);
		
		songBanner = new CustomText(123,135,200,150,"Songs");
		viewObjects.add(songBanner);
		
		
		String s = "Credits:"+credits; // add method "getCredits()" later
		credit = new CustomText(120,60,200,200, s);
		viewObjects.add(credit);
		
		//area where "SONGS" will be displayed
		banner = new CustomText(170,100,200,200, "Songs");
		
		//scroll bar, contains the songs
		ScrollablePane scroll = new ScrollablePane(this, 110,175,220,300);		
	//	scroll.setBackground(new Color(255,255,255,12));
	//	scroll.update();
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
						private CustomText purchasedTextLine1;
						private CustomText purchasedTextLine2;

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
								purchasedTextLine1 = new CustomText(350,90,300,430, "Song Purchased!");
								purchasedTextLine2 = new CustomText(342,130,300,420, "Added to Library");
								viewObjects.add(purchasedTextLine1);
								viewObjects.add(purchasedTextLine2);
								Thread.sleep(1000);
								purchasedTextLine1.setVisible(false);
								purchasedTextLine2.setVisible(false);
								scroll.remove(clickedButton);
								int index = buttons.indexOf(clickedButton);
								buttons.remove(clickedButton);
								for (int i = index; i < buttons.size(); i++)
								{
									if (buttons.get(i).getY() != 0)
									{
										buttons.get(i).move(buttons.get(i).getX(), (buttons.get(i).getY()-50), 10);
										scroll.update();
									}

								}
								
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
								CustomText warningLine1 = new CustomText(342,90,300,430, "You Do Not Have");
								CustomText warningLine2 = new CustomText(342,130,300,420, "Enough Credits");
								
								viewObjects.add(warningLine1);
								viewObjects.add(warningLine2);
								Thread.sleep(1000);
								warningLine1.setVisible(false);		
								warningLine2.setVisible(false);	
								yes.setVisible(false);				
								text.setVisible(false);
								no.setVisible(false);
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
			try 
			{
			    File pathToFile = new File("resources//button.png");
			    Image image = ImageIO.read(pathToFile);
		//	    ImageTextButton b = new ImageTextButton("Song "+i+" | Credits: 1500",image,15,(0+(i*70)),180,50,null);
			    Button b = new Button(0,(i*50),200,70,"Song "+i+" | Credits: 1500",null);
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
			} catch (IOException ex)
			{
			    ex.printStackTrace();
			}
			
		}
		
		for (int i = 0; i < buttons.size(); i++)
		{
			scroll.addObject(buttons.get(i));
		}
		
		
		
		
		scroll.update();
		viewObjects.add(scroll);
				
		
		// kevin
		
		
		buttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		confirmButton = new ArrayList<Button>();
		indexList = new ArrayList<Integer>();
		//the ten should be number chars that the player should unlock
		numChars = 4;
		//create the back button
		createIntList(numChars);
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				// go back to main screen
				
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
		}
		viewObjects.add(border2);
		viewObjects.add(unlock);
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
	
	 //turn all confirmButtons, visibility = false;
	public void setAllConfButVisFalse() {
		for(int i = 0; i < confirmButton.size(); i ++) {
			confirmButton.get(i).setVisible(false);
		}
	}
	//turn all  yes button visibility that are false except for de chosen one
	public void setConfButVisExceptThis(int i) {
		for(int x = 0; x < confirmButton.size(); x++) {
			if(x != i) {
				confirmButton.get(x).setVisible(false);
			}
			confirmButton.get(i).setVisible(true);
		}
	}
}


/*KEVIN'S CODEbuttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		confirmButton = new ArrayList<Button>();
		indexList = new ArrayList<Integer>();
		//the ten should be number chars that the player should unlock
		numChars = 4;
		//create the back button
		createIntList(numChars);
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				// go back to main screen
				
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
		}
		viewObjects.add(border2);
		viewObjects.add(unlock);
		
		
	}
	//create the index array
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
	
	 //turn all confirmButtons, visibility = false;
	public void setAllConfButVisFalse() {
		for(int i = 0; i < confirmButton.size(); i ++) {
			confirmButton.get(i).setVisible(false);
		}
	}
	//turn all  yes button visibility that are false except for de chosen one
	public void setConfButVisExceptThis(int i) {
		for(int x = 0; x < confirmButton.size(); x++) {
			if(x != i) {
				confirmButton.get(x).setVisible(false);
			}
			confirmButton.get(i).setVisible(true);
		}
	}
*/