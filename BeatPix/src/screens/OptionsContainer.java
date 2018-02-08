package screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.xml.soap.Text;

import gui.GUIApplication;
import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import gui.userInterfaces.Screen;
import screens.components.CustomText;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;
import screens.interfaces.Options;

public class OptionsContainer{

	Options parentScreen;
	int x; int y;
	
	ScalablePixelBack background;
	Component blackBack;
	ArrayList<CustomText> labelText;
	
	ImageButton back; 
	CustomText backText;
	
	ArrayList<ImageButton> keySelect;
	ArrayList<Button> hiddenKeyButtons;
	ArrayList<ScalablePixelBack> keyBackground;
	
	
	ImageButton toggleVolume;
	
	ScalablePixelBack selectingKeyScreen;
	ArrayList<CustomText> selectingKeyScreenText;
	
	/**
	 * selectingKeyPhases:
	 * -1 - not awaiting a key input
	 * 0 - awaiting keyinput
	 * 1 - keypressed
	 * 2 - key is not valid
	 */
	int selectingKeyPhase;
	int columnButtonSelected;
	
	public OptionsContainer(int x , int y, Options currentScreen) {
		this.parentScreen = currentScreen;
		this.x = x;
		this.y = y;
		
		selectingKeyPhase = -1;
		createComponents();
	}
	
