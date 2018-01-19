package mainGame.components;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Scoring {
	int score = 100;
	int offset;
	String img;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void scoring() {
		if(offset<16) {
			if(score<500000) {
				score += 10000;
			}	
					score += 15000;
		}
		if(offset<40 && offset>16) {
			score += 5000;
		}
		if(offset>40 && offset<73) {
			score += 1000;
		}
		if(offset>73 && offset<103) {
			score += 100;
		}
		if(offset>103 && offset<127) {
			score -= 200;
		}
		if(offset>127) {
			score -= 2000;
		}
			
	}
	public void display(Graphics2D g) {
		String scoreStr = String.valueOf(score);
		for(int i = 0; i<scoreStr.length();i++) {
			img = "resources/score-"+ scoreStr.substring(i, i+1);
		}
		ImageIcon icon = new ImageIcon(img);
		g.drawImage(icon.getImage(), 0, 0, null);
		
	}
}