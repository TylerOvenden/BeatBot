package screens.components;

import java.util.Timer;
import java.util.TimerTask;

import gui.components.Graphic;
import gui.userInterfaces.FullFunctionScreen;
import screens.interfaces.Options;

public class Explosion {

	private int x;
	private int y;
	private int w;
	private int h;
	
	private Graphic explosion;
	private Options parentScreen;
	private Timer timer;
	
	private int currentExplosion;
	
	public Explosion(int x, int y, int w, int h, Options parentScreen2) {
		this.x = x; this.y = y; this.w = w; this.h = h; this.parentScreen = parentScreen2;
		
		currentExplosion = 1;
	}

	public void explode(int currentExplosion) {
		if(currentExplosion < 7) {
			explosion = new Graphic(x,y,w,h,"resources\\backgrounds\\explosion\\explode"+currentExplosion+".png");
			parentScreen.addObject(explosion);
			
			timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					parentScreen.remove(explosion);
					explode(currentExplosion+1);
				}
			}, 30);
		}
	}
	
	

}
