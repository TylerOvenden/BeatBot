package screens.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import gui.components.Action;
import gui.components.AnimatedComponent;
import gui.components.Graphic;
import gui.userInterfaces.FullFunctionScreen;
import screens.interfaces.Options;

public class BottleClick {

	Options parentScreen;
	ArrayList<Bottle> bottleStack;
	private Bottle bottleOnSill;
	
	Timer time; int count;
	
	
	public BottleClick(Options parentScreen) {
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
				parentScreen.toggleButtons(false);
				bottleShake();
				bottleFlyOff();

				getBottleOnSill().setAction(new Action() {
					public void act() {
						//Graphic explosion = new Graphic(Bottle.getBottle().getMyX()-35, Bottle.getBottle().getMyY(), 100, 100, "resources\\backgrounds\\explode.png");
						//parentScreen.addObject(explosion);
						//Bottle me = BottleClick.this.getBottleOnSill();
						//parentScreen.remove(me);
						//Timer timer = new Timer();
						//AnimatedComponent explosion = new AnimatedComponent(Bottle.getMyX(), Bottle.getMyY(), 50, 50);
						//explosion.addSequence("resources\\backgrounds\\explode.png", 100, 0, 0, 100, 100, 4);
						//timer.scheduleAtFixedRate(new TimerTask() {
						//	int timeC = 100;
						//	public void run() {
						//		timeC --;
						//		if(timeC < 0) {
						//			this.cancel();
									//parentScreen.remove(explosion);
						//		}
						//	}
						//}, 0, 1);
						//parentScreen.addObject(explosion);
						//bottleStack.remove(Bottle.getBottle());
					}
				});
				bottleOnSill.setBottle(getBottleOnSill());
				
				bottleStack.add(bottleOnSill);
				insertionSortBottleStack();
				redrawBottleStack();
				
				time.scheduleAtFixedRate(new TimerTask() {
					
					public void run() {
						if(getBottleOnSill().nextSequence()) {
						}else {
							time.cancel();
							parentScreen.toggleButtons(true);
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
			positionX -= velocityX;
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
			positionX -= velocityX;
			if(positionY > 400) { // <-- this should be the condition for a while and not use the for loop
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
			positionX -= randomXVelocity;
			temp = new ArrayList<Integer>(Arrays.asList(getBottleOnSill().BOTTLE_DOWN));
			getBottleOnSill().addSequence(temp, randomYVelocity);
			positionY -= randomYVelocity;
		}
		getBottleOnSill().setFinalY(positionY);
		getBottleOnSill().setFinalY2(positionY);
		getBottleOnSill().setFinalX2(positionX);
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
