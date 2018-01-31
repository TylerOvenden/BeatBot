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
	// 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1504601622695326879L;
	
	private TextLabel text;
	private ArrayList<Button> buttonList;
	private CustomRectangle border;
	private ScrollablePane charScroll;
	private Button noButton;
	private ArrayList<Button> yesButton;
	private int numChars;
	private TextLabel unlock;
	

	public ShopScreenKevin(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{	
		buttonList = new ArrayList<Button>();
		yesButton = new ArrayList<Button>();
		//the ten should be number chars that the player should unlock
		numChars = 10;
		//create the back button
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				// go back to main screen
				
			}
		});
		
		//create arrayList of YesButtons
		for(int k = 0; k < numChars; k++) {
			yesButton.add(new Button(300, 250, 50, 50, "yes "+ k, Color.GREEN, new Action() {

				@Override
				public void act() {
					// i guess to have the images, and set the a boolean to true and says unlock
						
				}
			}));
		}
		
		//create the panel, and the buttons in it
		charScroll = new ScrollablePane(this, 650, 100, 250, 400);
		charScroll.setBorderWidth(3);
		for(int i=0; i < numChars; i++){ 
			//got the index number
			final int x = i;
			charScroll.addObject(new Button(5,30*i,100,25,"Button "+i, new Action() {
				int j = x;
				@Override
				public void act() {
					
					setThingsVisTrue();
					setButVisExceptThis(j);
				}
			}));
		}
		
		 //create all the things 
		 border = new CustomRectangle(280, 180, 220, 120, Color.BLACK, 3);
		 text = new TextLabel(300, 200, 200, 100, "Do you wish to unlock this?");
		 //unlock = new TextLabel(300, 100, 200, 400, "Congratz");
		 
		 noButton = new Button(400, 250, 50, 50, "no", Color.RED, new Action() {
			
			@Override
			public void act() {
				 setThingsVisFalse();
				 setAllButVisFalse();
			}
		});
		 
		 //visible all the things
		 setThingsVisFalse();
		 setAllButVisFalse();
		 
		
		//add the objects
		viewObjects.add(backButton);
		viewObjects.add(border);
		viewObjects.add(text);
		charScroll.update();
		viewObjects.add(charScroll);
		viewObjects.add(noButton);
		for(int a = 0; a < numChars; a++) {
			viewObjects.add(yesButton.get(a));
		}
		
	}
	//helper methods

	//things that are not buttons, visibility = false
	public void setThingsVisFalse() {
		 border.setVisible(false);
		 text.setVisible(false);
		 noButton.setVisible(false);
	}
	//things that are not buttons, visibility = true
	public void setThingsVisTrue() {
		 border.setVisible(true);
		 text.setVisible(true);
		 noButton.setVisible(true);
	}
	
	 //turn all yesButtons, visibility = false;
	public void setAllButVisFalse() {
		for(int i = 0; i < yesButton.size(); i ++) {
			yesButton.get(i).setVisible(false);
		}
	}
	//turn all button visibility that are false except for the the chosen one
	public void setButVisExceptThis(int i) {
		for(int x = 0; x < yesButton.size(); x++) {
			if(x != i) {
				yesButton.get(x).setVisible(false);
			}
			yesButton.get(i).setVisible(true);
		}
	}
	
}
