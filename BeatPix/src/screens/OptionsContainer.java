package screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gui.GUIApplication;
import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import mainGame.MainGUI;
import screens.components.CustomText;
import screens.components.ImageButton;
import screens.components.ScalablePixelBack;
import screens.interfaces.Options;

public class OptionsContainer{

	Options parentScreen; // Screen for popup 
	int x; int y; // Width and height of screen
	/**IMPORTANT FOR DIMENSIONS:**
	 * 
	 * x1 and y1 are determined through trial and error on the basic
	 * 960 x 540 dimensions. Multiplying by the new screen's x by the
	 * original ratio would result in proper scaling.
	 * 
	 * WIDTH: x * x1 / 960
	 * HEIGHT: y * y1 / 540
	 */
	
	Component blackBack; // Black out previous screen
	ScalablePixelBack background; // Pop-up background
	ArrayList<CustomText> labelText; // Basic texts
	
	ImageButton back; // Back button
	CustomText backText; // Text for back button
	
	ArrayList<ImageButton> keySelect; // Letters of key select
	ArrayList<Button> hiddenKeyButtons; // Hidden buttons behind keySelect
	ArrayList<ScalablePixelBack> keyBackground; //Background for hidden buttons
	
	
	ImageButton toggleVolume; // Volume toggle button
	
	ScalablePixelBack selectingKeyScreen; // Pop-up when selecting key
	ArrayList<CustomText> selectingKeyScreenText; // Text for pop-up
	
	private int selectingKeyPhase; // User not selecting key or selecting (-1,0)
	private int columnButtonSelected; // keySelect user has chosen
	
	/**Constructor**
	 * 
	 * selectingKeyPhase set to default state (-1)
	 * 
	 * All components are created for use later
	 * 
	 * @param x
	 * @param y
	 * @param currentScreen
	 */
	public OptionsContainer(int x , int y, Options currentScreen) {
		this.parentScreen = currentScreen;
		this.x = x;
		this.y = y;
		
		selectingKeyPhase = -1;
		createComponents();
	}
	
	/**Adds and Removes Components to Screen**
	 * 
	 * The method that is called whenever a button or
	 * key is clicked to access the options
	 * 
	 * Works by adding all the components to the currentScreen
	 * of the GUIApp by utilizing the FullFunctionScreen's
	 * addObject() and remove()
	 */
	public void addObjects() {
		parentScreen.addObject(blackBack);
		parentScreen.addObject(background);
		/*for(CustomText c: labelText) {
			parentScreen.addObject(c);
		}*/
		
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
		/*for(CustomText c: labelText) {
			parentScreen.remove(c);
		}*/
		
		parentScreen.remove(back);
		parentScreen.remove(backText);
		
		for(int i = 0; i<4; i++) {
			parentScreen.remove(hiddenKeyButtons.get(i));
			parentScreen.remove(keyBackground.get(i));
			parentScreen.remove(keySelect.get(i));
		}
		
		parentScreen.remove(toggleVolume);
		
		//Reenables clickables on parentScreen and returns cursor to arrow
		parentScreen.toggleButtons(true);
		GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**Short Helper Method for Organization
	 *
	 * Creates black out(tinted) background as well as
	 * the pixel background for the options
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
		
		background = new ScalablePixelBack(x/10,
												y/10,
													x*8/10,
														y*8/10,
															1.5);
		createLabelText();
		
		createBackButton();
		
		createKeySelects();
		
		updateVolumeToggle();
	}
	
	/**--LABEL TEXTS
	 * 
	 * Static texts that are part of the background
	 * 
	 */
	public void createLabelText() {
		
	}
	
	 /**--BACK BUTTON--
	  * 
	  * Same design as mainMenu's buttons
	  * 
	  * Will call removeObjects() in order to remove all of
	  * Option's components
	  */
	public void createBackButton() {
		back = new ImageButton(x*600/960,
									y*180/540, 
										x*200/960, 
											y*50/540,
												"resources\\ui\\buttons\\buttongeneral.png");
		
		backText = new CustomText(x*620/960, 
									y*200/540, 
										x*150/960, 
											y*40/540,
												"Back",
													true, true);
		back.setAction(new Action() {
			public void act() {
				removeObjects();
			}
		});
		back.setEnabled(true);
	}
	
	/**--KEY SELECT BUTTONS--
	 * **Since I'm Lazy**
	 * Three Parts:
	 * -ScalablePixelBack - for aesthetics, looks like a button
	 * -ImageButton - only used to show letters
	 * -Button - the real button hidden behind ScalablePixelBack
	 *  
	 * Letters aren't aligned with ScalablePixelBack
	 */
	public void createKeySelects() {
		
		keyBackground = new ArrayList<ScalablePixelBack>();
		keySelect = new ArrayList<ImageButton>();
		hiddenKeyButtons = new ArrayList<Button>();
		
		for(int i = 0; i < 4; i ++) {
			keyBackground.add(new ScalablePixelBack(x*i*110/960 + x*180/960,
														y*150/540,
															x*100/960,
																x*100/960, 
																	1.3));
			
			hiddenKeyButtons.add(new Button(x*i*110/960 + x*180/960,
												y*150/540,
													x*100/960,
														x*100/960, 
															"", null));
			
/**/		keySelect.add(new ImageButton(x*i*110/960 + x*200/960,
												y*170/540,
													x*80/960,
														x*40/960, 
															"resources\\text\\" + MainGUI.getKeys(i) + ".png"));
		}
		setKeySelectActions();
	}
	
