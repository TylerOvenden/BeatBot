package shop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Component;

public class CustomRectangle extends Component 
{
	
	private Color color;
	private int thickness;
	public CustomRectangle(int x, int y, int width, int height, Color color, int thickness)
	{
		super(x, y, width, height);
		this.color = color;
		this.thickness = thickness;
		update();
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setStroke(new BasicStroke(thickness));
		g.setColor(color);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.setStroke(g.getStroke());
				
	}

}
