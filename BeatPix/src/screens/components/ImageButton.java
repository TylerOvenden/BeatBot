package screens.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.interfaces.Clickable;

public class ImageButton extends Graphic implements Clickable{

	Action action;
	
	private boolean hovered;
	
	private boolean left;

	private boolean enabled;
	
	public ImageButton(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h, imageLocation);
	}



	public void act(){
		if(action != null) action.act();
	}
	
	public void setAction(Action a){
		this.action = a;
	}
	
	//--From Buttons--//
	public boolean isHovered(int x, int y) {
		boolean b = x>getX() && x<getX()+getWidth() 
		&& y > getY() && y<getY()+getHeight();
//		if(b != hovered){
//			
//		}
		hovered = b && enabled;
		if(hovered){
			hoverAction();
		}else if (!hasLeft()){
			unhoverAction();
		}
		return hovered;
	}
	public void hoverAction(){
		GUIApplication.mainFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLeft(false);
	}
	public void unhoverAction(){
		setLeft(true);
		GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(!enabled)hovered = false;
	}
	//--Text Label--//
	public boolean hasLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
}
