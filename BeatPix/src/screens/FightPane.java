package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import gui.components.AnimatedComponent;
import gui.components.FullFunctionPane;
import gui.components.Pane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;

public class FightPane extends FullFunctionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669770711157703541L;
	AnimatedComponent robot;
	boolean hit;

	public FightPane(FocusController focusController, int x, int y) {
		super(focusController, x, y, 400, 200);
		// TODO Auto-generated constructor stub
	}

	public void initAllObjects(List<Visible> viewObjects){
		robot = new AnimatedComponent(30, 100, 117, 84);
		robot.addSequence("resources/spriteSheet.bmp", 250, 0, 0, 39, 28, 2);
		Thread run = new Thread(robot);
		run.start();
		viewObjects.add(robot);
		robot.setVisible(true);
	}
	
	public void update(Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		super.drawObjects(g);
	}
	
	public void robotHit(boolean hasHit)
	{
		if(hasHit)
		{
			robot.clear();
			robot.addSequence("resources/spriteSheet.bmp", 250, 0, 35, 39, 26, 5);
			Thread atk = new Thread(robot);
			atk.start();
		}
		else
		{ 
			
		}
	}
	
	
}
