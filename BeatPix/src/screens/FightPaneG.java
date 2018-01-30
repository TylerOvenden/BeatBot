package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import gui.components.AnimatedComponent;
import gui.components.FullFunctionPane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;

public class FightPaneG extends FullFunctionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669770711157703541L;
	AnimatedComponent robot;
	AnimatedComponent robotHit;
	boolean hasHit;
	boolean animationRunning;
	boolean miss;

	public FightPaneG(FocusController focusController, int x, int y) {
		super(focusController, x, y, 400, 200);
		// TODO Auto-generated constructor stub
	}

	public void initAllObjects(List<Visible> viewObjects){
		robot = new AnimatedComponent(30, 100, 117, 84);
		robot.addSequence("resources/spriteSheet.bmp", 250, 0, 0, 39, 28, 2);
		Thread run = new Thread(robot);
		run.start();
		addKeyListener(this);
		setFocusable(true);
		
		viewObjects.add(robot);
		robot.setVisible(true);
	}
	
	public void update(Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		super.drawObjects(g);
	}
	
	public void runAtkAnimation() 
	{
		if(animationRunning) {
			robot.clear();
			//robot.addSequence("resources/spriteSheet.bmp", 250, 0, 34, 39, 27, 5);
			robot.addSequence("resources/spriteSheet.bmp", 250, 0, 105, 39, 28, 4);

			animationRunning = false;
		}
		else
		{
			robot.clear();
			robot.addSequence("resources/spriteSheet.bmp", 250, 0, 0, 39, 28, 2);
			animationRunning = true;
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		miss = true;
		robot.clear();
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K)
		{
			if(!miss)
			{
				runAtkAnimation();
			}
			else
			{
				robot.clear();
				robot.addSequence("resources/spriteSheet.bmp", 250, 0, 144, 39, 29, 2);
			}
		}
	}
	
	
}
