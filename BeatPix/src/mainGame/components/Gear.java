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
		update();
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
