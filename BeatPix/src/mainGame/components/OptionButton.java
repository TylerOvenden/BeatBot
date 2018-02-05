package mainGame.components;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.components.Button;
import mainGame.screens.GameScreen;

public class OptionButton extends Button {

	private String field;
	private int oX;
	private int oY;
	private int scaledWidth;
	private int scaledHeight;
	
	public OptionButton(int x, int y, int w, int h, String text) {
		super(x, y, w, h, text, null, null);
		oX = x;
		oY = y;
		scaledWidth = w;
		scaledHeight = h;
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
	
	/**
	 * 
	 */
	public boolean isOnButton(int x, int y) {
		int sWidth = (int) (GameScreen.game.getWidth() * (((double) oX)/GameScreen.game.getOWidth()));
		int sHeight = (int) (GameScreen.game.getHeight() * (((double) oY)/GameScreen.game.getOHeight()));
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
