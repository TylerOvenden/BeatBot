package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.Component;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.CustomText;
import screens.components.ImageButton;
import screens.interfaces.Options;

public class InformationContainer {

	Options parentScreen; // Screen for popup 
	private Component blackBack;
	private ArrayList<CustomText> information;
	private ImageButton back;
	
	
	public InformationContainer(Options parentScreen) {
		this.parentScreen = (Options) parentScreen;
		createObjects();
	}
	public void addObjects() {
		parentScreen.addObject(blackBack);
		parentScreen.addObject(back);
	}
	
	public void createObjects() {
		blackBack = new Component(0, 0, parentScreen.getWidth(), parentScreen.getHeight()) {
			public void update(Graphics2D g) {
				g.setColor(Color.BLACK);
				g.fillRect(0,0, getWidth(), getHeight());
			}
		};
		blackBack.update();
		
		back = new ImageButton(50,50,50,50,"resources\\WhiteFont\\I_White_Transparent.png");
		back.setEnabled(true);
		back.setAction(new Action() {
			public void act() {
				parentScreen.remove(back);
				parentScreen.remove(blackBack);
				
				parentScreen.toggleButtons(true);
			}
		});
	}
	
	public void createInstructions(String s) {
		
	}
}
