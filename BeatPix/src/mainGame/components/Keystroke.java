package mainGame.components;

import java.awt.Graphics2D;

import gui.components.AnimatedComponent;

/*
 * This class will be mainly coded by Justin Yau
 * PERIOD 4 & 5 - AP Computer Science Java
 */
public class Keystroke extends AnimatedComponent {

	public Keystroke(int x, int y, String path) {
		super(x, y, 65, 65);
		addSequence(path, 100, x, y, 65, 65, 4);
		Thread animation = new Thread(this);
		animation.start();
		update();
	}
	
	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
