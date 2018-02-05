package mainGame.components;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Component;
import mainGame.components.interfaces.JustinTimingInterface;
import mainGame.screens.GameScreen;
import mainGame.screens.MainScreen;

public class Timing2 extends Component implements Runnable,JustinTimingInterface {
	
	String imgString;
	float trans;
	int scale;
	private int w;
	private int h;

	public Timing2(int x, int y, int w, int h, String img) {
		super(x, y, w, h);
		this.w=w;
		this.h=h;
		this.imgString=img;
		trans=(float) .001;
		scale=1;
		Thread a=new Thread(this);
		a.start();
		update();
	}

	@Override
	public void run() {
		try {
			for(int i=0;i<30;i++) {
				Thread.sleep(17);
				trans+=0.032;
				scale+=4;
				update();
			}
		}catch(Exception e) {
			
		}

	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
		try {
			ImageIcon icon = new ImageIcon(imgString);
			Image img = icon.getImage();
			float alpha = trans;
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			g.setComposite(ac);
			Image newimg;
			newimg = img.getScaledInstance(scale,scale,java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			g.drawImage(newIcon.getImage(), 0, 0, null);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void calculateAccuracy(Keystroke stroke) {
		GameScreen.game.getTimings().add(new Timing2(100,100,100,100,""));
		
	}

	@Override
	public void calculateFirstAccuracy(Holdstroke stroke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateEndAccuracy(Holdstroke stroke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void missAccuracy() {
		// TODO Auto-generated method stub
		
	}
	
	

}