	/**
	 * The method that is called whenever a button or
	 * key is clicked to access the options
	 * 
	 * Works by adding all the components 
	 * to the currentScreen of the GUIApp
	 */
	public void addObjects() {
		parentScreen.addObject(blackBack);
		parentScreen.addObject(background);
		
		parentScreen.addObject(back);
		parentScreen.addObject(backText);
		
		for(int i = 0; i<4; i++) {
			parentScreen.addObject(hiddenKeyButtons.get(i));
			parentScreen.addObject(keyBackground.get(i));
			parentScreen.addObject(keySelect.get(i));
		}
		
		parentScreen.addObject(toggleVolume);
	}
	public void removeObjects() {
		parentScreen.remove(blackBack);
		parentScreen.remove(background);
		parentScreen.remove(back);
		parentScreen.remove(backText);
		for(int i = 0; i<4; i++) {
			parentScreen.remove(hiddenKeyButtons.get(i));
			parentScreen.remove(keyBackground.get(i));
			parentScreen.remove(keySelect.get(i));
		}
		parentScreen.remove(toggleVolume);
		
		parentScreen.toggleButtons(true);
		GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	/**
	 * Short helper method to help
	 * me organize all component creation
	 */
	public void createComponents() {
		blackBack = new Component(0, 0, x, y) {
			public void update(Graphics2D g) {
				g.setColor(Color.BLACK);
				g.fillRect(0,0, x, y);
			}
		};
		blackBack.update();
		blackBack.setAlpha(0.7f);
		
		background = new ScalablePixelBack(x/10,y/10,x*8/10,y*8/10,1.5);
		createBackButton();
		
		createKeySelects();
		
		updateVolumeToggle();
	}
	
	 /** --BACK BUTTON--
	  * Designed the same way as the MainMenuScreen buttons
	  * where there is an ImageButton with CustomText on top of it
	  * 
	  * Will be the counterpart to the addObject() since all
	  * components will have to be removed from the screen
	  */
	public void createBackButton() {
		back = new ImageButton( x*600/960, y*180/540, x*200/960, y*50/540, "resources\\ui\\buttons\\buttongeneral.png");
		backText = new CustomText(x*620/960, y*200/540, x*150/960, y*40/540,"Back",true,true);
		back.setAction(new Action() {
			public void act() {
				removeObjects();
			}
		});
		back.setEnabled(true);
	}
	
	/** --KEY SELECT BUTTONS--
	 * Since I'm Lazy:
	 * Will be made of three things:
	 * 	-ScalablePixelBack - just for aesthetics to look like a button
	 *  -ImageButton - not an actual button, but just to show the letters
	 *  -Button - this will be the real button hidden below ScalablePixelBack
	 *  			because it's too difficult to make ScalablePixelBack a button
	 *  
	 *  Button won't be aligned 
	 */
	public void createKeySelects() {
		keyBackground = new ArrayList<ScalablePixelBack>();
		keySelect = new ArrayList<ImageButton>();
		hiddenKeyButtons = new ArrayList<Button>();
		for(int i = 0; i < 4; i ++) {
			keyBackground.add(new ScalablePixelBack(x*i*110/960 + x*180/960,y*150/540,x*100/960,x*100/960,1.3));
			hiddenKeyButtons.add(new Button(x*i*110/960 + x*180/960,y*150/540,x*100/960,x*100/960,"",null));
			//System.out.println(Test.test.keys[i]);
/**/			keySelect.add(new ImageButton(x*i*110/960 + x*200/960,y*170/540,x*80/960,x*40/960, "resources\\text\\" + "A" + ".png"));
		}
		setKeySelectActions();
	}
	

	public void setKeySelectActions() {
		hiddenKeyButtons.get(i).setAction(new Action() {
			int myX = x2;
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = myX;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				
				
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(i).setAction(new Action() {
			int myX = x2;
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = myX;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				
				
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(i).setAction(new Action() {
			int myX = x2;
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = myX;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				
				
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(3).setAction(new Action() {
			int myX = x2;
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = myX;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				
				
				toggleButtons(false);
			}
		});

		
	}
	
	public void readKey(KeyEvent e) {
		if(selectingKeyPhase == 0) {
			boolean validKey = true;
			for(String s: Test.test.keys) {
				if(e.getKeyCode() == (int) s.charAt(0)) {
					validKey = false; break;
				}
			}
			if(validKey) {
				//selectingKeyPhase = 1;
				Test.test.keys[columnButtonSelected] = Integer.toString(e.getKeyCode());
				//System.out.println(Test.test.keys[columnButtonSelected]);
				selectingKeyPhase = -1;
				//System.out.println("Valid");
				for(String s: Test.test.keys) {
					System.out.println(s);
				}
				System.out.println("Key set to " + Integer.toString(e.getKeyCode()));
				parentScreen.remove(selectingKeyScreen);
				toggleButtons(true);
			}else {
				selectingKeyPhase = 0;
				System.out.println("Reselect key");
			}
		}
	}
	/** --KEY SELECTION SCREEN--
	 * Will always be recreated as there will be different
	 * text depending on what the user selected for the key
	 */
	public void createSelectingKeyPopUp(String s) {
		selectingKeyScreen = new ScalablePixelBack(x*330/960, y*100/540, x*300/960, y*200/540, 1);
		selectingKeyScreenText = new ArrayList<CustomText>();
		for(int i = 0; i < s.length(); i ++) {
			if(i < 10) {
				
			}
		}
		selectingKeyScreenText.add(new CustomText(330, 50, 1000,  200, s, true ,false));
	}
	
	/** --VOLUME TOGGLE--
	 * 
	 */
	public void updateVolumeToggle() {
		toggleVolume = new ImageButton(200, 300, 50, 50, "resources\\ui\\volume\\v" + Test.test.volume + ".png");
		toggleVolume.setEnabled(true);
		toggleVolume.setAction(new Action() {
			public void act() {
				if(Test.test.volume - 1 == -1) {
					Test.test.volume = 3;
				}else {
					Test.test.volume -= 1;
				}

				parentScreen.remove(toggleVolume);
				updateVolumeToggle();
				parentScreen.addObject(toggleVolume);
				System.out.println("resources\\ui\\volume\\v" + Test.test.volume + ".png");
			}
		});
	}
	
	/**
	 * Will set all buttons' enabled to
	 * the boolean b
	 * @param b
	 */
	public void toggleButtons(boolean b) {
		back.setEnabled(b);
		toggleVolume.setEnabled(b);
		for(int i = 0; i < 4; i++) {
			hiddenKeyButtons.get(i).setEnabled(b);
		}
	}
}
