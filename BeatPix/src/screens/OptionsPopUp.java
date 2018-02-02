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
	
	public OptionsPopUp(FocusController parentScreen, int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
		// TODO Auto-generated constructor stub
	}
	
	public void update(Graphics2D g) {
		/*g.setColor(Color.BLACK);
		g.drawRect(0,0,getWidth()*8/10,getHeight()*8/10);
		g.fillRect(0,0,getWidth()*8/10,getHeight()*8/10);
		g.setColor(Color.GRAY);
		g.drawRect(10,10,getWidth()*7/10,getHeight()*7/10);
		g.fillRect(10,10,getWidth()*7/10,getHeight()*7/10);*/
		ScalablePixelBack a = new ScalablePixelBack(0,0,getWidth()*8/10,getHeight()*8/10);
		a.update();
	}
	
	
}
