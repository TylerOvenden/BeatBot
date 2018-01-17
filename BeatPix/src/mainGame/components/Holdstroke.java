package mainGame.components;

import java.util.ArrayList;

import gui.components.AnimatedComponent;
import gui.interfaces.Visible;
import mainGame.components.interfaces.HoldstrokeInterface;
import mainGame.screens.GameScreen;

public class Holdstroke implements HoldstrokeInterface {
	
	private int fallSpeed;
	private int holdTime;
	private int height;
	private ArrayList<Visible> list;

	public Holdstroke(int x, int y, int stime, String path, int holdTime, int fallSpeed) {
		this.holdTime = holdTime;
		this.fallSpeed = fallSpeed;
		determineHeight();
		list = new ArrayList<Visible>(0);
		Keystroke frontStroke = new Keystroke(x, y, stime, path);
		frontStroke.updateFallSpeed(fallSpeed);
		frontStroke.setHold(true);
		Keystroke backStroke = new Keystroke(x, y - height, stime, path);
		backStroke.setHold(true);
		backStroke.updateFallSpeed(fallSpeed);
		Rectanglu rect = new Rectanglu(x, y-height+25, 65, height);
		rect.updateFallSpeed(fallSpeed);
		list.add(backStroke);
		list.add(rect);
		list.add(frontStroke);
	}
	
	public ArrayList<Visible> getStrokes() {
		return list;
	}
	
	//Each fall speed 
	public void determineHeight() {
		height = (int) (holdTime / fallSpeed);
	}

}
