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
	
	private int counter;
	private ArrayList<TextLabel> text;
	private ArrayList<Button> buttonList;
	CustomRectangle border;
	ScrollablePane charScroll;

	public ShopScreenKevin(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{	
		text = new ArrayList<TextLabel>();
		buttonList = new ArrayList<Button>();
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				// go back to main screen
				
			}
		});
		charScroll = new ScrollablePane(this, 650, 100, 250, 400);
		charScroll.setBorderWidth(3);
		//change the 10, to the number of costumes, 10 is just a place holder
		for(int i=0; i < 2; i++){ 

			charScroll.addObject(new Button(5,30*i,100,25,"Button "+(i+1), new Action() {
				//int j = i;
				@Override
				public void act() {
					border.setVisible(true);
					text.get(0).setVisible(true);
				}
			}));
		}
		
		 border = new CustomRectangle(280, 180, 220, 120, Color.BLACK, 3);
		 text.add(new TextLabel(300, 200, 200, 100, "Do you wish to unlock this0?"));
		 text.add(new TextLabel(300, 200, 200, 100, "Do you wish to unlock this1?"));
		 border.setVisible(false);
		 text.get(0).setVisible(false);
		 text.get(1).setVisible(false);
		
		
		viewObjects.add(backButton);
		viewObjects.add(border);
		viewObjects.add(text.get(0));
		viewObjects.add(text.get(1));
		charScroll.update();
		viewObjects.add(charScroll);
		
	}
}
