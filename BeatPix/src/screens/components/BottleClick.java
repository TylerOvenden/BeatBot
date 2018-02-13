package screens.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import gui.components.Action;
import gui.userInterfaces.FullFunctionScreen;

public class BottleClick {

	FullFunctionScreen parentScreen;
	ArrayList<Bottle> bottleStack;
	private Bottle bottleOnSill;
	
	Timer time; int count;
	
	
	public BottleClick(FullFunctionScreen parentScreen) {
		this.parentScreen = parentScreen;
		bottleStack = new ArrayList<Bottle>();
		createBottleOnSill();
		addObjects();
	}

	public void addObjects() {
		parentScreen.addObject(getBottleOnSill());
		for(Bottle b: bottleStack) {
			parentScreen.addObject(b);
		}
	}
	public void removeObjects() {
		parentScreen.remove(getBottleOnSill());
		for(Bottle b: bottleStack) {
			parentScreen.remove(b);
		}
	}
	
	public void createBottleOnSill() {
		bottleOnSill = new Bottle(400,
										120,
											50, 
												50, 
													"resources\\backgrounds\\bottle.png");
		getBottleOnSill().setAction(new Action() {
			public void act() {
				time = new Timer(); 
				
				bottleShake();
				bottleFlyOff();
				
				getBottleOnSill().setAction(new Action() {
					public void act() {
						
					}
				});
				bottleStack.add(bottleOnSill);
				insertionSortBottleStack();
				redrawBottleStack();
				
				time.scheduleAtFixedRate(new TimerTask() {
					
					public void run() {
						if(getBottleOnSill().nextSequence()) {
						}else {
							time.cancel();

							createBottleOnSill();
							parentScreen.addObject(getBottleOnSill());
						}
					}
				}, 0, 10);
			}
		});
		getBottleOnSill().setEnabled(true);
	}
	
	public void insertionSortBottleStack(){
        Bottle temp;
        for (int i = 1; i < bottleStack.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(bottleStack.get(j).getFinalY() > bottleStack.get(j-1).getFinalY()){
                    temp = bottleStack.get(j);
                    bottleStack.set(j, bottleStack.get(j-1));
                    bottleStack.set(j-1,temp);
                }
            }
        }
    }
	public void bottleShake() {
		
		for(int i = 0; i < 20; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_UP));
			getBottleOnSill().addSequence(temp, 2);
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_DOWN));
			getBottleOnSill().addSequence(temp, 2);
		}
	}
	
	public void bottleFlyOff() {
		int velocityX = (int) (Math.random()*10+1);
		int velocityY = 30;
		int positionX = getBottleOnSill().getX();
		int positionY = getBottleOnSill().getY();
		//launched off with inital y velocity
		for(int i = 0; i < 20; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_UP));
			getBottleOnSill().addSequence(temp, velocityY);
			positionY -= velocityY;
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_LEFT));
			getBottleOnSill().addSequence(temp, velocityX);
			positionX += velocityX;
			velocityY -= 6;
			if(velocityY < 1) {
				break;
			}
		}
		//peak parabola, falling back down
		for(int i = 0; i < 100; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_DOWN));
			getBottleOnSill().addSequence(temp, Math.abs(velocityY));
			positionY -= velocityY;
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_LEFT));
			getBottleOnSill().addSequence(temp, velocityX);
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
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_UP));
			getBottleOnSill().addSequence(temp, 5);
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_DOWN));
			getBottleOnSill().addSequence(temp, 5);
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_LEFT));
			getBottleOnSill().addSequence(temp, randomXVelocity);
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_DOWN));
			getBottleOnSill().addSequence(temp, randomYVelocity);
			positionY -= randomYVelocity;
		}
		getBottleOnSill().setFinalY(positionY);
	}
	
	public void redrawBottleStack() {
		for(Bottle b: bottleStack) {
			parentScreen.remove(b);
		}
		for(Bottle b: bottleStack) {
			parentScreen.addObject(b);
		}
	}

	public Bottle getBottleOnSill() {
		return bottleOnSill;
	}
	
}
