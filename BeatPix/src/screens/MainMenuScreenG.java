package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class MainMenuScreenG extends FullFunctionScreen implements FocusController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197187517418245951L;

	public Timer time;
	
	public Graphic background;
	
	public ArrayList<ImageButton> buttons;
	public static int LEVEL_IDX = 0;
	public static int CHARACTER_IDX = 1;
	public static int UNLOCK_IDX = 2;
	public static int OPTIONS_IDX = 3;
	
	public static Pane options;
	public boolean optionsOn;
	
	public MainMenuScreenG(int width, int height) {
		super(width, height);
	}

	public void initAllObjects(List<Visible> viewObjects) {
/**/	ImageIcon icon = new ImageIcon("resources\\backgrounds\\start.jpg");
/**/	background = new Graphic(0,0,getWidth(),(int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+100),"resources\\backgrounds\\start.jpg");
		background.setY(-background.getHeight()+getHeight());
		
/**/	icon = new ImageIcon("resources\\ui\\buttons\\buttonwithrivet.png");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
/**/		buttons.add(new ImageButton(getWidth()+100,(i*100)+50,icon.getIconWidth(),icon.getIconHeight(),"resources\\ui\\buttons\\buttonwithrivet.png"));
		}
		buttons.get(0).setAction(new Action() {
			public void act(){
				buttons.get(0).unhoverAction();
/**/			Test.test.setScreen(new StartScreenG(getWidth(),getHeight()));
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
		
		
		slideInButtons();
		
		viewObjects.add(background);
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
		
	}

	public void slideInButtons() {
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(buttons.get(0).getX() > getWidth()-buttons.get(0).getWidth()-100) {
					for(ImageButton b: buttons) {
						b.setX(b.getX()-5);
					}
				}else {
					slideInButtonsEnd();
				}
			}
		}, 0, 10);
	}
	public void slideInButtonsEnd() {
		time.cancel();
		for(ImageButton b: buttons) {
			b.setEnabled(true);
		}
	}
	
}
