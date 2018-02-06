package mainGame.components;

import java.awt.Graphics2D;

import gui.components.Component;

public class Combo extends Component {
	
	int combo;

	public Combo(int x, int y, int w, int h) {
		super(x, y, w, h);
		combo=0;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.drawString(""+combo, 0, 0);
	}
	
	public void add() {
		combo++;
	}
	public void set() {
		combo=0;
	}

}
