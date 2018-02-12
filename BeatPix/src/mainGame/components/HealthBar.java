package mainGame.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

import gui.components.Component;

/**
 * Health Bar allows the user to visually see their health progress throughout the game
 * 
 * @author Justin Yau
 */
public class HealthBar extends Component {
	
	private int health; //The current health of the player will be stored here
	private Color currentHealthColor; //The current color of the health bar will be stored here
	private final int green = 100; //The health value for the bar to be green will be stored here
	private final int yellow = 50; //The health value for the bar to be yellow will be stored here
	private final int red = 30; //The health value for the bar to be red will be stored here

	/**
	 * Constructor creates a visual health bar that'll allow the user to visually see their health progress throughout the game
	 * @param x - The x coordinate of the bar
	 * @param y - The y coordinate of the bar 
	 * @param w - The width of the bar
	 * @param h - The height of the bar
	 */
	public HealthBar(int x, int y, int w, int h) {
		super(x, y, w, h);
		health = 100;
		update();
	}
	
	/**
	 * This method applies the health point addition/deduction to the variable responsible for updating the visual
	 * @param deduction - Health points to be taken
	 * 
	 * @author Justin Yau
	 */
	public void applyHealth(int addition) {
		health += addition;
		if(health > 100) {
			health = 100;
		}
		if(health < 0) {
			health = 0;
		}
		update();
	}
	
	/**
	 * This method returns the current health on the health bar
	 * @return - The current health of the health bar
	 * 
	 * @author Justin Yau
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * This method determines the color of the bar based on the current health 
	 * 
	 * @return - The color the bar should be
	 * 
	 * @author Justin Yau
	 */
	public Color determineColor() {
		if(health <= green && health > yellow) {
			return Color.GREEN;
		}
		if(health <= yellow && health > red) {
			return Color.YELLOW;
		}
		if(health <= red && health >= 0) {
			return Color.RED;
		}
		return currentHealthColor;
	}
	
	@Override
	public void update(Graphics2D g) {
		currentHealthColor = determineColor();
		g.setColor(currentHealthColor);
		g.setStroke(new BasicStroke(1));
		double healthHeight = getHeight() - (getHeight() * ((double)health/100));
		Rectangle health = new Rectangle(1, (int)healthHeight, getWidth() - 4, getHeight() - 3);
		g.draw(health);
		g.fill(health);
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(5));
		Rectangle box = new Rectangle(0,0,getWidth() - 1, getHeight() - 1);
		g.draw(box);
	}

}
