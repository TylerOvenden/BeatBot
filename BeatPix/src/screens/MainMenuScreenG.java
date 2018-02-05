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
	 * 				- scroll down similarly to in StartScreen
	 * 	-Idle Character - will be at the left side of screen
	 * 					- will slide in along with the background
	 * 	-Buttons - will be on the right side of the screen
	 * 			 - takes up most of the screen space
	 * 			 - will slide in along with the background
	 * 			 - will slide in from the right
	 */
	/*D*/ // <- indicate that there are parameters where dimensions are pixel specific
	/*P*/ // <- indicate that there are folder path specific parameters
	
	private static final long serialVersionUID = -7197187517418245951L;
	
	public Timer time;
	private int screenPhase;
	
	public ImageButton background;
	
	public AnimatedComponent idleCharacter;
	
	public ArrayList<ImageButton> buttons;
	public static int LEVEL_IDX = 0;
	public static int CHARACTER_IDX = 1;
	public static int UNLOCK_IDX = 2;
	public static int OPTIONS_IDX = 3;
	
	public static OptionsPopUp options;
	public boolean optionsOn;
	
	public MainMenuScreenG(int width, int height) {
		super(width, height);
		System.out.println(Test.test.x+"s START MAIN");
		screenPhase = 0;
	}
	
	public void initAllObjects(List<Visible> viewObjects) {
		//--BACKGROUND
		createBackground();
		//--BUTTONS
		createButtons();
		//--IDLE CHARACTER ANIMATION 
		createIdleCharacter();
		
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
		//NEED TO TEST OPTIONS AND FINISH
		options = new OptionsPopUp(getWidth()/10, getHeight()/10,getWidth()*8/10, getHeight()*8/10);
		buttons.get(OPTIONS_IDX).setAction(new Action() {
			public void act(){
				System.out.println("Select Options Screen Clicked");
				buttons.get(OPTIONS_IDX).unhoverAction();
				viewObjects.add(new ScalablePixelBack(getWidth()/10,getHeight()/10,getWidth()*8/10,getHeight()*8/10,5));
				//viewObjects.add(options);
			}
		});
		//
		
		//viewObjects adding
		viewObjects.add(background);
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
		viewObjects.add(idleCharacter);
		
		scrollDown();
	}
	
	/** --COMPLETE--
	 * Creates a background that scales to the screens width resolution
	 * 
	 * The y of the background is set at 2 times the screen's pixel above
	 * the bottom of the background image's height (where StartScreen leaves it off)
	 * 
	 * Since MouseListener will break other buttons, background is created as an
	 * ImageButton and goes to the next phase when clicked (unlike StartScreen which
	 * uses a MouseListener)
	 */
	public void createBackground() {
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
	}
	
	/** --INCOMPLETE-- need to fix dimensions to scale (specifically the y position and dimensions of the image)
	 * Creates an animation of the idle character with SPECIFIC dimensions
	 * that are not scalable
	 * 
	 * The y is set 200 pixels plus the size of the screen in order for it
	 * to be off-screen and move up along with the background
	 * 
	 * _________________
	 * |                |
	 * |                |<-- Visible Screen	
	 * |                |
	 * |________________|
	 * |                |
	 * |                |				
	 * |  X             |<-- Position of idle character
	 * |________________|
	 * 
	 * The number before "+ getHeight()" for the y direction
	 * will eventually be the last position of the component
	 * on the screen when it stops scrolling
	 * 
	 * The x dimension is whatever since the position won't
	 * be modified in any of the events
	 * */
	public void createIdleCharacter() {
/*D*/	idleCharacter = new AnimatedComponent(getWidth()/10, getHeight()*200/540 + getHeight(), getWidth()*400/960, getHeight()*300/540);
/*P*/	idleCharacter.addSequence("resources//sprites//sheet.png", 500, 0, 0, 39, 33, 2);
		Thread run = new Thread(idleCharacter);
		run.start();
	}
	
	/** --INCOMPLETE-- need to fix dimensions to scale EVERYWHERE
	 * Creates a series of buttons on the screen that is not scaled whatsover
	 * using the original image dimensions
	 * 
	 * For the y parameter each button is 100 pixel below the previous button
	 * 
	 * They share all the same x parameter, but is more complicated when
	 * trying to animate it sliding in from the right
	 */
	public void createButtons() {
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
/*P D*/		buttons.add(new ImageButton(getHeight()/6 + getWidth(),getHeight()*100/540*(i+1) + getHeight(),getWidth()*399/960,getHeight()*100/540,"resources\\ui\\buttons\\buttonwithrivet.png"));
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
	}
	
//--EVENTS--//
	/**
	 * Returns true if the final status is incomplete
	 * Returns false when its done
	 * 
	 * moveBackground()
	 * 	Will move background until it is shown as the bottom
	 * 	of the background image matches with the bottom of the screen
	 * 
	 * moveIdleCharacter()
	 * 	Moves the idleCharacter along with the background until it
	 * 	reaches the designated final y coordinate(which is set in the
	 * 	y parameter of when idleCharacter is made as an AnimatedComponent)
	 * 
	 * moveButtonsY()
	 * 	Similar to that of moveidleCharacter, but stops when the first button
	 * 	reaches its final position, which should move the rest at the same rate
	 * 
	 * moveButtonsX()
	 * 	The more complicated movement as it doesn't move relative to any other
	 * 	component on the screen(unlike the components moving along with background
	 * 	through the y position)
	 * 	
	 */
	public void scrollDown() {
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
					if(!isBackgroundFinal()
							|| !isIdleFinal()
								|| !isButtonXFinal()
									|| !isButtonYFinal()) {
						moveBackground();
						moveIdleCharacter();
						moveButtonsX(); 
						moveButtonsY();
					}else {
						scrollDownEnd();
					}
				/*
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
				}*/
			}
		}, 0, 2);
	}
	
	//--SCROLL DOWN METHODS
	public void moveBackground() {
		if(!isBackgroundFinal()) {
			background.setY(background.getY() - 1);
		}
	}
	public boolean isBackgroundFinal() {
		return !(background.getY() > -background.getHeight()+getHeight());
	}
	
	public void moveIdleCharacter() {
		if(!isIdleFinal()) {
			idleCharacter.setY(idleCharacter.getY() - 1);
		}
	}
	public boolean isIdleFinal() {
		return !(idleCharacter.getY() > getHeight()*200/540);
	}
	
	public void moveButtonsX() {
		if(!isButtonXFinal()) {
			for(int i = 0; i < buttons.size(); i++) {
				buttons.get(i).setX(buttons.get(i).getX()-1);
			}
		}
	}
	public boolean isButtonXFinal() {
		return !(buttons.get(0).getX() > getWidth()*480/960);
	}
	
	public void moveButtonsY() {
		if(!isButtonYFinal()) {
			for(int i = 0; i < buttons.size(); i++) {
				buttons.get(i).setY(buttons.get(i).getY()-1);
			}
		}
	}
	public boolean isButtonYFinal() {
		return !(buttons.get(0).getY() > getHeight()*100/540);
	}
	
	public void scrollDownEnd() {
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
