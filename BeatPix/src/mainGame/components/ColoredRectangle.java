package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gui.components.Component;

/**
 * This component is a filled colored rectangle created by the parameters given in the constructor 
 * 
 * @author Justin Yau
 *
 */
public class ColoredRectangle extends Component {

	private int width; //The width of the rectangle
	private int height; //The height of the rectangle
	private float alpha; //The transparency of the rectangle
	private Color color; //The color of the rectangle
	
	/**
	 * Constructor creates a filled color rectangle based on the specified parameters. 
	 * 
	 * @param x - The x coordinate of the rectangle
	 * @param y - The y coordinate of the rectangle
	 * @param w - The width of the rectangle
	 * @param h - The height of the rectangle
	 * @param f - The transparency of the rectangle
	 * @param c - The color of the rectangle
	 * 
	 * @author Justin Yau
	 */
	public ColoredRectangle(int x, int y, int w, int h, float f, Color c) {
		super(x, y, w, h);
		color = c;
		width = w;
		height = h;
		alpha = f;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setColor(color);
		Rectangle rect = new Rectangle(0,0,width,height);
		g.draw(rect);
		g.fill(rect);
		setAlpha(alpha);
	}

}
