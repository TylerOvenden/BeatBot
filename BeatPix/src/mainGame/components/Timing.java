package mainGame.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;
import mainGame.components.interfaces.JustinTimingInterface;
import mainGame.screens.GameScreen;

public class Timing extends Component implements JustinTimingInterface {
	
	private String img="";

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
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<40) {
			changeImg("resources/great.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<73) {
			changeImg("resources/good.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			return ;
		}
	}

	@Override
	public void calculateFirstAccuracy(Holdstroke stroke) {
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<16) {
			changeImg("resources/perfect.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<40) {
			changeImg("resources/great.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<73) {
			changeImg("resources/good.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			return ;
		}
		
	}

	@Override
	public void calculateEndAccuracy(Holdstroke stroke) {
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<16) {
			changeImg("resources/perfect.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<40) {
			changeImg("resources/great.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<73) {
			changeImg("resources/good.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<103) {
			changeImg("resources/ok.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<127) {
			changeImg("resources/bad.png");
			update();
			return ;
		}
		if(Math.abs(GameScreen.timePass()-stroke.getClickTime())<164) {
			changeImg("resources/miss.png");
			update();
			return ;
		}
		
	}

	@Override
	public void missAccuracy() {
		changeImg("resources/miss.png");
		update();
	}
}
