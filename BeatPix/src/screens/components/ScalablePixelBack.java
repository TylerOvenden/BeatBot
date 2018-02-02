package screens.components;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;

public class ScalablePixelBack extends Component {

	/**
	 * HELLO PEOPLE
	 * 	THIS WILL BE A SCALABLE IMAGE BASED OFF THE BUTTON THAT I COPPED ONLINE
	 * THE BASIC IS THIS:
	 * 	IF WE WANTED THE BUTTON TO BE A DIfFERENT SHAPE(RECT -> SQUARE), SCALING WON"T WORK
	 * IT WILL STRETCH IT, SO TO PREVENT IT
	 * WE HAVE TO RECREATE EACH PART BIT BY BIT BASED OFF THE ORIGNINAL SCALE
	 * 
	 * WE WILL JUST BE MODIFYING THE SIDES
	 * CORNERS WILL BE EASY
	 * SIDES WILL BE ADDED WHEN REQUIRED
	 * 
	 * 
	 * 2/2 - WILL ADD SCALING LATER SO BACKGROUND DOESN'T SEEM SO SMALL
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	
	private static String testFile = "resources\\ui\\buttons\\test";
	
	private String[][] gridOfImages;
	
	public static void main(String[] args) {
		ScalablePixelBack a = new ScalablePixelBack(0,0,120,120);
	}
	
	public ScalablePixelBack(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		w -= w%12; h -= h%12;
		
		//each image is a 12 by 12 pixel square
		int xCount = w/12; int yCount = h/12;
		gridOfImages = new String[yCount][xCount];
		
		for(int row = 0; row < gridOfImages.length; row++) {
			for(int column = 0; column < gridOfImages[row].length; column++) {
				if(row == 0) {
					if(column == 0)
						gridOfImages[row][column] = testFile + "\\topleft.png";
					else if(column == gridOfImages[row].length -1)
						gridOfImages[row][column] = testFile + "\\topright.png";
					else 
						gridOfImages[row][column] = testFile + "\\top.png";
					
				}else if(row == gridOfImages.length - 1) {
					if(column == 0) 
						gridOfImages[row][column] = testFile + "\\bottomleft.png";
					else if(column == gridOfImages[row].length -1)
						gridOfImages[row][column] = testFile + "\\bottomright.png";
					else
						gridOfImages[row][column] = testFile + "\\bottom.png";
				}else {
					if(column == 0)
						gridOfImages[row][column] = testFile + "\\left.png";
					else if(column == gridOfImages[row].length -1)
						gridOfImages[row][column] = testFile + "\\right.png";
					else
						gridOfImages[row][column] = testFile + "\\center.png";
				}
			}
		}
		
		/*for(int row = 0; row < gridOfImages.length; row++) {
			String printLine = "";
			for(int column = 0; column < gridOfImages[row].length; column++) {
				printLine += " "+gridOfImages[row][column];
			}
			System.out.println(printLine);
		}*/
		update();
	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
			try {
				int currentX = 0; int currentY = 0;
				for(int row = 0; row < gridOfImages.length; row++) {
					for(int column = 0; column < gridOfImages[row].length; column++) {
						ImageIcon icon = new ImageIcon(gridOfImages[row][column]);
						//SCALING THE IMAGE
						
						
						
						
						g.drawImage(icon.getImage(),currentX,currentY,null);
/*D*/						currentX+= 12;
						//System.out.println(currentX + " " + currentY);
					}
						currentX = 0;
/*D*/					currentY+=12;
				}
				//System.out.println("I am working inside you");
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
