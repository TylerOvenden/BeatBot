package mainGame.components;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Scoring {
	int score = 1000;
	int offset;
	String img;
	public static void main(String[] args) {


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
		String img1="";
		String img2="";
		String img3="";
		String img4="";
		String img5="";
		String img6="";
		String scoreStr = String.valueOf(score);

		if(scoreStr.length()==3) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,0)+ ".png";
			img3="resources/score-"+scoreStr.substring(2)+ ".png";
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
		if(scoreStr.length()==4) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,0)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 0, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 100, 0, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 200, 0, null);			
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 300, 0, null);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(scoreStr.length()==5) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,0)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3,4)+ ".png";
			img5="resources/score-"+scoreStr.substring(4)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 0, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 100, 0, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 200, 0, null);			
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 300, 0, null);
				icon = new ImageIcon(img5);
				g.drawImage(icon.getImage(), 400, 0, null);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		if(scoreStr.length()==6) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,0)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3,4)+ ".png";
			img5="resources/score-"+scoreStr.substring(4,5)+ ".png";
			img6="resources/score-"+scoreStr.substring(5)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 0, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 100, 0, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 200, 0, null);			
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 300, 0, null);
				icon = new ImageIcon(img5);
				g.drawImage(icon.getImage(), 400, 0, null);
				icon = new ImageIcon(img6);
				g.drawImage(icon.getImage(), 500, 0, null);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			 
		}



			
		}
		
		
		
	}
