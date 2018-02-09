package shop;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import gui.components.TextLabel;
import gui.components.ScrollablePane;

public class ShopScreenKevin extends FullFunctionScreen {

	//things to do: create a text that visible and not visible
	// yes or no button
	//the letters used as local variable in initAllObjects - k, l, z, i, x, j, a 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1504601622695326879L;
	
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
	public String imageName;
	private CharacterSelectionScreen ab;
	

	public ShopScreenKevin(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{	
		//instantiate the variables
		ab = new CharacterSelectionScreen(getWidth(), getHeight());
		buttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		confirmButton = new ArrayList<Button>();
		indexList = new ArrayList<Integer>();
		images = new ArrayList<ImageButton>();
		imageNames = new String[] {"resources/sprites/redGuy.png", "resources/sprites/greenGuy.png", "resources/sprites/whiteGuy.png"};
		for(int i = 0; i < imageNames.length; i ++) {
			images.add(new ImageButton(290, 180, 200, 300, imageNames[i], null, null));
		}
		//the ten should be number chars that the player should unlock
		numChars = 3;
		
		
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
					for (int i = index; i < buttonList.size(); i++)
					{
						if (buttonList.get(i).getY() != 0)
						{
							buttonList.get(i).move(buttonList.get(i).getX(), (buttonList.get(i).getY()-30), 10);
							charScroll.update();
						}

					}
					charScroll.remove(buttonList.get(a));
					//buttonList.get(a).setVisible(false);
					charScroll.update();
					indexList.remove(index);
					ab.unlock(a);
					
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
	
}