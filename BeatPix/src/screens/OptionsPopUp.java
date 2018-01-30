package screens;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import gui.components.Graphic;
import gui.components.Pane;
import gui.components.TextLabel;
import gui.interfaces.Clickable;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import screens.components.ImageButton;

public class OptionsPopUp extends Pane {

	/**
	 * 
	 */
	private ArrayList<Graphic> words;
	
	//private Graphic background;
	//private Component background;
	private ArrayList<TextLabel> labels;
	private ArrayList<ImageButton> keySelect;
	private ImageButton volumeToggle;
	private ImageButton menuToggle;
	
	private static final long serialVersionUID = -2208166683490986648L;

	public OptionsPopUp(FocusController focusController, int x, int y, int width, int height) {
		super(focusController, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void initObjects(List<Visible> viewObjects) {
		initAllObjects(viewObjects);
		clickables = new ArrayList<Clickable>();
		for(Visible v: viewObjects){
			if(v instanceof Clickable){
				clickables.add((Clickable)v);
			}
		}
	}
	public void initAllObjects(List<Visible> viewObjects){
		//background = new Button(0,0,100,100, "Test", null);
		//background.setBackground(Color.GRAY);
		keySelect = new ArrayList<ImageButton>();
		setBackground(Color.GRAY);
		for(int i = 0; i < 4; i++) {
			keySelect.add(new ImageButton(100*i+50, 50, 45, 45, "resources\\ui\\buttons\\buttonwithrivet.png"));
			keySelect.get(i).setAction(new Action() {
				
				@Override
				public void act() {
					Test.options[0] ++;
					System.out.println(Test.options);
					
				}
			});
			keySelect.get(i).setEnabled(true);
			System.out.println(keySelect.size());
			viewObjects.add(keySelect.get(i));
		}
		
		//viewObjects.add(background);
	}
}
