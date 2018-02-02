package screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import gui.components.*;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.CustomText;
import screens.components.ImageButton;

public class MainMenuScreenG extends FullFunctionScreen implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197187517418245951L;
	
	public Timer time;
	private int screenPhase;
	
	public Graphic background;
	
	public ArrayList<ImageButton> buttons;
	public static int LEVEL_IDX = 0;
	public static int CHARACTER_IDX = 1;
	public static int UNLOCK_IDX = 2;
	public static int OPTIONS_IDX = 3;
	
	public AnimatedComponent idleCharacter;
	
	public static FullFunctionPane options;
	public boolean optionsOn;
	
	public MainMenuScreenG(int width, int height) {
		super(width, height);
		screenPhase = 0;
	}

	public void mouseClicked(MouseEvent e) {
		if(screenPhase == 0) {
			scrollDownEnd();
		}
	}
	
	public void initAllObjects(List<Visible> viewObjects) {
		//--BACKGROUND
/*P*/	ImageIcon icon = new ImageIcon("resources\\backgrounds\\start.jpg");
/*P D*/	background = new Graphic(0,0,getWidth(),(int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+100),"resources\\backgrounds\\start.jpg");
		background.setY(-background.getHeight()+getHeight()*2);
		
		//--BUTTONS
/*P*/	icon = new ImageIcon("resources\\ui\\buttons\\buttonwithrivet.png");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<4; i++) {
/*P D*/		buttons.add(new ImageButton(getHeight()/6 + getWidth(),100*(i+1) + getHeight(),icon.getIconWidth(),100,"resources\\ui\\buttons\\buttonwithrivet.png"));
		}
		
		buttons.get(0).setAction(new Action() {
			public void act(){
				System.out.println("Hello");
				buttons.get(0).unhoverAction();
			}
		});
		buttons.get(OPTIONS_IDX).setAction(new Action() {
			//Options pop up
			public void act() {
				System.out.println("else");
				buttons.get(OPTIONS_IDX).unhoverAction();
				viewObjects.add(new OptionsPopUp(null, 250, 250, getWidth()/2, getHeight()/2));
				for(ImageButton b: buttons)
					b.setEnabled(false);
				}
		});
		
		//--IDLE CHARACTER ANIMATION  /*D*/ Indicates dimensions have to be scaled (*required for animations*)
/*D*/	idleCharacter = new AnimatedComponent(100, 200 + getHeight(), 400, 300);
/*P*/	idleCharacter.addSequence("resources//sprites//sheet.png", 500, 0, 0, 39, 33, 2);
		Thread run = new Thread(idleCharacter);
		run.start();
		
		scrollDown();
		
		viewObjects.add(background);
		for(ImageButton b: buttons) {
			viewObjects.add(b);
		}
		viewObjects.add(idleCharacter);
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
		}, 0, 1);
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
/*D*/	idleCharacter.setY(200);

		for(int i = 0; i < buttons.size(); i++) {
/*D*/		buttons.get(i).setY(100*(i+1));
/*D*/		buttons.get(i).setX(getWidth()/2 + getWidth()/2*(1/10));
		}
		for(ImageButton b: buttons) {
			b.setEnabled(true);
			System.out.println("Works");
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
