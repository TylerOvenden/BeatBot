package mainGame.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;
import mainGame.components.interfaces.JustinTimingInterface;

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
		// TODO Auto-generated method stub
		
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
