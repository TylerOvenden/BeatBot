package screens.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.interfaces.Clickable;

public class ImageButton extends Graphic implements Clickable{

	/* 
	 * Basically a CustomButton, but the user can set the hover actions
	 * as they want as well as easier implementation of images
	 * */
	
	Action action;
	
	Action hoverAction;
	
	Action unhoverAction;
	
	private boolean hovered;
	
	private boolean left;

	private boolean enabled;
	
	private BufferedImage image;
	private boolean loadedImages;
	
	private int idxArray;
	
	public ImageButton(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h, imageLocation);
		unhoverAction = new Action() {
			public void act() {
				GUIApplication.mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
		hoverAction = new Action() {
			public void act() {
				GUIApplication.mainFrame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		};
	}

	public void setIdxArray(int x) {
		idxArray = x;
	}
	public int getIdxArray() {
		return idxArray;
	}
	
	public void loadImages(String imageLocation, double scale) {
		try{
			//get the full-size image
			ImageIcon icon = new ImageIcon(imageLocation);
	
			int newWidth = (int) (icon.getIconWidth() * scale);
			int newHeight = (int) (icon.getIconHeight() * scale);
			
			image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(icon.getImage(), 0, 0, null);
			
			AffineTransform scaleT = new AffineTransform();
			scaleT.scale(scale, scale);
			AffineTransformOp scaleOp = new AffineTransformOp(scaleT, AffineTransformOp.TYPE_BILINEAR);
			image = scaleOp.filter(image,new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB));
			
			loadedImages = true;
		}catch(Exception e){
			e.printStackTrace();
		}
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
		if(hoverAction != null)
			hoverAction.act();
		setLeft(false);
	}
	public void setHoverAction(Action a) {
		hoverAction = a;
	}
	public void unhoverAction(){
		setLeft(true);
		if(unhoverAction != null)
			unhoverAction.act();
	}
	public void setUnhoverAction(Action a) {
		unhoverAction = a;
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
