package mainGame.components;

import gui.components.AnimatedComponent;

/**
 * This class will be used to display where the target press area is.
 * 
 * @author Justin Yau
 *
 */
public class KeystrokeIndicator extends AnimatedComponent {

	private String path; //This will store the img path of the indicator
	private String animPath; //This will store the path of the sprite sheet that will activate on key press
	
	/**
	 * Create a indicator at the given x and y coordinate with the img retrieved from the given path.
	 * 
	 * @param x - The X coordinate of the indicator
	 * @param y - The Y coordinate of the indicator
	 * @param path - The image path file of the indicator
	 * 
	 * @author Justin Yau
	 */
	public KeystrokeIndicator(int x, int y, String p) {
		super(x, y, 70, 70);
		path = "resources/arrows/" + p + "ph.png";
		animPath = "resources/arrows/" + p + "explosion.png";
		addSequence(this.path, 0, 0, 0, 64, 64, 1);
		Thread animation = new Thread(this);
		animation.start();
		update();
	}
	
	/**
	 * This method makes the program sleep for the inputted amount of time
	 * 
	 * @param time - The time that you would like the the program to sleep for
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
	 * This method makes the stroke indicator play an animation to show that the key was hit
	 * 
	 * @author Justin Yau
	 */
	public void animate() {
		super.clear();
		addSequence(animPath, 100, 0, 0, 72, 72, 2);
		addSequence(path, 0, 0, 0, 64, 64, 1);
		setRepeat(false);
		update();
		sleep(1000);
		normalize();
	}
	
	/**
	 * This method makes the stroke indicator resume as a normal indicator
	 * 
	 * @author Justin Yau
	 */
	public void normalize() {
		super.clear();
		addSequence(path, 0, 0, 0, 64, 64, 1);
		update();
	}

}
