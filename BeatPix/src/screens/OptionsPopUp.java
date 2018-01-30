package screens;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import gui.components.FullFunctionPane;
import gui.components.Graphic;
import gui.components.Pane;
import gui.components.TextLabel;
import gui.interfaces.Clickable;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import screens.components.ImageButton;

public class OptionsPopUp extends FullFunctionPane {

	/**
	 * 
	 */
	private ArrayList<Graphic> words;
	
	private ArrayList<TextLabel> labels;
	private ArrayList<ImageButton> keySelect;
	private ImageButton volumeToggle;
	private ImageButton menuToggle;
	
	private static final long serialVersionUID = -2208166683490986648L;

	public OptionsPopUp(FocusController focusController, int x, int y, int width, int height) {
		super(focusController, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void initAllObjects(List<Visible> viewObjects){
		
		keySelect = new ArrayList<ImageButton>();
		setBackground(Color.GRAY);
		for(int i = 0; i < 4; i++) {
			keySelect.add(new ImageButton(100*i+50, 50, 45, 45, "resources\\ui\\buttons\\buttonwithrivet.png"));
			keySelect.get(i).setAction(new Action() {
	//Needs to create a method that interacts with settings that will be saved in GUIApplications			
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
	}
}
