package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.Component;
import gui.components.FullFunctionPane;
import gui.components.Graphic;
import gui.components.TextLabel;
import gui.interfaces.FocusController;
import screens.components.ImageButton;

public class OptionsPopUp extends FullFunctionPane {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6985381676035574625L;

	/**
	 * 
	 */
	private ArrayList<Graphic> words;
	
	private ArrayList<TextLabel> labels;
	private ArrayList<ImageButton> keySelect;
	private ImageButton volumeToggle;
	private ImageButton back;
	
	public OptionsPopUp(FocusController parentScreen, int x, int y, int w, int h) {
		super(parentScreen, x, y, w, h);
		update();
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.drawRect(0,0,100,100);
		
	}
	
	
}
