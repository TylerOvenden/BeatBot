package screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import gui.components.Pane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;

public class CustomPaneG extends Pane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639314147762863597L;
	
	private static int paneLength = 420;
	private static int paneHeight = 50;
	private int numberOfKeys = 4;
	

	public CustomPaneG(FocusController focusController, int x, int y) {
		super(focusController, x, y, paneLength, paneHeight);
		// TODO Auto-generated constructor stub
	}

	public void update(Graphics2D g){
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.black);
		for(int i = 0; i < numberOfKeys; i++)
		{
			if(i == 0)
				g.drawRect(5, 5, 100, 30);
			else
				g.drawRect(100 * i + 5, 5, 100, 30);
		}
		super.drawObjects(g);
	}
	
	public void initAllObjects(List<Visible> viewObjects){
		
	}
	
}
