package mainGame.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gui.components.Component;
import gui.components.MovingComponent;
import mainGame.components.interfaces.RectangluInterface;
import mainGame.screens.GameScreen;

public class Rectanglu extends MovingComponent implements RectangluInterface {

	private int fallTime; //The speed at which the rectangle falls down
	private int rectHeight;
	private boolean cancel;
	private boolean pause;
	
	public Rectanglu(int x, int y, int w, int h) {
		super(x, y, w, h);
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
	 * This method will make the keyrectangle gradually fall down the display till it hits the goal when it does, it will disappear. <br>
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
	
	public Rectangle rectanglueo(int y, int height) {

		return new Rectangle(2,y,getWidth()-5, height); 
	}
	
	@Override
	public void update(Graphics2D g) {
		super.clear();
		Rectangle rect = new Rectangle();
		int totalHeight = (GameScreen.columnHeight + GameScreen.columnY);
		int currentHeight = (getY() - GameScreen.columnY);
		int bottomHeight = ((getY() + rectHeight));
		if(getY() >= (totalHeight - rectHeight)) {

			rect = rectanglueo(0,totalHeight - getY() + 40);

		}
		//If the rectangle's bottom point reaches the target area, keep spawning a rectangle that gets bigger and bigger until it reaches height
		else if((bottomHeight >= GameScreen.columnY) && (rectHeight >= bottomHeight - GameScreen.columnY)) {

			rect = rectanglueo(rectHeight - currentHeight,currentHeight);
			
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
