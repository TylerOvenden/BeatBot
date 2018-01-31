package mainGame.components;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Component;

public class Accuracy extends Component{
	
	private int acc;

	public Accuracy(int x, int y, int w, int h) {
		super(x, y, w, h);
		acc=100;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
		String img1="";
		String img2="";
		String img3="";
		if(acc==100) {
			img1="resources/Separated%20font/A_Transparent.gif";
			img2="resources/score-0.png";
			img3="resources/score-0.png";
		}else {
			img1="resources/score-"+0+".png";
			img2="resources/score-"+((acc-(acc%10))/10)+".png";
			img3="resources/score-"+(acc%10)+".png";
		}
		try {
			ImageIcon icon = new ImageIcon(img1);
			g.drawImage(icon.getImage(), 0, 0, null);
			icon = new ImageIcon(img2);
			g.drawImage(icon.getImage(), 100, 0, null);
			icon = new ImageIcon(img3);
			g.drawImage(icon.getImage(), 200, 0, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void setAcc(int acc) {
		this.acc=acc;
		update();
	}

}
