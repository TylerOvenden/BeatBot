package screens.components;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gui.components.Component;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.ComponentContainer;

public class CustomText extends ComponentContainer implements Visible {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1615091844335828099L;
	/**
	 * 
	 */
	private String text;
	
	public CustomText(int x, int y, int w, int h, String text) {
		super(x, y);
		this.text = text;
	}
	
	public String letterImageLocation(String letter) {
		return "resources\\text\\"+letter+".png";
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		text = "AB";
		for(int i = 0; i < text.length(); i++) {
			//letterImageLocation(text.substring(i, i+1)), i*100, 100
			viewObjects.add(new Graphic(100*i,0,letterImageLocation(text.substring(i, i+1))));
			System.out.println("Sunnnny");
		}
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unhoverAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hoverAction() {
		// TODO Auto-generated method stub
		
	}

}
