package mainGame.components;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import gui.components.Component;

public class KeystrokeIndicatorTest extends Component {
	
	private String path;
	private boolean isHit;
	

	public void setHit(boolean isHit) {
		this.isHit = isHit;
		update();
	}

	public KeystrokeIndicatorTest(int x, int y,int w,int h, String p) {
		super(x, y, w, h);
		this.path=p;
		isHit=false;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
		ImageIcon icon;
		icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image newimg;
		if(isHit) {
			newimg = img.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
			
		}else {
			newimg=img;
		}
		ImageIcon newIcon = new ImageIcon(newimg);
		g.drawImage(newIcon.getImage(), 0, 0, null);	
	}

}
