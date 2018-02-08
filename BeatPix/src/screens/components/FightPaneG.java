package screens.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gui.components.AnimatedComponent;
import gui.components.FullFunctionPane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;

public class FightPaneG extends FullFunctionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669770711157703541L;
	private AnimatedComponent robotIdle;
	private AnimatedComponent robotHit1;
	private AnimatedComponent robotHit3;
	private AnimatedComponent robotHit2;
	private AnimatedComponent robotMiss;
	private AnimatedComponent enemyHit;
	private AnimatedComponent enemyMiss;

	private Thread idleThread;
	private Thread hit1Thread;
	private Thread hit2Thread;
	private Thread hit3Thread;
	private Thread missThread;
	private Thread enemyHitThread;
	private Thread enemyMissThread;
	
	private ArrayList<AnimatedComponent> poweredUp; // the attacks are improved and stronger if the combo is > 10 //Or DO HATS but have to change a lot of the art
	private int combo;
	private String skin = "default"; //Skins will just be recolors of the robot, maybe;
	private boolean animationRunning = false;
	private boolean miss;
	private int pastRand;

	public FightPaneG(FocusController focusController, int x, int y) {
		super(focusController, x, y, 400, 200);
		// TODO Auto-generated constructor stub
	}

	public void initAllObjects(List<Visible> viewObjects){
		String rsrcFile = "resources/sprites/defaultSprite.bmp";
		robotIdle = new AnimatedComponent(30, 100, 117, 84);
		 robotHit1 = new AnimatedComponent(30, 100, 117, 84);
		robotHit3 = new AnimatedComponent(30, 100, 117, 84);
		robotHit2 = new AnimatedComponent(30, 100, 117, 84);
		robotMiss = new AnimatedComponent(30, 100, 117, 84);
		robotIdle.addSequence("resources/sprites/defaultSprite.bmp", 200, 0, 0, 39, 28, 2);
		robotHit1.addSequence(rsrcFile, 200, 0, 105, 39, 28, 4);
		robotHit1.setVisible(false);
		robotHit2.addSequence(rsrcFile, 200, 0, 34, 39, 27, 5);
		robotHit2.setVisible(false);
		robotHit3 = new AnimatedComponent(30,100,117,84);
		robotHit3.addSequence(rsrcFile, 200, 0, 70, 39, 27, 3);
		robotHit3.setVisible(false);
		robotMiss = new AnimatedComponent(30,100,117,84);
		robotMiss.addSequence(rsrcFile, 200, 0, 146, 39, 27, 2);
		robotMiss.setVisible(false);
		
		
		
		hit1Thread = new Thread(robotHit1);
		hit2Thread = new Thread(robotHit2);
		hit3Thread = new Thread(robotHit3);
		missThread = new Thread(robotMiss);
		idleThread = new Thread(robotIdle);
		
		missThread.start();
		hit1Thread.start();
		hit2Thread.start();
		hit3Thread.start();
		idleThread.start();
		
		addKeyListener(this);
		setFocusable(true);
		
		
		viewObjects.add(robotIdle);
		viewObjects.add(robotHit1);
		viewObjects.add(robotHit2);
		viewObjects.add(robotHit3);
		viewObjects.add(robotMiss);

		robotIdle.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e)
	{	
		miss = false;
		if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_F || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K) && (animationRunning == false)) {
			if(!miss) {
				robotIdle.setVisible(false);
				robotHit1.setVisible(false);
				robotHit2.setVisible(false);
				robotHit3.setVisible(false);
				robotMiss.setVisible(false);
				animationRunning = true;
				
				
				int rand = (int) (Math.random()*3);
				while(rand == pastRand)
					rand = (int) (Math.random()*3);
				pastRand = rand;
				
				if(rand == 0) {
					setAnimation(robotHit1, 700);
				} 
				else if(rand == 1) {
					setAnimation(robotHit2, 900);
				} 
				else if(rand == 2) {
					setAnimation(robotHit3, 500);
				}
			} //End if miss statement
			else
			{
				setAnimation(robotMiss, 300);
			}
		}
	}
	
	public void setAnimation(AnimatedComponent a, int s) {
		a.setVisible(true);
		Thread show = new Thread(a);
		show.start();
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				robotIdle.setVisible(true);
				a.setVisible(false);
				a.setRunning(false);
				animationRunning = false;
			}
		}, s                );
	}
	
}
