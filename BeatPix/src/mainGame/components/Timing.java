package mainGame.components;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;

import gui.interfaces.Visible;
import mainGame.components.interfaces.JustinTimingInterface;
import mainGame.components.interfaces.Stroke;
import mainGame.screens.GameScreen;
import screens.interfaces.robotActionInterface;

public class Timing extends Component implements JustinTimingInterface,robotActionInterface {
	
	private String img="";
	private float lastTiming=0;
	public static final String[] tim= {"resources/perfect.png","resources/great.png","resources/good.png","resources/ok.png","resources/bad.png","resources/miss.png"};
	public static final double[] times= {1,.95,.66,.5,.33,0};
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
		checkAcc((Stroke) stroke,true);
	}

	public float getLastTiming() {
		return lastTiming;
	}

	@Override
	public void calculateFirstAccuracy(Holdstroke stroke) {
		checkAcc((Stroke) stroke,true);
		
	}

	@Override
	public void calculateEndAccuracy(Holdstroke stroke) {
		checkAcc((Stroke) stroke,false);
		
	}
	
	/**
	 * @author Justin Yau
	 * @author Steven Li
	 */
	public void checkAcc(Stroke stroke, boolean start) {
		int goal = GameScreen.columnY + GameScreen.columnHeight;
		int difference = goal - stroke.getBottomPosition();
		if(!start) {
			difference = goal - stroke.getY();
		}
		for(int i=0;i<6;i++) {
			if(i==5) {
				calculations(0,"resources/miss.png");
				break;
			}
			if(Math.abs(difference)<i*5+5) {
				calculations(times[i],tim[i]);
				break;
			}
		}
		
		/*if(Math.abs(difference)< 5) {
			changeImg("resources/perfect.png");
			update();
			GameScreen.game.calcAcc(1);
			GameScreen.game.calcScore(1);
			GameScreen.game.calcCombo(false);
			return ;
		}
		if(Math.abs(difference)< 10) {
			changeImg("resources/great.png");
			update();
			GameScreen.game.calcAcc(.95);
			GameScreen.game.calcScore(.95);
			GameScreen.game.calcCombo(false);
			return ;
		}
		if(Math.abs(difference)< 20) {
			changeImg("resources/good.png");
			update();
			GameScreen.game.calcAcc(.66);
			GameScreen.game.calcScore(.66);
			GameScreen.game.calcCombo(false);
			return ;
		}
		if(Math.abs(difference)< 27) {
			changeImg("resources/ok.png");
			update();
			GameScreen.game.calcAcc(.5);
			GameScreen.game.calcScore(.5);
			GameScreen.game.calcCombo(false);
			return ;
		}
		if(Math.abs(difference)< 40) {
			changeImg("resources/bad.png");
			update();
			GameScreen.game.calcAcc(.33);
			GameScreen.game.calcScore(.33);
			GameScreen.game.calcCombo(false);
			return ;
		}
		calculations(0,"resources/miss.png");
		return ;*/
	}
	
	public void calculations(double score,String image) {
		changeImg(image);
		update();
		GameScreen.game.calcAcc(score);
		GameScreen.game.calcScore(score);
		GameScreen.game.fightScene.hit(score);
		if(score>0) {
			GameScreen.game.calcCombo(false);
		}else {
			GameScreen.game.calcCombo(true);
		}
	}
	
	@Override
	public void missAccuracy() {
		changeImg("resources/miss.png");
		update();
		GameScreen.game.calcAcc(0);
		GameScreen.game.calcCombo(true);
		//System.out.println("b");
	}

	@Override
	public boolean isHit() {
		return false;
	}

}
