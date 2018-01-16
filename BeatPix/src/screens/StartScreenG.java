package screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import gui.components.Graphic;
import gui.interfaces.*;
import gui.userInterfaces.*;

public class StartScreenG extends FullFunctionScreen implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6794226819818369625L;
	/**Design:
	 * -Background - will be a basic static image
	 * -Title - will be an image component that
	 * -StartButton - basic button to start the game
	 * 
	 * --Clicking start will scroll down the image to 
	 *   get to Main Menu background image
	 */
	
	private Timer time;
	
	private Graphic background;
	
	private Graphic title;
	private Graphic start;
	
	private boolean allowClick = false;

	public StartScreenG(int width, int height) {
		super(width, height);
	}

	public void mouseClicked(MouseEvent e) {
		if(allowClick) {
			fadeOuts();
		}
	}
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
/**/		background = updateBackground("\\BeatBot\\BeatPix\\resources\\backgrounds\\start.jpg");
/**/		title = updateTitle("\\BeatBot\\BeatPix\\resources\\title.png");
/**/		start = updateStart("\\BeatBot\\BeatPix\\resources\\ui\\buttons\\startbutton.png");
		
		title.setAlpha(0.0f);
		start.setAlpha(0.0f);
		
		scrollIn();
		
		viewObjects.add(background);
		viewObjects.add(title);
		viewObjects.add(start);
	}
//--Start ( [START] )--//
	private Graphic updateStart(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; int x; int y;
		w = getWidth()/5;
		h = getHeight()/5;
		x = (getWidth()/2) - w/2;
		y = (getHeight()/2) - h/2 + 100; //will have to modify 100 in order to scale
		
		return new Graphic(x,y,w,h,path);
	}
	
//--Title (BEATBOT)--//
	private Graphic updateTitle(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; int x; int y;
		w = getWidth()/4;
		h = getHeight()/4;
		x = (getWidth()/2) - w/2;
		y = (getHeight()/2) - h/2;
		return new Graphic(x,y,w,h,path);
	}
	
//--Background (background)--//
	private Graphic updateBackground(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; // 0 for either will use original image size/width
		int x = 0; int y = 0;
		if(background != null) {
			x = background.getX(); y = background.getY();
		}
		w = getWidth();
		h = (int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+(getWidth()*0.1)); //makes the width of background always match the screen
		return new Graphic(x,y,w,h,path);
	}
	
	//--Events--//
	public void scrollIn() {
		
		background.setX(0); background.setY(0);
		
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(background.getY() > -background.getHeight()/2+getHeight()) {
					background.setY(background.getY() - 1);
				}else {
					fadeIns();
					this.cancel();
				}
			}
		}, 0, 10); //100fps
	}
	public void fadeIns() {
		
		title.setAlpha(0.0f);start.setAlpha(0.0f);
		
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(title.getAlpha() > 0.7f) {
					if(start.getAlpha() < 0.99f) {
						start.setAlpha(start.getAlpha() + 0.01f);
					}
				}
				if(title.getAlpha() <= 0.99f) {
					title.setAlpha(title.getAlpha() + 0.01f);
				}
				if(title.getAlpha() >= 0.99f && start.getAlpha() >= 0.99f) {
					this.cancel();
					allowClick = true;
				}
			}
		}, 0, 10); //100fps
	}
	public void fadeOuts() {
		
		title.setAlpha(1f);start.setAlpha(1f);
		allowClick = false;

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(title.getAlpha() <= 0.01f && start.getAlpha() <= 0.01f) {
					scrollOut();
					this.cancel();
				}else {
					start.setAlpha(start.getAlpha() - 0.01f);
					title.setAlpha(title.getAlpha() - 0.01f);
				}
			}
		}, 0, 10); //100fps
	}
	public void scrollOut() {
		
		title.setAlpha(0f);start.setAlpha(0f);
		
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(background.getY() > -background.getHeight() + getHeight()) {
					background.setY(background.getY() - 1);
				}else {
/**/					Test.test.setScreen(new MainMenuScreenG(getWidth(),getHeight()));
					this.cancel();
				}
			}
		}, 0, 10); //100fps
	}
	
	//--Create setDimensions method which will resize/redraw the images based off window size changes--//
}
