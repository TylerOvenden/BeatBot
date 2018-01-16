package shop;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Component;

public class CustomRectangle extends Component {

	private int x;
	private int y;
	
	public CustomRectangle(int x, int y)
	{
		super(x, y, 100, 300);
	}

	@Override
	public void update(Graphics2D g) 
	{
		
		g.drawRect(0, 0, 100, 300);

	}

}
