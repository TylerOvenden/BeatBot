package shop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;

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
		viewObjects.add(backButton);
	}

}
