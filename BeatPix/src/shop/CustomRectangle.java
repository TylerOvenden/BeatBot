package shop;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Component;

public class CustomRectangle extends Component {

	private int xCoord;
	private int yCoord;
	private int w;
	private int h;
	
	private Color color;
	public CustomRectangle(int x, int y, int width, int height, Color color)
	{
		super(x, y, width, height);
		update();
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setColor(color);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}

}
