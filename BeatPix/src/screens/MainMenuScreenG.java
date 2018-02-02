package screens;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.CustomText;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;

public class MainMenuScreenG extends FullFunctionScreen {

	/**Design:
	 * 	-Background - based off where StartScreen left its background
	 * 				- scroll down similarly to startscreen
	 * 	-Idle Character - will be at the left side of screen
	 * 
	 */
	private static final long serialVersionUID = -7197187517418245951L;
	
	public Timer time;
	private int screenPhase;
	
	public ImageButton background;
	
	public ArrayList<ImageButton> buttons;
	public static int LEVEL_IDX = 0;
	public static int CHARACTER_IDX = 1;
	public static int UNLOCK_IDX = 2;
	public static int OPTIONS_IDX = 3;
	
	public AnimatedComponent idleCharacter;
	
	public static OptionsPopUp options;
	public boolean optionsOn;
	
	public MainMenuScreenG(int width, int height) {
		super(width, height);
		screenPhase = 0;
	}
	
	public void initAllObjects(List<Visible> viewObjects) {
		//--BACKGROUND
/*P*/	ImageIcon icon = new ImageIcon("resources\\backgrounds\\start.jpg");
/*P D*/	background = new ImageButton(0,0,getWidth(),(int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+100),"resources\\backgrounds\\start.jpg");
		background.setEnabled(true);
		background.setY(-background.getHeight()+getHeight()*2);
		background.setAction(new Action() {
			public void act() {
				if(screenPhase == 0) {
					scrollDownEnd();
				}
			}
		});
		//
		
		//--BUTTONS
/*P*/	icon = new ImageIcon("resources\\ui\\buttons\\buttonwithrivet.png");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
/*P D*/		buttons.add(new ImageButton(getHeight()/6 + getWidth(),100*(i+1) + getHeight(),icon.getIconWidth(),100,"resources\\ui\\buttons\\buttonwithrivet.png"));
			buttons.get(i).setUnhoverAction(new Action() {
				public void act() {
					GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
			buttons.get(i).setHoverAction(new Action() {
				public void act() {
					GUIApplication.mainFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
		}
		//
		
		// Set button actions
		buttons.get(LEVEL_IDX).setAction(new Action() {
			public void act(){
				System.out.println("Select Level Screen Clicked");
				buttons.get(LEVEL_IDX).unhoverAction();
			}
		});
		buttons.get(CHARACTER_IDX).setAction(new Action() {
			public void act(){
				System.out.println("Select Character Screen Clicked");
				buttons.get(CHARACTER_IDX).unhoverAction();
			}
		});
		buttons.get(UNLOCK_IDX).setAction(new Action() {
			public void act(){
				System.out.println("Select Unlocks Screen Clicked");
				buttons.get(UNLOCK_IDX).unhoverAction();
			}
		});
		options = new OptionsPopUp(this, getWidth()/10, getHeight()/10, getWidth(), getHeight());
		buttons.get(OPTIONS_IDX).setAction(new Action() {
			public void act(){
				System.out.println("Select Options Screen Clicked");
				buttons.get(OPTIONS_IDX).unhoverAction();
				viewObjects.add(new ScalablePixelBack(getWidth()/10,getHeight()/10,getWidth()*8/10,getHeight()*8/10));
				viewObjects.add(options);
			}
		});
		//
		
		//--IDLE CHARACTER ANIMATION  /*D*/ Indicates dimensions have to be scaled (*required for animations*)
/*D*/	idleCharacter = new AnimatedComponent(100, 200 + getHeight(), 400, 300);
/*P*/	idleCharacter.addSequence("resources//sprites//sheet.png", 500, 0, 0, 39, 33, 2);
		Thread run = new Thread(idleCharacter);
		run.start();
		//
		
		//add all objects tp viewObjects
		viewObjects.add(background);
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
		viewObjects.add(idleCharacter);
		
		//viewObjects.add(new ScalableButtonBackGroundThingIDunno(50,50,getWidth()*8/10,getHeight()*8/10));
		scrollDown();
	}

//--EVENTS--//
	public void scrollDown() {
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
					//-- SCROLL BACKGROUND UP
					moveBackground();
					//-- SCROLL IDLE CHRACTER UP
					moveIdleCharacter();
					
					//-- SCROLL BUTTONS UP
					moveButtonsY();
					//will begin sliding in buttons from left once the background reaches point where the button is visible
					if(background.getY() > -background.getHeight() && buttons.get(0).getX() > getWidth()*5/10) {
						moveButtonsX();
				}else {
					scrollDownEnd();
				}
			}
		}, 0, 2);
	}
	//--SCROLL DOWN METHODS
	public void moveBackground() {
		if(background.getY() > -background.getHeight()+getHeight()) {
			background.setY(background.getY() - 1);
		}
	}
	public void moveIdleCharacter() {
/*D*/		if(idleCharacter.getY() > 200) {
			idleCharacter.setY(idleCharacter.getY() - 1);
		}
	}
	public void moveButtonsX() {
		if(buttons.get(0).getX() > getWidth()*5/10) {
			for(int i = 0; i < buttons.size(); i++) {
/*D*/			buttons.get(i).setX(buttons.get(i).getX()-1);
			}
		}
	}
	public void moveButtonsY() {
		if(buttons.get(0).getY() > 100) {
			for(int i = 0; i < buttons.size(); i++) {
/*D*/			buttons.get(i).setY(buttons.get(i).getY()-1);
			}
		}
	}
	public void scrollDownEnd() {
		time.cancel();
		background.setY(-background.getHeight()+getHeight());
		background.setEnabled(false);
/*D*/	idleCharacter.setY(200);

		for(int i = 0; i < buttons.size(); i++) {
/*D*/		buttons.get(i).setY(100*(i+1));
/*D*/		buttons.get(i).setX(getWidth()/2 + getWidth()/2*(1/10));
		}
		for(ImageButton b: buttons) {
			b.setEnabled(true);
		}
		screenPhase = 1;
	}
	
	//--IGNORE
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
		for(int i = 0; i < buttons.size(); i++) {
/*D*/		buttons.get(i).setY(i);
/*D*/		buttons.get(i).setX(i);
		}
	}
	
}
