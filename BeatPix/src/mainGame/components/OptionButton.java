package mainGame.components;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.components.Button;

public class OptionButton extends Button {

	private String field;
	
	public OptionButton(int x, int y, int w, int h, String text) {
		super(x, y, w, h, text, null, null);
		field = text;
		update();
	}
	
	public void drawButton(Graphics2D g, boolean hover) {
		try {
			BufferedImage theButton = ImageIO.read(new File("resources/TransparentButtonA.png"));
			g.drawImage(theButton, 0, 0, getWidth(), getHeight(), 0, 0, theButton.getWidth(), theButton.getHeight(), null);
		} catch (IOException e) {
			System.out.println("Path file not correct.");
		}
		Font f = new Font("Impact", Font.BOLD, 16);
		g.setFont(f);
		if(field != null) {
			g.drawString(field, (int) (getWidth() * 0.325), (int) (getHeight() * 0.6));
		}
	}

}
