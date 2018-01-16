package mainGame.components;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Component;

public class Timing extends Component {
	
	public final String perfect="resources/good.png";

	public Timing(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Graphics2D g) {
		try {
			ImageIcon icon = new ImageIcon(perfect);
			g.drawImage(icon.getImage(), 100, 100, null);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
