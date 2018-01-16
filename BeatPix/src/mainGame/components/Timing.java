package mainGame.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;

public class Timing extends Component {
	
	public final String good="resources/good.png";

	public Timing(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Graphics2D g) {
		try {
			ImageIcon icon = new ImageIcon(good);
			g.drawImage(icon.getImage(), 0, 0, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
