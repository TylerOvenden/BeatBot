package screens.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import gui.components.Action;
import gui.userInterfaces.FullFunctionScreen;

public class BottleClick {

	FullFunctionScreen parentScreen;
	ArrayList<ImageButton> bottleStack;
	Bottle bottleOnSill;
	
	Timer time; int count;
	
	
	public BottleClick(FullFunctionScreen parentScreen) {
		this.parentScreen = parentScreen;
		bottleStack = new ArrayList<ImageButton>();
		createBottleOnSill();
		addObjects();
	}

	public void addObjects() {
		parentScreen.addObject(bottleOnSill);
	}
	
	public void createBottleOnSill() {
		bottleOnSill = new Bottle(400,
											120,
												50, 
													50, 
														"resources\\backgrounds\\bottle.png");
		bottleOnSill.setAction(new Action() {
			public void act() {
				time = new Timer(); 
				
				bottleShake();
				bottleFlyOff();
				
				time.scheduleAtFixedRate(new TimerTask() {
					
					public void run() {
						if(bottleOnSill.nextSequence()) {
							
						}else {
							System.out.println("hello");
							time.cancel();
							bottleStack.add(bottleOnSill);
							bottleOnSill.setAction(new Action() {
								public void act() {
								}
							});

							parentScreen.remove(bottleOnSill);
							redrawBottleStack();
							createBottleOnSill();
							parentScreen.addObject(bottleOnSill);
						}
					}
				}, 0, 10);
			}
		});
		bottleOnSill.setEnabled(true);
	}
	
	public void bottleShake() {
		
		for(int i = 0; i < 20; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_UP));
			bottleOnSill.addSequence(temp, 2);
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_DOWN));
			bottleOnSill.addSequence(temp, 2);
		}
	}
	
	public void bottleFlyOff() {
		int velocityX = (int) (Math.random()*10+1);
		int velocityY = 30;
		int positionX = bottleOnSill.getX();
		int positionY = bottleOnSill.getY();
		//launched off with inital y velocity
		for(int i = 0; i < 20; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_UP));
			bottleOnSill.addSequence(temp, velocityY);
			positionY -= velocityY;
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_LEFT));
			bottleOnSill.addSequence(temp, velocityX);
			positionX += velocityX;
			velocityY -= 6;
			if(velocityY < 1) {
				break;
			}
		}
		//peak parabola, falling back down
		for(int i = 0; i < 100; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_DOWN));
			bottleOnSill.addSequence(temp, Math.abs(velocityY));
			positionY -= velocityY;
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_LEFT));
			bottleOnSill.addSequence(temp, velocityX);
			velocityY -= 6;
			positionX += velocityX;
			if(positionY > 400) {
				break;
			}
		}
		//random last tumble
		int randomTumbleAmount = (int) (Math.random()*10 + 2);
		int randomXVelocity = (int) (Math.random()*3 + 1);
		int randomYVelocity = (int) (Math.random()*3 + 1);
		for(int i = 0; i < randomTumbleAmount; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_UP));
			bottleOnSill.addSequence(temp, 5);
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_DOWN));
			bottleOnSill.addSequence(temp, 5);
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_LEFT));
			bottleOnSill.addSequence(temp, randomXVelocity);
			temp = new ArrayList<Integer>(Arrays.asList(bottleOnSill.BOTTLE_DOWN));
			bottleOnSill.addSequence(temp, randomYVelocity);
		}
	}
	
	public void redrawBottleStack() {
		for(ImageButton b: bottleStack) {
					parentScreen.remove(b);
		}
		for(ImageButton b: bottleStack) {
			for(int i = 0; i < 100; i++) {
				if(b.getY() == 400+i) {
					parentScreen.addObject(b);
				}
			}
		}
	}
	
}
