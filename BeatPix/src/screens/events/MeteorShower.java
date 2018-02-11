package screens.events;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;
import gui.components.Component;
import gui.components.Graphic;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.screens.MainScreen;
import screens.MainMenuScreenG;
import screens.StartScreenG;

public class MeteorShower {

	Timer time;
	StartScreenG s;

	int timeCount = 0;
	public MeteorShower(StartScreenG screen) {
		this.s = screen;
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() { timeCount++;
				
			
			
			
				if(timeCount > 3000 && timeCount < 6500) { //screenshake at 3s
					screenShake();
				}
				if(timeCount == 3300) {
					System.out.println("method called");
					spaceShipFalling();
				}
				if(timeCount > 7000) {
					spaceShipCrash();
				}
				if(timeCount == 8000) {
					time.cancel();
					afterFlash();
				}
			}
		}, 0, 1);
	}
	
	public void screenShake() {
		if(StartScreenG.background.getX()== 0) {
			if(Math.random() < 0.5) {
				StartScreenG.background.setX(-3);
			}else {
				StartScreenG.background.setX(-2);
			}
		}else {
			StartScreenG.background.setX(0);
		}
		if(StartScreenG.background.getY()== -2) {
			if(Math.random() < 0.5) {
				StartScreenG.background.setY(-3);
			}else {
				StartScreenG.background.setY(-4);
			}
		}else {
			StartScreenG.background.setY(-2);
		}
	}

	AnimatedComponent theShip;
	Graphic startTop; int currentCloud = 1;
	boolean spaceShipFallStart; Timer personal;
	public void spaceShipFalling() {
		if(!spaceShipFallStart) {
			spaceShipFallStart = true;
			theShip = new AnimatedComponent(500, 0, 150, 150);
			theShip.addSequence("resources\\backgrounds\\fireball_0.png", 8, 0, 0, 64, 54, 8);
			
			ImageIcon icon = new ImageIcon("resources\\backgrounds\\start_top.jpg");
			startTop = new Graphic(s.getWidth(),icon.getIconHeight(),"resources\\backgrounds\\start_top.jpg");
			
			Thread run = new Thread(theShip);
			run.start();
			System.out.println("Object added");
			s.addObject(theShip);
			s.addObject(startTop);
		}
		
		personal = new Timer();
		personal.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				theShip.setY(theShip.getY() + 8);
				theShip.setX(theShip.getX() - 1);
			}
		}, 0, 40);
	}
	
	Component crashFlash; boolean crashActivated;
	public void spaceShipCrash() {
		if(!crashActivated) {
			crashFlash = new Component(0, 0, 960, 540) {
				public void update(Graphics2D g) {
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, s.getWidth(), s.getHeight());
				}
			};
			crashFlash.setAlpha(0.5f);
			crashFlash.update();
			s.addObject(crashFlash);
			crashActivated = true;
		}
		
		if(timeCount > 7000 && timeCount < 7200) {
			if(crashFlash.getAlpha() == 0.5f) {
				crashFlash.setAlpha(1.0f);
			}else {
				crashFlash.setAlpha(0.5f);
			}
		}else if(timeCount == 7200){
			crashFlash.setAlpha(1.0f);
		}
	}
	
	public void afterFlash() {
		s.scrollIn();
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(crashFlash.getAlpha() > 0.05f) {
					crashFlash.setAlpha(crashFlash.getAlpha() - 0.03f);
				}
				
			}
		}, 0, 16);
	}
	

}
