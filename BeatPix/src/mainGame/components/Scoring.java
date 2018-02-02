package mainGame.components;

import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import gui.components.Component;

public class Scoring extends Component {
	int score;
	int offset;
	String img;
	public int[] ints = {0, 0, 0, 1, 0, 0};
	
	public Scoring(int x, int y, int w, int h) {
		super(x, y, w, h);
		score = 1000;



 		update();
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


/*	@Override
	public void update(Graphics2D g) {
		String img1="";
		String img2="";
		String img3="";
		String img4="";
		String img5="";
		String img6="";
		String scoreStr = String.valueOf(score);

		if(scoreStr.length()==3) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,2)+ ".png";
			img3="resources/score-"+scoreStr.substring(2)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 300, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 60, 300, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 120, 300, null);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(scoreStr.length()==4) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,2)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 300, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 60, 300, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 120, 300, null);
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 180, 300, null);
	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(scoreStr.length()==5) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,2)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3,4)+ ".png";
			img5="resources/score-"+scoreStr.substring(4)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 300, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 60, 300, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 120, 300, null);
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 180, 300, null);
				icon = new ImageIcon(img5);
				g.drawImage(icon.getImage(), 240, 300, null);
				 
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		if(scoreStr.length()==6) {
			img1="resources/score-"+scoreStr.substring(0,1)+ ".png";
			img2="resources/score-"+scoreStr.substring(1,2)+ ".png";
			img3="resources/score-"+scoreStr.substring(2,3)+ ".png";
			img4="resources/score-"+scoreStr.substring(3,4)+ ".png";
			img5="resources/score-"+scoreStr.substring(4,5)+ ".png";
			img6="resources/score-"+scoreStr.substring(5)+ ".png";
			try {
				ImageIcon icon = new ImageIcon(img1);
				g.drawImage(icon.getImage(), 0, 300, null);
				icon = new ImageIcon(img2);
				g.drawImage(icon.getImage(), 60, 300, null);
				icon = new ImageIcon(img3);
				g.drawImage(icon.getImage(), 120, 300, null);
				icon = new ImageIcon(img4);
				g.drawImage(icon.getImage(), 180, 300, null);
				icon = new ImageIcon(img5);
				g.drawImage(icon.getImage(), 240, 300, null);
				icon = new ImageIcon(img6);
				g.drawImage(icon.getImage(), 300, 300, null);
				}
			catch(Exception e){
				e.printStackTrace();
					}			
			}	   
		} */
	public void update(Graphics2D g) {
		String img1="";
		String img2="";
		String img3="";
		String img4="";
		String img5="";
		String img6="";
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int i : ints)
		{
		    intList.add(i);
		    
		}
		img1="resources/"+ intList.subList(0,1)  + ".png";
		img2="resources/"+ intList.subList(1,2)  + ".png";
		img3="resources/"+ intList.subList(2,3)  + ".png";
		img4="resources/"+ intList.subList(3,4)  + ".png";
		img5="resources/"+ intList.subList(4,5)  + ".png";
		img6="resources/"+ intList.subList(5,6)  + ".png";

		try {
			ImageIcon icon = new ImageIcon(img1);
			g.drawImage(icon.getImage(), 0, 300, null);
			icon = new ImageIcon(img2);
			g.drawImage(icon.getImage(), 60, 300, null);
			icon = new ImageIcon(img3);
			g.drawImage(icon.getImage(), 120, 300, null);
			icon = new ImageIcon(img4);
			g.drawImage(icon.getImage(), 180, 300, null);
			icon = new ImageIcon(img5);
			g.drawImage(icon.getImage(), 240, 300, null);
			icon = new ImageIcon(img6);
			g.drawImage(icon.getImage(), 300, 300, null);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		}	
	

	}