	/**Replaces Old Key with New**
	 * 
	 * Removes old letter image from parentScreen,
	 * recreates it and adds it back
	 */
	public void recreateKey(int x1) {
		parentScreen.remove(keySelect.get(x1));
		keySelect.set(x1, new ImageButton(x*x1*110/960 + x*200/960,
												y*170/540, 
													x*80/960, 
														x*40/960, 
															"resources\\text\\" + MainGUI.getKeys(x1) + ".png"));
		parentScreen.addObject(keySelect.get(x1));
	}

	/**Set keySelect Actions Manually**
	 * 
	 * Sets the keySelect actions one by one because within
	 * Action() there can't be any outside influence (such as i)
	 * that will be saved onto the button itself
	 * 
	 * selectingKeyPhase is set to 0 (selecting a key) and
	 * the specific columnButton selected has to be tracked
	 * 
	 * Creates pop up asking for a key and the
	 * other Option's buttons have to be disabled
	 */
	public void setKeySelectActions() {
		hiddenKeyButtons.get(0).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 0;
				
				createSelectingKeyPopUp("Awaiting key input...");
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(1).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 1;
				
				createSelectingKeyPopUp("Awaiting key input...");
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(2).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 2;
				
				createSelectingKeyPopUp("Awaiting key input...");
				toggleButtons(false);
			}
		});
		hiddenKeyButtons.get(3).setAction(new Action() {
			public void act() {
				selectingKeyPhase = 0; 
				columnButtonSelected = 3;
				
				createSelectingKeyPopUp("Awaiting key input...");
				toggleButtons(false);
			}
		});

		
	}
	
	/**Method Called by Other Screens**
	 * 
	 * Method called by other screens whenever
	 * kepPress/keyReleased is called in order
	 * to determine which key the user chose
	 * 
	 * Example:
	   	public void keyPressed(KeyEvent e) {
			passKeyCodeIntoOptions(e);
		}
	 * 
	 * Checks if key pressed is not in use:
	 * -If in unavailable, it will stay phase 0
	 *  where it will wait for another key press
	 * -If available, it will auto set it and
	 *  go into the non selecting phase
	 */
	public void readKey(KeyEvent e) {
		int tempX = e.getKeyCode();
		
		if(selectingKeyPhase == 0) {
			boolean validKey = true;
			for(String s: MainGUI.getKeys()) {
				if((char) tempX == s.charAt(0)) {
					validKey = false; break;
				}
			}
			
			if(validKey) {
				String tempS = ""+ (char) tempX;
				MainGUI.setKeys(columnButtonSelected, tempS);
				
				selectingKeyPhase = -1;
				recreateKey(columnButtonSelected); // needs to recreate that specific letter
				
				createSelectingKeyPopUp("Key set to " + (char) tempX);
				System.out.println("Key set to " + (char) tempX);
				
				// will remove the popup after specified time and re-enable options
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
						removeSelectingKeyPopUp();
						toggleButtons(true);
					}
				}, 200);
				
			}else {
				selectingKeyPhase = 0;
				
				createSelectingKeyPopUp("Reselect key");
				System.out.println("Reselect key");
			}
		}
	}
	
	/** --KEY SELECTION SCREEN--
	 * 
	 * Two Parts:
	 * -ScalablePixelBack - same as Option's overall background
	 * -ArrayList<CustomText> - CustomText with multiple lines
	 *                          in order to scale by height
	 * 
	 * Pop-up that appears when user is choosing a new key to
	 * bind to a column then displays if user successfully 
	 * binded or they have to reselect a valid key
	 * 
	 * The ArrayList is built by determining the string width
	 * and dividing it into rows in order to fit a specific
	 * width and height
	 * 
	 * Also adds it onto the parentScreen
	 */
	public void createSelectingKeyPopUp(String s) {
		
		selectingKeyScreen = new ScalablePixelBack(x*330/960,
														y*100/540, 
															x*300/960, 
																y*200/540, 
																	1);
		
		selectingKeyScreenText = new ArrayList<CustomText>();
		for(int i = 0; i < s.length(); i ++) {
			if(i < 10) {
				
			}
		}
		
		parentScreen.addObject(selectingKeyScreen);
		/*for(CustomText c: selectingKeyScreenText) {
			parentScreen.addObject(c);
		}*/
	}
	public void removeSelectingKeyPopUp() {
		parentScreen.remove(selectingKeyScreen);
		/*for(CustomText c: selectingKeyScreenText) {
			parentScreen.remove(c);
		}*/
	}
	
	/** --VOLUME TOGGLE--
	 *
	 * User clicks button to toggle between 4 stages
	 * (Mute, V1, V2, Max)
	 * 
	 * Each click removes the previous toggleVolume, changes the
	 * volume in MainGUI, then recreates toggleVolume based off the
	 * new volume
	 */
	public void updateVolumeToggle() {
		
		toggleVolume = new ImageButton(200, 
											300, 
												50, 
													50, 
														"resources\\ui\\volume\\v" + MainGUI.getVolume() + ".png");
		toggleVolume.setEnabled(true);
		
		toggleVolume.setAction(new Action() {
			public void act() {
				if(MainGUI.getVolume() - 1 == -1) {
					MainGUI.setVolume(3);
				}else {
					MainGUI.setVolume(MainGUI.getVolume()-1);
				}

				parentScreen.remove(toggleVolume);
				updateVolumeToggle(); // When clicked it recreates toggleVolume
				parentScreen.addObject(toggleVolume);
			}
		});
	}
	
	/**Changes Enabled of Option's Clickables
	 * 
	 * Sets the enabled for all of OptionContainer's
	 * clickable components to b
	 * 
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
