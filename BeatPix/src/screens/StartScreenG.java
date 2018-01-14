package screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Graphic;
import gui.interfaces.*;
import gui.userInterfaces.*;

public class StartScreenG extends FullFunctionScreen implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**Design:
	 * -Background - will be a basic static image
	 * -Title - will be an image component that
	 * -StartButton - basic button to start the game
	 * 
	 * --Clicking start will scroll down the image to 
	 *   get to Main Menu background image
	 */
	
	private Timer time;
	private int timeCounter;
	
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
		
		background = updateBackground("D:\\Downloads\\!!!BeatBotArt\\Backgrounds\\start.jpg");
		title = updateTitle("D:\\Downloads\\!!!BeatBotArt\\title.png");
		start = updateStart("D:\\Downloads\\!!!BeatBotArt\\UI\\Buttons\\startbutton.png");
		
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
		time = new Timer();
		timeCounter = 100;
		background.setX(0); background.setY(0);
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(timeCounter > 0) {
					background.setY(background.getY() - 5); //will move up 5 for 100 times, moving 500 px
					timeCounter--;
				}else {
					fadeIns();
					this.cancel();
				}
			}
		}, 0, 42); //24fps
	}
	public void fadeIns() {
		time = new Timer();
		title.setAlpha(0.0f);
		start.setAlpha(0.0f);
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
		time = new Timer();
		title.setAlpha(1f);
		start.setAlpha(1f);
		allowClick = false;
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
		time = new Timer();
		timeCounter = 100;
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(timeCounter > 0) {
					background.setY(background.getY() - 5); //will move up 5 for 100 times, moving 500 px
					timeCounter--;
				}else {
					this.cancel();
				}
			}
		}, 0, 42); //24fps
	}
}
