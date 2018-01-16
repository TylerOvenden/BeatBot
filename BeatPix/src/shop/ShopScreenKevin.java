package shop;

import java.awt.Color;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import gui.components.TextLabel;
import gui.components.ScrollablePane;

public class ShopScreenKevin extends FullFunctionScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1504601622695326879L;

	public ShopScreenKevin(int width, int height) 
	{
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) 
	{	
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				// go back to main screen
				
			}
		});
		ScrollablePane charScroll = new gui.components.ScrollablePane(this, 100, 100, 100, 100);
		charScroll.setBorderWidth(3);
		for(int i=0; i < 10; i++){
			
			charScroll.addObject(new TextLabel(5,30*i,100,25,"Label "+(i+1)));
		}
		charScroll.update();
		
		viewObjects.add(backButton);
		viewObjects.add(charScroll);
	}

}
