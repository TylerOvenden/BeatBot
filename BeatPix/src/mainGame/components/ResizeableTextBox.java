package mainGame.components;

import java.awt.Cursor;

import gui.GUIApplication;
import gui.components.TextBox;
import mainGame.screens.interfaces.ResizableScreen;

/**
 * A text box that'll have its hover area scaled to the appropriate place so the user can input things when window is resized. <br />
 * Currently does NOT consider cursor index. 
 * 
 * @author Justin Yau
 */
public class ResizeableTextBox extends TextBox {

	private int scaledWidth; //The scaled width will be stored here
	private int scaledHeight; //The scaled height will be stored
	private int oX; //The original x coordinate of the box
	private int oY; //The original y coordinate of the box
	private ResizableScreen screen; //The original screen will be stored here
	
	/**
	 * Creates a text box that'll have its hover area scaled to the appropriate place so the user can input things when window is resized. <br />
	 * Currently does NOT consider cursor index. 
	 * @param x - X coordinate of the box
	 * @param y - Y coordinate of the box
	 * @param w - Width of the box
	 * @param h - Height of the box
	 * @param text - The text you would like to put in the box before the user inputs
	 * @param s - The screen the box is on
	 */
	public ResizeableTextBox(int x, int y, int w, int h, String text, ResizableScreen s) {
		super(x, y, w, h, text);
		screen = s;
		oX = x;
		oY = y;
		scaledWidth = w;
		scaledHeight = h;
		update();
	}
	
	/**
	 * This method calculates and sets the new scaled widths and heights based on the inputted scales
	 * @param xScale - The x scale that was applied to the box
	 * @param yScale - The y scale that was applied to the box
	 * 
	 * @author  Justin Yau
	 */
	public void updateScales(double xScale, double yScale) {
		scaledWidth = (int) (getWidth() * xScale);
		scaledHeight = (int) (getHeight() * yScale);
	}
	
	public boolean isHovered(int x, int y) {
		int sWidth = (int) (screen.getWidth() * (((double) oX)/screen.getOWidth()));
		int sHeight = (int) (screen.getHeight() * (((double) oY)/screen.getOHeight()));
		boolean b =  x > sWidth && x < sWidth + scaledWidth && y > sHeight+DESCRIPTION_SPACE && y < sHeight + scaledHeight;
		if(b){
			relativeX = x - getX();
			relativeY = y - getY();
			setLeft(false);
			GUIApplication.mainFrame.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		}else if(!hasLeft()){
			setLeft(true);
			GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		return b;
	}

}
