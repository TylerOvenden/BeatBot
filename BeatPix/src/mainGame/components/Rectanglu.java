package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gui.components.MovingComponent;
import mainGame.components.interfaces.RectangluInterface;
import mainGame.screens.GameScreen;

/**
 * This class will enable us to make the hold stroke for the game where the user holds down a button for x amount of time. 
 * 
 * @author Justin Yau
 *
 */
public class Rectanglu extends MovingComponent implements RectangluInterface {

	private int fallTime; //The speed at which the rectangle falls down
	private int rectHeight; //This int will be the value of the Rectangle's height
	private boolean cancel; //This boolean will track whether the stroke was canceled by user interactions
	private boolean pause; //This boolean will track whether the game was paused or not
	private int yPos;
	
	/**
	 * Creates a generic rectangle that will render into the column lanes
	 * 
	 * @param x - x coordinate of the starting position
	 * @param y - y coordinate of the starting position
	 * @param w - width of the rectangle
	 * @param h - height of the rectangle
	 * 
	 * @author Justin Yau
	 */
	public Rectanglu(int x, int y, int w, int h) {
		super(x, y, w, h);
		yPos = y;
		fallTime = 10;
		rectHeight = h;
		update();
	}

	/**
	 * This method will make the rectangle stop falling 
	 * 
	 * @author Justin Yau
	 */
	public void cancelFall() {
		this.cancel = true;
	}
	
	/**
	 * This method will make the rectangle stop falling
	 * 
	 * @author Justin Yau
	 */
	public void pauseFall() {
		pause = true;
	}
	
	/**
	 * This method will make the rectangle continue falling
	 * 
	 * @author Jusitn Yau
	 */
	public void resumeFall() {
		pause = false;
	}
	
	/**
	 * This method will make the rectangle gradually fall down the display till it hits the goal when it does, it will disappear. <br>
	 * Default Time Between Each Fall Call: 10 ms 
	 * 
	 * @author Justin Yau
	 */
	public void rectangleFall() {
		cancel = false;
		pause = false;
		while(!(isBeyondGoal(GameScreen.columnHeight + GameScreen.columnY)) && !cancel) {
			while(pause) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			setY(getY() + 1);
			try {
				Thread.sleep(fallTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update();
		}
		if(!cancel) {
			GameScreen.game.removeRectangle(this);
		}
		update();
	}
	
	/**
	 * This method will check the current y position of the rectangle and see if it has made it past the target area
	 * @param goal - The y coordinate of the goal 
	 * @return - Whether or not the rectangle has passed the goal
	 * 
	 * @author Justin Yau
	 */
	public boolean isBeyondGoal(int goal) {
		return getY() > goal;
	}
	
	/**
	 * This method will update the sleep time that is called in each fall interval. <br>
	 * Default Fall Time - 10 ms <br>
	 * Decreasing this value will make the rectangle fall faster. 
	 * @param speed - Speed, in ms, that you want between each fall call.
	 * 
	 * @author Justin Yau
	 */
	public void updateFallSpeed(int speed) {
		fallTime = speed;
	}
	
	/**
	 * Create a rectangle object on a fixed x coordinate and width
	 * @param y - Y coordinate of the rectangle relative to the original y coordinate
	 * @param height - Height of the rectangle 
	 * @return - Returns a rectangle with the given properties
	 * 
	 * @author Justin Yau
	 */
	public Rectangle rectanglueo(int y, int height) {

		return new Rectangle(2,y,getWidth()-5, height); 
	}
	
	/**
	 * This update decides on a custom rectangle based on current Y position such that <br>
	 * it never goes out of the column lanes.
	 * 
	 * Custom update method created by Justin Yau
	 */
	@Override
	public void update(Graphics2D g) {
		super.clear();
		Rectangle rect = new Rectangle();
		int totalHeight = (GameScreen.columnHeight + GameScreen.columnY);
		int currentHeight = (getY() - GameScreen.columnY);
		int bottomPos = ((getY() + rectHeight - 25));
		int arrowYPos = ((yPos + rectHeight - 25)); //First arrow coords
		int currentYPositionFromStart = (getY() * - 1) + GameScreen.columnY;
		int currentBackHeight = (bottomPos - GameScreen.columnY);

		if(currentBackHeight >= 0 && rectHeight >= currentBackHeight) {

			if(currentBackHeight >= GameScreen.columnHeight) {
				rect = rectanglueo(currentYPositionFromStart,GameScreen.columnHeight);
			}
			else {
				rect = rectanglueo(currentYPositionFromStart,currentBackHeight + 25);
			}
			
		}
		else if(getY() >= (totalHeight - rectHeight)) {

			rect = rectanglueo(0,totalHeight - getY() + 40);

		}
		else {

			rect = rectanglueo(0,rectHeight);
			
		}
		
		g.setColor(Color.YELLOW);
		g.draw(rect);
		g.fill(rect);
	}

	@Override
	public void drawImage(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkBehaviors() {
		// TODO Auto-generated method stub
		
	}

}
