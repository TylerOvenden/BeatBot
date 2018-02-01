package screens;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class LevelSelectG extends FullFunctionScreen{

	private static final long serialVersionUID = 6265786684466337399L;
	public ArrayList<ImageButton> buttons;
	private Graphic background;
	public LevelSelectG(int width, int height) {
		super(width, height);
		}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		background = updateBackground("resources\\backgrounds\\start.jpg");
		//viewObjects.add(background);
		ImageIcon icon = new ImageIcon("resources\\tester.jpg");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
/*P D*/		buttons.add(new ImageButton( 150*(-i-1)+getWidth()-200,100*(i+1) + getHeight()-600,icon.getIconWidth(),100,"resources\\tester.jpg"));
		}
		buttons.get(0).setAction(new Action() {
			public void act(){
				buttons.get(0).unhoverAction();
			}
		});
		buttons.get(3).setAction(new Action() {
			//Options pop up
			public void act() {
				if(true) {
				viewObjects.add(new OptionsPopUp(null, 250, 250, getWidth()/2, getHeight()/2));
				for(ImageButton b: buttons)
					b.setEnabled(false);
				}
			}
		});
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
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
