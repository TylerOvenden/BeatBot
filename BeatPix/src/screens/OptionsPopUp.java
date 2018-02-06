package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import gui.components.FullFunctionPane;
import gui.components.Graphic;
import gui.components.ScrollablePane;
import gui.components.TextLabel;
import gui.interfaces.FocusController;
import screens.components.CustomText;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;

public class OptionsPopUp extends Component {


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
	
	private ScalablePixelBack a;
	
	public OptionsPopUp(int x, int y, int w, int h) {
		super( x, y, w, h);
		
		/*this.addObject(new ScalablePixelBack(0,0,w,h,1));
		this.addObject(new Button(getWidth()/4, getHeight()/8, 100, 100, "", new Action() {
			public void act() {
				System.out.println("What the heck");
			}
		}));*/
		update();
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g) {
		//g.setColor(Color.GRAY);
		//g.fillRect(0, 0, getWidth(), getHeight());
		a = new ScalablePixelBack(0,0,getWidth(),getHeight(),0);
		a.update();
	}
	
}
