package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.Component;
import gui.components.FullFunctionPane;
import gui.components.Graphic;
import gui.components.ScrollablePane;
import gui.components.TextLabel;
import gui.interfaces.FocusController;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;

public class OptionsPopUp extends ScrollablePane {


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
		this.addObject(new ScalablePixelBack(0,0,960*8/10,540*8/10));
		update();
		this.setBackground(Color.black);
		// TODO Auto-generated constructor stub
	}
	
	
}
