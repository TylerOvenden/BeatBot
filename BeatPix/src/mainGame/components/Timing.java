package mainGame.components;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;
import gui.interfaces.Visible;
import mainGame.components.interfaces.JustinTimingInterface;
import mainGame.screens.GameScreen;

public class Timing extends Component implements JustinTimingInterface,Runnable {
	
	private String img="";
	private float lastTiming=0;

	public Timing(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
		try {
			ImageIcon icon = new ImageIcon(img);
			g.drawImage(icon.getImage(), 0, 0, null);		
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void changeImg(String type) {
		img=type;
		update();
	}

	@Override
	public void calculateAccuracy(Keystroke stroke) {
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<16) {
			changeImg("resources/perfect.png");
			update();
			GameScreen.game.calcAcc(1);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<40) {
			changeImg("resources/great.png");
			update();
			GameScreen.game.calcAcc(.95);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<73) {
			changeImg("resources/good.png");
			update();
			GameScreen.game.calcAcc(.66);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			GameScreen.game.calcAcc(.5);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			GameScreen.game.calcAcc(.33);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			GameScreen.game.calcAcc(0);
			return ;
		}
	}

	public float getLastTiming() {
		return lastTiming;
	}

	@Override
	public void calculateFirstAccuracy(Holdstroke stroke) {
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<16) {
			changeImg("resources/perfect.png");
			update();
			GameScreen.game.calcAcc(1);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<40) {
			changeImg("resources/great.png");
			update();
			GameScreen.game.calcAcc(.95);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<73) {
			changeImg("resources/good.png");
			update();
			GameScreen.game.calcAcc(.66);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			GameScreen.game.calcAcc(.5);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			GameScreen.game.calcAcc(.33);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getFirstClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			GameScreen.game.calcAcc(0);
			return ;
		}
		
	}

	@Override
	public void calculateEndAccuracy(Holdstroke stroke) {
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<16) {
			changeImg("resources/perfect.png");
			update();
			GameScreen.game.calcAcc(1);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<40) {
			changeImg("resources/great.png");
			update();
			GameScreen.game.calcAcc(.95);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<73) {
			changeImg("resources/good.png");
			update();
			GameScreen.game.calcAcc(.66);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			GameScreen.game.calcAcc(.5);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			GameScreen.game.calcAcc(.33);
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getEndClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			GameScreen.game.calcAcc(0);
			return ;
		}
		
	}

	@Override
	public void missAccuracy() {
		changeImg("resources/miss.png");
		update();
		GameScreen.game.calcAcc(0);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
