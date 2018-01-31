package screens;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class LevelSelectG extends FullFunctionScreen{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6265786684466337399L;
	public ArrayList<ImageButton> buttons;
	private Graphic background;
	public LevelSelectG(int width, int height) {
		super(width, height);
		}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		background = updateBackground("resources\\backgrounds\\start.jpg");
		viewObjects.add(background);
	}
	
	private Graphic updateBackground(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; // 0 for either will use original image size/width 
		int x = 0; int y = 0;
		if(background != null) {
			x = background.getX(); y = background.getY();
		}
		w = getWidth();
		//GUIApp scales the height last
/*needs fixing*/h = (int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+100); //makes the width of background always match the screen
		return new Graphic(x,y,w,h,path);
	}
	// change to game screen calling with the appropriate songs
}
