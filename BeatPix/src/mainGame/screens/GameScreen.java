package mainGame.screens;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;

import gui.components.TextArea;
import gui.interfaces.Visible;
import gui.userInterfaces.ClickableScreen;
import mainGame.components.ColumnLane;
import mainGame.components.Holdstroke;
import mainGame.components.Keystroke;
import mainGame.components.Rectanglu;
import mainGame.components.Song;
import mainGame.components.Timing;

public class GameScreen extends ClickableScreen implements KeyListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016038914105990793L;
	
	//Justin Yau
	private int leftStroke; //Keystroke that the user presses for the left column
	private int leftCStroke; //Keystroke that the user presses for the left center column
	private int rightCStroke; //Keystroke that the user presses for the right center column
	private int rightStroke; //Key stroke that the user presses for the right column
	
	private String title; //Title of the beatmap 
	private int BPM; //Beats per minute from the beatmap
	private String artist; //Artist of the beatmap
	private int offSet; //Offset of the beatmap
	private ArrayList<int[]> beats; //Beats that will be majorly utilized by this screen
	
	public static long startTime; //The starting time in ms
	private boolean playing; //This will be used to determine whether there are more beats to display or not
	
	private ArrayList<Keystroke> strokes ; //All the keystrokes currently on the screen will appear here
	private ArrayList<Rectanglu> rectangles;
	private boolean pause; 
	private int fallTime;
	
	public static GameScreen game; //This will be used to make instance calls from other classes
	
	public static final int columnY = 75; //This is the set Y coordinate of the top of the columnLanes
	public static final int columnWidth = 70; //This is the width of the lanes
	public static final int columnHeight = 350; //This is the height of the lanes
	public static final int distanceG = 150; //Distance from the goal before the user can make a press for a stroke
	
	private Timing timing;
	private TextArea visual;
	
	public static final int[] arrowX = {100, 170, 240, 310}; //X coordinates of the indicators
	//Justin Yau
	
	public GameScreen(int width, int height, Song song) {
		super(width, height);
		
		setFixedSize(false);
		
		game = this;
		
		//Retrieve metadata and beats from the song
		title = song.getTitle();
		BPM = song.getBPM();
		artist = song.getArtist();
		offSet = song.getOffSet();
		beats = song.getBeats();
		
		updateKeyStrokes(KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_UP, KeyEvent.VK_RIGHT);
		
		Thread screen = new Thread(this);
		screen.start();
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
		/*
		 * Justin Yau's Notes/Plans:
		 * The object the is added later will appear on top of previous added objects
		 * key.getY() will return the Y-Coordinate of the component
		 * key.setY() will set the Y-Coordinate of the component
		 * Changing the Y through this will automatically update the component
		 * 
		 * viewObjects.remove(object) removes the object 
		 * 
		 * Use setAlpha(float int) to make the lanes transparent
		 * 
		 * - Create the lanes in the initAllObjects  - Completed
		 * - Read the map file in this class - Completed
		 * - Spawn the keystroke into the lanes in this class - Completed
		 * - Left Column - X: 100 Y: 75
		 * - Left Center Column - X: 170 Y: 75
		 * - Right Column - X: 240 Y: 75
		 * - Right Center Column - X: 310 Y: 75
		 * - Pause - Completed
		 * 
		 * Possible way to make the game pause:
		 * - Utilize wait/notify threads which seems complicated and the wrong application of the methods
		 * - Attempt to have a boolean, pause, and have the threads check for the boolean and sleep when true
		 * 
		 * Place where they must hit keystroke - Y: 425
		*/
	
		//CREATE THE LANES
		//THIS ALREADY MAKES THEM TRANSPARENT TO A SENSE
		addColumnLanes(viewObjects);
		
		/*
		Keystroke leftKey = new Keystroke(100, 75, "resources/arrows/darrow.png");
		viewObjects.add(leftKey);
		
		Keystroke leftCKey = new Keystroke(170, 75, "resources/arrows/darrow.png");
		viewObjects.add(leftCKey);
		
		Keystroke rightCKey = new Keystroke(240, 75, "resources/arrows/darrow.png");
		viewObjects.add(rightCKey);
		
		Keystroke rightKey = new Keystroke(310, 425, "resources/arrows/darrow.png");
		viewObjects.add(rightKey);
		*/
		
		timing=new Timing(175,300, 128, 128);
		viewObjects.add(timing);
		timing.update();
		
	}
	
	/**
	 * This method will add 4 visuals that will represent the lanes that the strokes will drop down through
	 * 
	 * @param viewObjects - The list of objects that will be visible by the display
	 * 
	 * @author Justin Yau
	 */
	public void addColumnLanes(List<Visible> viewObjects) {
		ColumnLane leftLane = new ColumnLane(arrowX[0] - 3,columnY - 5, columnWidth, columnY + columnHeight + 5);
		leftLane.setAlpha((float)0.3);
		viewObjects.add(leftLane);
		
		ColumnLane leftCLane = new ColumnLane(arrowX[1]- 3, columnY -5, columnWidth, columnY + columnHeight + 5);
		leftCLane.setAlpha((float)0.3);
		viewObjects.add(leftCLane);
		
		ColumnLane rightCLane = new ColumnLane(arrowX[2] - 3, columnY -5, columnWidth, columnY + columnHeight + 5);
		rightCLane.setAlpha((float)0.3);
		viewObjects.add(rightCLane);
		
		ColumnLane rightLane = new ColumnLane(arrowX[3] - 3, columnY -5, columnWidth, columnY + columnHeight + 5);
		rightLane.setAlpha((float)0.3);
		viewObjects.add(rightLane);
		//CREATE THE LANES
	}
	
	/**
	 * This method scales the height of the given image and scale
	 * @param imagePath - Image path of the file you would like to scale
	 * @param scale - Height scale that you would like to apply to the image
	 * @return - Resulting image after scale
	 * @author Justin Yau
	 */
	public BufferedImage heightScaleImage(String imagePath, double scale) {
		ImageIcon icon = new ImageIcon(imagePath);
		int newHeight = (int) (icon.getIconHeight() * scale);
		
		BufferedImage image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(icon.getImage(), 0, 0, null);
		
		AffineTransform scaleT = new AffineTransform();
		scaleT.scale(1, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(scaleT, AffineTransformOp.TYPE_BILINEAR);
		image = scaleOp.filter(image,new BufferedImage(icon.getIconWidth(), newHeight, BufferedImage.TYPE_INT_ARGB));
		return image;
	}
	
	/**
	 * Method to update the buttons that the user has to press to make strokes
	 * @param stroke1 - Key to be pressed for left stroke
	 * @param stroke2 - Key to be pressed for left center stroke
	 * @param stroke3 - Key to be pressed for right center stroke
	 * @param stroke4 - Key to be pressed for right stroke
	 * @author Justin Yau 
	 */
	public void updateKeyStrokes(int stroke1, int stroke2, int stroke3, int stroke4) {
		leftStroke = stroke1;
		leftCStroke = stroke2;
		rightCStroke = stroke3;
		rightStroke = stroke4;
	}
	
	/**
	 * Mainly overrided by Justin Yau
	 * This function will run when the user presses a key.
	 * Use e.getKeyCode() and compare it to a key and it will match if the user pressed that key 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			pauseGame();
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_B) {
			resumeGame();
			return;
		}
		
		/*
		if(e.getKeyCode() == KeyEvent.VK_D) {
			System.out.println(timePass());
			System.out.println(strokes.get(0).getStartingTime());
			for(int i=0;i<strokes.size();i++) {
				if(strokes.get(i).getColumnLane()==1) {
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<16) {
						timing.changeImg("resources/perfect.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<40) {
						timing.changeImg("resources/great.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<73) {
						timing.changeImg("resources/good.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<103) {
						timing.changeImg("resources/ok.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<127) {
						timing.changeImg("resources/bad.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<164) {
						timing.changeImg("resources/miss.png");
						break;
					}
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			for(int i=0;i<strokes.size();i++) {
				if(strokes.get(i).getColumnLane()==2) {
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<16) {
						timing.changeImg("resources/perfect.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<40) {
						timing.changeImg("resources/great.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<73) {
						timing.changeImg("resources/good.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<103) {
						timing.changeImg("resources/ok.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<127) {
						timing.changeImg("resources/bad.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<164) {
						timing.changeImg("resources/miss.png");
						break;
					}
				}
			}
				
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			for(int i=0;i<strokes.size();i++) {
				if(strokes.get(i).getColumnLane()==3) {
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<16) {
						timing.changeImg("resources/perfect.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<40) {
						timing.changeImg("resources/great.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<73) {
						timing.changeImg("resources/good.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<103) {
						timing.changeImg("resources/ok.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<127) {
						timing.changeImg("resources/bad.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<164) {
						timing.changeImg("resources/miss.png");
						break;
					}
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			for(int i=0;i<strokes.size();i++) {
				if(strokes.get(i).getColumnLane()==4) {
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<16) {
						timing.changeImg("resources/perfect.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<40) {
						timing.changeImg("resources/great.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<73) {
						timing.changeImg("resources/good.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<103) {
						timing.changeImg("resources/ok.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<127) {
						timing.changeImg("resources/bad.png");
						break;
					}
					if(Math.abs(timePass()-strokes.get(i).getStartingTime())<164) {
						timing.changeImg("resources/miss.png");
						break;
					}
				}
			}
		} */
		if(strokes.size() > 0 && strokes.get(0).distanceFromGoal() <= distanceG) {
			int[] keys = {leftStroke, leftCStroke, rightCStroke, rightStroke};
			ArrayList<Keystroke> strokesToCheck = strokesAtSameTime();
			boolean correctStroke = false;
			
			for(Keystroke stroke: strokesToCheck) {
				if(e.getKeyCode() == keys[stroke.getColumnLane() - 1]) {
					
					displayAcc(stroke);
					removeStroke(stroke); 
					stroke.cancelFall();
					correctStroke = true;
					break;
				
				} 
			}
			if(!correctStroke && madeLegalStroke(e)) {
				//CALCULATE MISS ACCURACY HERE PLACEHOLDER 
				
				Keystroke cStroke = strokes.get(0);
				removeStroke(cStroke);
				cStroke.cancelFall();

			}
		}
		
		/*
		TEST CODE
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			pause = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pause = false;
		}
		*/

		
		
		
	}
	
	private void displayAcc(Keystroke stroke) {
		System.out.println(timePass());
		System.out.println(stroke.getClickTime());
		System.out.println(Math.abs(timePass()-stroke.getClickTime()));
		if(Math.abs(timePass()-stroke.getClickTime())<16) {
			timing.changeImg("resources/perfect.png");
			return ;
		}
		if(Math.abs(timePass()-stroke.getClickTime())<40) {
			timing.changeImg("resources/great.png");
			return ;
		}
		if(Math.abs(timePass()-stroke.getClickTime())<73) {
			timing.changeImg("resources/good.png");
			return ;
		}
		if(Math.abs(timePass()-stroke.getClickTime())<103) {
			timing.changeImg("resources/ok.png");
			return ;
		}
		if(Math.abs(timePass()-stroke.getClickTime())<127) {
			timing.changeImg("resources/bad.png");
			return ;
		}
		if(Math.abs(timePass()-stroke.getClickTime())<164) {
			timing.changeImg("resources/miss.png");
			return ;
		}
	}

	/**
	 * This methods returns whether the user pressed one of the keys that represented a stroke. 
	 * 
	 * @param e - The KeyEvent that contains what key the user pressed
	 * @return - Whether the key pressed was one of the keys that the user was suppose to press to remove a stroke
	 * 
	 * @author Justin Yau
	 */
	public boolean madeLegalStroke(KeyEvent e) {
		int[] keys = {leftStroke, leftCStroke, rightCStroke, rightStroke};
		for(int key: keys) {
			if(e.getKeyCode() == key) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Crucial method to enable the program to register keyboard interactions
	 * DO NOT REMOVE
	 * @return
	 */
	@Override
	public KeyListener getKeyListener(){
		return this;
	}

	/**
	 * This method is to retrieve the next stroke and any other strokes that is supposed to be pressed at the same time
	 * @return - An array list of the next stroke and any strokes meant to be pressed at the same time
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Keystroke> strokesAtSameTime() {
		ArrayList<Keystroke> list = new ArrayList<Keystroke>(0);
		if(strokes.size() != 0) {
			
			list.add(strokes.get(0));
			
			int i = 1;
			
			while(i < 5) {
				if(strokes.size() > i && strokes.get(i).getStartingTime() == list.get(0).getStartingTime()) {
					list.add(strokes.get(i));
				}
				else {
					break;
				}
				i++;
			}
			
		}
		return list;
	}
	
	//We will use this if we want to have a long hold press for the strokes 
	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	//We won't be using this
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		startTime = (System.nanoTime());
		playing = true;
		pause = false;
		strokes = new ArrayList<Keystroke>(0);
		rectangles = new ArrayList<Rectanglu>(0);
		calculateAndSetFallTimeFromBeats();
		playMap();
	}
	
	/**
	 * This method will return the time, in milliseconds, that has ellapsed since the game has started running
	 * 
	 * @return - The time that has ellapsed since start of this particular game, in milliseconds.
	 * 
	 * @author Justin Yau
	 */
	public static long timePass() {
		return ((System.nanoTime() - startTime))/1000000;
	}
	
	/**
	 * This method will set the startTime to the appropriate amount of time that has elapsed. <br> 
	 * To be called after resuming the game
	 * 
	 * @author Justin Yau
	 */
	public static void recalculateStartTime(long ellapsedTime) {
		long timeEllapsedNano = ellapsedTime * 1000000;
		startTime = System.nanoTime() - timeEllapsedNano;
	}
	
	/**
	 * This method will remove the given stroke from this arrayList and the visibleObjects
	 * 
	 * @param e - The stroke that you would like to remove
	 * 
	 * @author Justin Yau
	 */
	public void removeStroke(Keystroke e) {
		strokes.remove(e);
		remove(e);
		remove(e); //Just in case it doesn't get removed the first time
	}
	
	/**
	 * This method will remove the given rectangle from this arrayList and the visibleObjects
	 * 
	 * @param r - The rectangle that you would like to remove
	 * 
	 * @author Justin Yau
	 */
	public void removeRectangle(Rectanglu r) {
		rectangles.remove(r);
		remove(r);
		remove(r); //Just in case it doesn't get removed the first time
	}
	
	/**
	 * This method will change a boolean that will halt all game operations
	 * 
	 * @author Justin Yau
	 */
	public void pauseGame() {
		pause = true;
	}
	
	/**
	 * This method will change a boolean that will resume all game operations
	 * 
	 * @author Justin Yau
	 */
	public void resumeGame() {
		pause = false;
	}
	
	/**
	 * This method will pause all operations until it is resumed by setting the pause boolean to false. <br>
	 * All operations will continue running after things are resumed.
	 * 
	 * @author Justin Yau
	 */
	public void handlePause() {
		long time = timePass();
		for(Keystroke stroke: strokes) {
			stroke.pauseFall();
		}
		for(Rectanglu rect: rectangles) {
			rect.pauseFall();
		}
		while(pause) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(Keystroke stroke: strokes) {
			stroke.resumeFall();
		}
		for(Rectanglu rect: rectangles) {
			rect.resumeFall();
		}
		recalculateStartTime(time);
	}
	
	/**
	 * Calculates the total fall time based on column height and fall time. <br>
	 * CALCULATE FALL TIME BEFORE CALLING THIS METHOD
	 * @return Returns the total fall time based on column height and fall time
	 * @author Justin Yau
	 */
	public int calculateTotalFallTime() {
		return fallTime * columnHeight;
	}
	
	/**
	 * This method calculates the fall time from BPM and sets it to the fall time variable
	 * 
	 * @author Justin Yau
	 */
	public void calculateAndSetFallTimeFromBeats() {
		if(BPM == 0 || BPM <= 45) {
			fallTime = 10;
		}
		else {
			fallTime = (int) (((float)1/BPM) * 800);
		}
	}
	
	/**
	 * This method handles the addition of a new keystroke to the gameboard. <br>
	 * Makes the the rectangle start falling aswell.
	 * 
	 * @param s - The keystroke you would like to add to the game
	 * 
	 * @author Justin Yau
	 */
	public void handleKeystroke(Keystroke s) {
		addObject(s);
		strokes.add(s);
		Thread tr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(strokes.contains(s)) {
					s.keystrokeFall();
				}
					
			}
			
		});
		tr.start();
	}
	
	/**
	 * This method handles the addition of a new rectangle to the gameboard. <br>
	 * Makes the the rectangle start falling aswell.
	 * 
	 * @param rect - The rectangle you would like to add to the game
	 * 
	 * @author Justin Yau
	 */
	public void handleRectangle(Rectanglu rect) {
		addObject(rect);
		rectangles.add(rect);
		Thread tr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(rectangles.contains(rect)) {
					rect.rectangleFall();
				}
					
			}
			
		});
		tr.start();
	}
	
	/**
	 * This method will be used to spawn the strokes in according to the time that has elapsed. 
	 * 
	 * @author Justin Yau
	 */
	public void playMap() {
		while(playing) {
			if(pause) {
				handlePause();
			}
			if(beats.size() == 0) {
				playing = false;
			}
			else if(timePass() >= beats.get(0)[1]) {
				int[] beat = beats.remove(0);
				int lane = beat[0] - 1;
				if(beat[2] != 0) {
					int holdTime = beat[2] - beat[1];
					Holdstroke str = new Holdstroke(arrowX[lane], columnY, beat[1], "resources/arrows/darrow.png", holdTime, fallTime);
					for(Visible s: str.getStrokes()) {
						if(s instanceof Keystroke) {
							handleKeystroke((Keystroke) s);
						}
						else {
							handleRectangle((Rectanglu) s);
						}
					}
				}
				else {
					Keystroke str = new Keystroke(arrowX[lane], columnY, beat[1], "resources/arrows/darrow.png");
					str.updateFallSpeed(fallTime);
					handleKeystroke(str);
				}
				//strokes.add(str);
			}
		}
	}
}
