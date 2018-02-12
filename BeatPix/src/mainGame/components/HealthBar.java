package mainGame.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

import gui.components.Component;

public class HealthBar extends Component {
	
	private int health; //The current health of the player will be stored here
	private Color currentHealthColor; //The current color of the health bar will be stored here
	private HashMap<Integer, Color> healthColors; //The colors of the health bars according to health will be stored here

	public HealthBar(int x, int y, int w, int h) {
		super(x, y, w, h);
		health = 100;
		setUpColors();
		update();
	}

	public void setUpColors() {
		healthColors = new HashMap<Integer, Color>();
		healthColors.put(100, Color.green);
		healthColors.put(50, Color.YELLOW);
		healthColors.put(30, Color.red);
	}
	
	public void applyHealth(int deduction) {
		health -= deduction;
		update();
	}
	
	public Color determineColor() {
		return healthColors.get(health) != null ? healthColors.get(health) : currentHealthColor;
	}
	
	@Override
	public void update(Graphics2D g) {
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(2));
		Rectangle box = new Rectangle(0,0,getWidth(), getHeight());
		g.draw(box);
		currentHealthColor = determineColor();
		g.setColor(currentHealthColor);
		double healthWidth = getWidth() * ((double)health/100);
		Rectangle health = new Rectangle(1,1, (int)healthWidth, getHeight() - 1);
		g.draw(health);
	}

}
