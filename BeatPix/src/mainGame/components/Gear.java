package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.components.Action;
import gui.components.Button;
import mainGame.screens.GameScreen;

/**
 * This button will be used to allow the player to pause from the game and enter the escape menu
 * @author Justin Yau
 *
 */
public class Gear extends Button {

	private int oHeight; //The original height will be stored here
	private int oWidth; //The original width will be stored here
	private int scaledWidth; //The scaled width will be stored here
	private int scaledHeight; //The scaled height will be stored
	private int oY; //The original y coordinate of the gear
	
	/**
	 * Creates a button that can be used to allow the player to pause from the game and enter the escape menu
	 * @author Justin Yau
	 *
	 */
	public Gear(int x, int y, int w, int h) {
		super(x, y, w, h, " ", null, new Action() {
			
			@Override
			public void act() {
				if(!GameScreen.game.getPause()) {
					GameScreen.game.pauseGame();
				}
			}
			
		});
		oY = y;
		oHeight = h;
		oWidth = w;
		scaledWidth = w;
		scaledHeight = h;
		update();
	}
	
	/**
	 * This method returns the original y coordinate of the gear
	 * @return - Returns the original y coordinate of the gear
	 * 
	 * @author Justin Yau	
	 */
	public int getOY() {
		return oY;
	}
	
	/**
	 * This method returns the original height of the gear
	 * @return - Returns the original height of the gear
	 * 
	 * @author Justin Yau
	 */
	public int getOHeight() {
		return oHeight;
	}
	
	/**
	 * This method returns the original width of the gear
	 * @return - Returns the original width of the gear
	 * 
	 * @author Justin Yau
	 */
	public int getOWidth() {
		return oWidth;
	}
	
	/**
	 * This method calculates and sets the new scaled widths and heights based on the inputted scales
	 * @param xScale - The x scale that was applied to the gear
	 * @param yScale - The y scale that was applied to the gear
	 * 
	 * @author  Justin Yau
	 */
	public void updateScales(double xScale, double yScale) {
		scaledWidth = (int) (oWidth * xScale);
		scaledHeight = (int) (oHeight * yScale);
	}
	
	public boolean isOnButton(int x, int y) {
		return x>getX() && x<getX()+scaledWidth 
		&& y > getY() && y<getY()+scaledHeight;
	}
	
	public void drawButton(Graphics2D g, boolean hover) {
		try {
			BufferedImage theGear = ImageIO.read(new File("resources/escapebuttons/gearbutton.png"));
			g.drawImage(theGear, 0, 0, getWidth(), getHeight(), 0, 0, theGear.getWidth(), theGear.getHeight(), null);
		} catch (IOException e) {
			System.out.println("Path file not correct.");
		}
	}
	
}
