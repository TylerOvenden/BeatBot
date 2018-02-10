package mainGame.components;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.components.Button;
import mainGame.components.interfaces.ResizeClickInterface;
import mainGame.screens.GameScreen;
import mainGame.screens.interfaces.ResizableScreen;

/**
 * This button is for the ones that appear in the options menu when the user is esacped/paused
 *  
 * @author Justin Yau
 *
 */
public class OptionButton extends Button implements ResizeClickInterface {

	private String field; //The text the button contains
	private int oX; //The original x value of the button
	private int oY; //The original y value of the button
	private int scaledWidth; //The scaled width of the button (FOR RESIZING)
	private int scaledHeight; //The scaled height of the button (FOR RESIZING)
	private ResizableScreen screen; //The resizeable screen the button is on will be here
	
	/**
	 * Constructor creates an "option" button where the user can click the custom button to do what the button says
	 * @param x - The x coordinate of the button
	 * @param y - The y coordinate of the button
	 * @param w - The width of the button
	 * @param h - The height of the button
	 * @param text - What the button should say or what type of option button is should be
	 * 
	 * @author Justin Yau
	 */
	public OptionButton(int x, int y, int w, int h, String text, ResizableScreen s) {
		super(x, y, w, h, text, null, null);
		oX = x;
		oY = y;
		scaledWidth = w;
		scaledHeight = h;
		screen = s;
		field = text;
		update();
	}
	
	/**
	 * This method calculates and sets the new scaled widths and heights based on the inputted scales
	 * @param xScale - The x scale that was applied to the button
	 * @param yScale - The y scale that was applied to the button
	 * 
	 * @author  Justin Yau
	 */
	public void updateScales(double xScale, double yScale) {
		scaledWidth = (int) (getWidth() * xScale);
		scaledHeight = (int) (getHeight() * yScale);
	}
	
	public boolean isOnButton(int x, int y) {
		int sWidth = (int) (screen.getWidth() * (((double) oX)/screen.getOWidth()));
		int sHeight = (int) (screen.getHeight() * (((double) oY)/screen.getOHeight()));
		return x>sWidth && x<sWidth+scaledWidth 
		&& y > sHeight && y<sHeight+scaledHeight;
	}
	
	public void drawButton(Graphics2D g, boolean hover) {
		if(field == null) {
			field = "";
		}
		try {
			BufferedImage theButton = ImageIO.read(new File("resources/TransparentButtonA.png"));
			g.drawImage(theButton, 0, 0, getWidth(), getHeight(), 0, 0, theButton.getWidth(), theButton.getHeight(), null);
		} catch (IOException e) {
			System.out.println("Path file not correct.");
		}
		Font f = new Font("Impact", Font.BOLD, 20);
		FontMetrics fm = g.getFontMetrics(f);
		// Determine the X coordinate for the text
	    int x = (getWidth() - fm.stringWidth(field)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
		g.setFont(f);
		g.drawString(field, x, y);
	}

}
