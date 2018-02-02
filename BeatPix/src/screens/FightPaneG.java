package screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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

	private Thread idleThread;
	private Thread hit1Thread;
	private Thread hit2Thread;
	private Thread hit3Thread;
	private Thread missThread;
	
	boolean hasHit;
	boolean animationRunning = false;
	boolean miss;
	int pastRand;

	public FightPaneG(FocusController focusController, int x, int y) {
		super(focusController, x, y, 400, 200);
		// TODO Auto-generated constructor stub
	}

	public void initAllObjects(List<Visible> viewObjects){
		robotIdle = new AnimatedComponent(30, 100, 117, 84);
		robotIdle.addSequence("resources/spriteSheet.bmp", 200, 0, 0, 39, 28, 2);
		robotHit1 = new AnimatedComponent(30,100,117,84);
		robotHit1.addSequence("resources/spriteSheet.bmp", 200, 0, 105, 39, 28, 4);
		robotHit1.setAlpha(0);
		robotHit2 = new AnimatedComponent(30,100,117,84);
		robotHit2.addSequence("resources/spriteSheet.bmp", 200, 0, 34, 39, 27, 5);
		robotHit2.setAlpha(0);
		robotHit3 = new AnimatedComponent(30,100,117,84);
		robotHit3.addSequence("resources/spriteSheet.bmp", 200, 0, 70, 39, 27, 3);
		robotHit3.setAlpha(0);
		robotMiss = new AnimatedComponent(30,100,117,84);
		robotMiss.addSequence("resources/spriteSheet.bmp", 200, 0, 146, 39, 27, 2);
		
		robotMiss.setAlpha(0);
		
		
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
	
	public void update(Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		super.drawObjects(g);
	}
	
	public void keyPressed(KeyEvent e)
	{	
		if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K) && (animationRunning == false)) {
			
			robotIdle.setAlpha(0);
			robotHit1.setAlpha(0);
			robotHit2.setAlpha(0);
			robotHit3.setAlpha(0);
			robotMiss.setAlpha(0);
			animationRunning = true;
			
			
			int rand = (int) (Math.random()*3);
			while(rand == pastRand)
				rand = (int) (Math.random()*3);
			pastRand = rand;
			
			if(rand == 0) {
				robotHit1.setAlpha(1);
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						robotIdle.setAlpha(1);
						robotHit1.setAlpha(0);
						animationRunning = false;
					}
				}, 800);
			} 
			else if(rand == 1) {
				robotHit2.setAlpha(1);
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						robotIdle.setAlpha(1);
						robotHit2.setAlpha(0);
						animationRunning = false;
					}
				}, 1000);
			}
			else if(rand == 2) {
				robotHit3.setAlpha(1);
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						robotIdle.setAlpha(1);
						robotHit3.setAlpha(0);
						animationRunning = false;
					}
				}, 600);
			}
		}
	}
	
	
}
