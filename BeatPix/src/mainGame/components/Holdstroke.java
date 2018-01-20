package mainGame.components;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.components.AnimatedComponent;
import gui.interfaces.Visible;
import mainGame.components.interfaces.HoldstrokeInterface;
import mainGame.screens.GameScreen;

/**
 * This class essentially makes the scroll keystroke that the user will have to follow to maintain or increase accuracy.
 * 
 * @author Justin Yau
 *
 */
public class Holdstroke extends AnimatedComponent implements HoldstrokeInterface {
	
	private int fallSpeed; //The speed at which you would like the stroke to fall at
	private int holdTime; //The time the user has to hold down a particular key for this stroke
	private int height; //The height of the stroke for visuals
	private boolean cancel;
	private boolean pause;
	private boolean switchFunc;
	private ArrayList<BufferedImage> frames;
	private int fallTime;
	private String path;

	public Holdstroke(int x, int y, int h, int sTime, String path) {
		super(x,y,64, h + 65);
		fallTime = 5;
		height = h + 64;
		this.path = path;
		frames = new ArrayList<BufferedImage>(0);
		addSequence(path, 100, 0, 420 - height, 64, height, 4);
		for(BufferedImage img: this.frame) {
			frames.add(img);
		}
		Thread animation = new Thread(this);
		animation.start();
		update();
	}

	public void update() {
		super.update();
		setY(getY() + 1);
		if(determineCurrentHeightFromY() == (height)) {
			switchFunc = true;
		}
		if(switchFunc) {
			resizeFramesEnd(determineCurrentHeightFromY());
		}
		else {
			resizeFramesStart(determineCurrentHeightFromY());
		}
	}
	
	public int determineCurrentHeightFromY() {
		int bottomHeight = getY();
		int bottomHeightFromStart = bottomHeight - GameScreen.columnY;
		int bottomHeightFromBottom = GameScreen.columnY + GameScreen.columnHeight - bottomHeight;
		if(bottomHeightFromStart <= 0) {
			return 1;
		}
		else if(height - 64 >= bottomHeightFromStart) {
			if(height > GameScreen.columnHeight) {
				return height;
			}
			return bottomHeightFromStart + 64;
		}
		else if(height >= bottomHeightFromBottom + GameScreen.distanceAAfterGoal  ) {
			if(bottomHeightFromBottom <= - GameScreen.distanceAAfterGoal ) {
				GameScreen.game.removeHoldStroke(this);
				cancel = true;
				return 1;
			}
			return bottomHeightFromBottom + GameScreen.distanceAAfterGoal ;
		}
		return height;
	}

	public void resizeFramesStart(int height) {
		if(height == this.height) {
			return;
		}
		for(int i = 0; i < frames.size(); i++) {
			BufferedImage fram = frames.get(i);
			int width = fram.getWidth();
			int h1 = fram.getHeight();
			BufferedImage dest = fram.getSubimage(0, h1 - height, width, height);
			BufferedImage nImg = new BufferedImage(width, this.height, dest.getType());
			int horizontalOffset = 0;
			int verticalOffset = height;
			int widthToMove = width - horizontalOffset;
			int heightToMove = nImg.getHeight() - verticalOffset;
			int[] rgb = nImg.getRGB(0, 0, widthToMove, heightToMove, null, 0, widthToMove);
			nImg.setRGB(horizontalOffset,verticalOffset,widthToMove, heightToMove,rgb, 0, widthToMove);
			Graphics2D g2d = nImg.createGraphics();
			g2d.drawImage(dest, 0, 0, width, height, null);
			g2d.dispose();
			this.frame.set(i, nImg);
		}
	}
	
	public void resizeFramesEnd(int height) {
		if(height == this.height) {
			return;
		}
		for(int i = 0; i < frames.size(); i++) {
			BufferedImage fram = frames.get(i);
			int width = fram.getWidth();
			BufferedImage dest = fram.getSubimage(0, 0, width, height);
			BufferedImage nImg = new BufferedImage(width, this.height, dest.getType());
			Graphics2D g2d = nImg.createGraphics();
			g2d.drawImage(dest, 0, 0, null);
			g2d.dispose();
			this.frame.set(i, nImg);
		}
	}
	
	/**
	 * This method makes the program sleep for the given amount of time
	 * 
	 * @param time - Time in ms that you would like to make the program sleep for
	 * 
	 * @author Justin Yau
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will make the keystroke gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void holdstrokeFall() {
		cancel = false;
		pause = false;
		while(!(isBeyondGoal(GameScreen.columnHeight + GameScreen.columnY + GameScreen.distanceAAfterGoal)) && !cancel) {
			while(pause) {
				sleep(0);
			}
			setY(getY() + 1);
			sleep(fallTime);
			update();
		}
		if(!cancel) {
			GameScreen.game.removeHoldStroke(this);
			//Place Scoring Here
			GameScreen.game.getTiming().changeImg("resources/miss.png");
			GameScreen.game.getCombo().set();
			GameScreen.game.calcAcc(0);
			//Place Scoring Here
		}
		update();
	}
	
	public boolean isBeyondGoal(int goal) {
		return getY() + height > goal;
	}

	/**
	 * This method calculates the height of the stroke, for visuals. 
	 * 
	 * @author Justin Yau
	 */
	public static int determineHeight(int holdTime, int fallSpeed) {
		return (int) (holdTime / fallSpeed);
	}

}
