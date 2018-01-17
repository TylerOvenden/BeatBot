package screens;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class CharCustomG extends FullFunctionScreen{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6958166941821425998L;
	public ArrayList<ImageButton> buttons;
	
	public CharCustomG(int width, int height) {
		super(width, height);
		
	}

	private CustomPaneG keyIndicators;
	public void initAllObjects(List<Visible> viewObjects) {
		ImageIcon icon = new ImageIcon("resources\\ui\\buttons\\buttonwithrivet.png");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
			buttons.add(new ImageButton(getWidth()+100,(i*100)+50,icon.getIconWidth(),icon.getIconHeight(),"resources\\ui\\buttons\\buttonwithrivet.png"));
		}
		buttons.get(0).setAction(new Action() {
			public void act(){
				buttons.get(0).unhoverAction();
				Test.test.setScreen(new StartScreenG(getWidth(),getHeight()));
			}
		});
		
		
		
		
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
		
	}
	//change the variable that chooses which skin to the corresponding button
}
