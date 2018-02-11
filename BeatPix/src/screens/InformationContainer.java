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
		for(CustomText c: information) {
			parentScreen.addObject(c);
		}
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
				
				for(CustomText c: information) {
					parentScreen.remove(c);
				}
				
				parentScreen.toggleButtons(true);
			}
		});
		
		createInstructions("I am a long line of instructions, I don't know how well this will appear, but it doesn't matter cus how much in struction"
				+ " you need anyways?");
	}
	
	
	
	public int positionOfLastLetterOfLongestWordComboWithinBoundary(String s, int boundary) {
		int idx = boundary;
		for(int i = boundary; i > 0; i--) {
			if(s.length() <= boundary) {
				return s.length()-1;
			}
			if(s.substring(i-1, i).equals(" ") || i == 0) {
				idx = i;
				break;
			}else {
				idx --;
			}
		}
		return idx;
	}
	public ArrayList<String> arrayOfBrokenUpStrings(String s, int boundary) {
		String temps = s;
		ArrayList<String> arrayTemp = new ArrayList<String>();
		while(temps.length() > 0) {
			if(positionOfLastLetterOfLongestWordComboWithinBoundary(temps, boundary) == 0)
				break;
			
			int tempx = positionOfLastLetterOfLongestWordComboWithinBoundary(temps, boundary);
			String tempSub = temps.substring(0,tempx+1);
			
			while(tempSub.length()<boundary) {
				tempSub += " ";
			}
			
			arrayTemp.add(tempSub);
			temps = temps.substring(tempx,temps.length());
		}
		return arrayTemp;
	}
	
	public void createInstructions(String s) {
		information = new ArrayList<CustomText>();
		ArrayList<String> temp = arrayOfBrokenUpStrings(s, 10);
		for(int i = 0; i< temp.size(); i++) {
			CustomText ct = new CustomText(parentScreen.getWidth()/960*	340,
														50*i  + parentScreen.getHeight()/540*	120, 
														parentScreen.getWidth()/960*	280, 
														parentScreen.getHeight()/540*	300, 
																	temp.get(i), true, true, true);
			information.add(ct);
		}
	}
}
