package mainGame.screens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.ClickableScreen;
import mainGame.components.Keystroke;

public class GameScreen extends ClickableScreen implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016038914105990793L;
	
	private int leftStroke;
	private int leftCStroke;
	private int rightCStroke;
	private int rightStroke;
	
	public GameScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
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
		 * - Create the lanes in the initAllObjects 
		 * - Read the map file in this class
		 * - Spawn the keystroke into the lanes in this class
		*/
		Keystroke key = new Keystroke(100, 100, "resources/arrows/darrow.png");
		viewObjects.add(key);
	
		updateKeyStrokes(KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D);
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
	 * Managed by Justin Yau. This function will run when the user presses a key.
	 * Use e.getKeyCode() and compare it to a key and it will match if the user pressed that key 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//We won't be using this 
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//We won't be using this
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
