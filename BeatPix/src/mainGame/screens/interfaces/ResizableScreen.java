package mainGame.screens.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import gui.interfaces.Clickable;
import gui.interfaces.Visible;
import gui.userInterfaces.ClickableScreen;
import gui.userInterfaces.FullFunctionScreen;

/**
 * This class is for when you want to make a clickable screen resizeable with all the components scaling to how much the <br>
 * window was resized by. 
 * 
 * @author Justin Yau
 *
 */
public abstract class ResizableScreen extends FullFunctionScreen {

	private int originalWidth; //The original width of the screen will be stored here
	private int originalHeight; //The original height of the screen will be stored here
	private double xScale; //The x scaling factor will be stored in this variable
	private double yScale; //the y scaling factor will be stored in this variable
	
	private ComponentAdapter adapter; //The adapter of the screen will be stored here
	
	/**
	 * Constructer creates a resizeable clickable screen with all the components scaling to how much the <br>
	 * window was resized by. 
	 * 
	 * @author Justin Yau
	 *
	 */
	public ResizableScreen(int width, int height) {
		super(width, height);
		originalWidth = width;
		originalHeight = height;
		xScale = 1;
		yScale = 1;
		setUpComponentListener();
	}

	@Override
	public abstract void initAllObjects(List<Visible> viewObjects);
	
	/**
	 * This method adds a component listener just in case the user attempts to resize the default screen. <br> 
	 * The main goal of this method is to have the components scale with how much the window was resized. <br>
	 * 
	 * @author Justin Yau
	 */
	public void setUpComponentListener() {
		adapter = getComponentAdapter();
		addComponentListener(adapter);
	}
	
	/**
	 * This method will return the current adapter in the screen
	 * @return - The current adapter in the screen
	 */
	public ComponentAdapter getCAdapter() {
		return adapter;
	}
	
	/**
	 * This method generates the default adapter that will change the scales whenever the window is resized
	 * @return - The component adapter generated
	 * 
	 * @author Justin Yau
	 */
	public ComponentAdapter getComponentAdapter() {
		return new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				Component c = (Component)arg0.getSource();
				int height = c.getHeight();
				int width = c.getWidth();
				xScale = ((double) width)/originalWidth;
				yScale = ((double) height)/originalHeight;
			}
			
		};
	}
	
	/**
	 * This method returns the original width of the screen
	 * @return - The original width of the screen
	 * 
	 * @author Justin Yau
	 */
	public int getOWidth() {
		return originalWidth;
	}
	
	/**
	 * This method returns the original height of the screen
	 * @return - The original height of the screen
	 * 
	 * @author Justin Yau
	 */
	public int getOHeight() {
		return originalHeight;
	}
	
	/**
	 * This method sets the x scale of window screen
	 * @param x - X scale of the window screen 
	 * 
	 * @author Justin Yau
	 */
	public void setXScale(double x) {
		xScale = x;
	}
	
	/**
	 * This method sets the y scale of the window screen
	 * @param y - Y scale of the window screen
	 * 
	 * @author Justin Yau
	 */
	public void setYScale(double y) {
		yScale = y;
	}
	
	/**
	 * This method returns the current x scale of the screen
	 * @return - The current x scale of the screen
	 * 
	 * @author Justin Yau
	 */
	public double getXScale() {
		return xScale;
	}
	
	/**
	 * This method returns the current y scale of the screen
	 * 
	 * @return - The current y scale of the screen
	 * @author Justin Yau
	 */
	public double getYScale() {
		return yScale;
	}
	
	public void update(Graphics2D g){
		BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = buffer.createGraphics();
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		drawBackground(g2);
		g2.setColor(Color.black);
		g2.scale(xScale, yScale);
		drawObjects(g2);
		g.drawImage(buffer, 0, 0, null);
	}

}
