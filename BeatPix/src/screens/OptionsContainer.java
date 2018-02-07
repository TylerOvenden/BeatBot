package screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.components.FullFunctionPane;
import gui.interfaces.Clickable;
import gui.interfaces.Dragable;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.ComponentContainer;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;

public class OptionsContainer{

	ScalablePixelBack background;
	FullFunctionScreen parentScreen;
	int x; int y;
	
	ArrayList<ImageButton> keySelect;
	ImageButton toggleVolume;
	
	//FROM CLICKABLESCREEN
	private ArrayList<Clickable> clickables;
	
	public OptionsContainer(int x , int y, List<Visible> vObjects, FullFunctionScreen screen) {
		this.parentScreen = screen;
		this.x = x;
		this.y = y;
	}
	
	public void addObjects() {
		ScalablePixelBack a = new ScalablePixelBack(x/10,y/10,x*8/10,y*8/10,1.5);
		
		ImageButton temp = new ImageButton(300, 200,400, 100, "resources\\ui\\buttons\\buttonwithrivet.png");
		temp.setAction(new Action() {
			
			@Override
			public void act() {
				parentScreen.remove(a);
				parentScreen.remove(temp);
				System.out.println("Words");
			}
		});
		temp.setEnabled(true);
		
		parentScreen.addObject(a);
		parentScreen.addObject(temp);
	}
	
	//FROM CLICKABLESCREEN


}
