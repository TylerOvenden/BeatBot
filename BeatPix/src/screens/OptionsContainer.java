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
import mainGame.MainGUI;
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
			System.out.println(MainGUI.getKeys(2) + "");
/**/		keySelect.add(new ImageButton(x*i*110/960 + x*200/960,y*170/540,x*80/960,x*40/960, "resources\\text\\" + MainGUI.getKeys(i) + ".png"));
		}
		setKeySelectActions();
	}
	
	/**
	 * Recreates the letters based off the newly
	 * assigned keys on the keySelect buttons
	 * 
	 * Basically removes the old letter and recreates
	 * the new letters and adds it back onto the screen
	 */
	public void updateKeySelect() {
		for(int i = 0; i < 4; i++) {
			parentScreen.remove(keySelect.get(i));
			parentScreen.addObject(keySelect.get(i));
		}
	}
	public void recreateKey(int x1) {
		parentScreen.remove(keySelect.get(x1));
		keySelect.set(x1, new ImageButton(x*x1*110/960 + x*200/960,y*170/540,x*80/960,x*40/960, "resources\\text\\" + MainGUI.getKeys(x1) + ".png"));
		parentScreen.addObject(keySelect.get(x1));
	}

	/**
	 * Sets the keySelect actions manually for each one
	 * because within Action() there can't be any outside
	 * influence (i) that will be saved onto the button itself
	 */
	public void setKeySelectActions() {
		hiddenKeyButtons.get(0).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 0;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(1).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 1;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(2).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 2;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(3).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 3;
				
				createSelectingKeyPopUp("Words");
				parentScreen.addObject(selectingKeyScreen);
				toggleButtons(false);
			}
		});

		
	}
	//--ERROR: When new key is set on Test, it is saved as an Integer value <- probably why recreateKey doesn't work
	/**
	 * Basically the method that is called within
	 * other screens whenever keyPress is called since
	 * all screens will be FullFunctionScreen (thus having
	 * KeyListener and keyPressed/Released)
	 * 
	 * Needs to pass in the KeyEvent from the master method
	 * 
	 * Checks if the key pressed is not one of the already
	 * selected keys and then goes into a state where it waits
	 * for another key press if the key is invalid, or sets it
	 * automatically into the new keys within Test.test
	 */
	boolean validKey;
	public void readKey(KeyEvent e) {
		int tempX = e.getKeyCode();
		
		if(selectingKeyPhase == 0) {
			validKey = true;
			for(String s: MainGUI.getKeys()) {
				//System.out.println((char) tempX + " is it equal to " + s.charAt(0));
				if((char) tempX == s.charAt(0)) {
					validKey = false; break;
				}
			}
			
			if(validKey) {
				//Where ERROR would be
				String tempS = ""+ (char) tempX;

				System.out.println(tempS);
				MainGUI.setKeys(columnButtonSelected, tempS);
				
				selectingKeyPhase = -1;
				recreateKey(columnButtonSelected);
				//updateKeySelect();
				
				parentScreen.remove(selectingKeyScreen);
				toggleButtons(true);
				
				System.out.println("Key set to " + (char) tempX);
				
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
	/**The pixel back is always constant, but can be
	 * changed depending on the text length
	 * 
	 * Creates the text that will be displayed such as:
	 * "Press a key" as it waits for user to select a key
	 * "Key is already in use" or
	 * "Invalid key" when user presses a bad key
	 * and finally "Key set to --" when they successfully change it
	 * 
	 * The text will be set in an ArrayList in order to prevent
	 * overcrowding and multiple lines of CustomText
	 * 
	 * The length is yet to be determined
	 * @param s
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
	 * User clicks the volume icon for it to toggle
	 * between stages, each stage represents a volume
	 */
	/**
	 * Recreates toggleVolume based on the new stage,
	 * sets the action for the next stage as well.
	 * 
	 * Basically removes old toggleVolume, recreates it,
	 * then puts it back in the screen
	 */
	public void updateVolumeToggle() {
		toggleVolume = new ImageButton(200, 300, 50, 50, "resources\\ui\\volume\\v" + MainGUI.getVolume() + ".png");
		toggleVolume.setEnabled(true);
		toggleVolume.setAction(new Action() {
			public void act() {
				if(MainGUI.getVolume() - 1 == -1) {
					MainGUI.setVolume(3);
				}else {
					MainGUI.setVolume(MainGUI.getVolume()-1);
				}

				parentScreen.remove(toggleVolume);
				updateVolumeToggle();
				parentScreen.addObject(toggleVolume);
				//System.out.println("resources\\ui\\volume\\v" + Test.test.volume + ".png");
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
