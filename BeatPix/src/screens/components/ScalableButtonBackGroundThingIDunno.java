package screens.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.Component;

public class ScalableButtonBackGroundThingIDunno extends Component {

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
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	
	private BufferedImage image;
	private boolean loadedImages;
	
	private static String testFile = "resources\\ui\\buttons\\test";
	
	private String[][] gridOfImages;
	
	public static void main(String[] args) {
		ScalableButtonBackGroundThingIDunno a = new ScalableButtonBackGroundThingIDunno(0,0,120,120);
	}
	
	public ScalableButtonBackGroundThingIDunno(int x, int y, int w, int h) {
		super(x, y, w, h);
		
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
		
		for(int row = 0; row < gridOfImages.length; row++) {
			String printLine = "";
			for(int column = 0; column < gridOfImages[row].length; column++) {
				printLine += " "+gridOfImages[row][column];
			}
			System.out.println(printLine);
		}
	}

	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub

	}
	
	private void loadImages(String imageLocation, double scale) {
		try{
			//get the full-size image
			ImageIcon icon = new ImageIcon(imageLocation);
	
			int newWidth = (int) (icon.getIconWidth() * scale);
			int newHeight = (int) (icon.getIconHeight() * scale);
			
			image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(icon.getImage(), 0, 0, null);
			
			AffineTransform scaleT = new AffineTransform();
			scaleT.scale(scale, scale);
			AffineTransformOp scaleOp = new AffineTransformOp(scaleT, AffineTransformOp.TYPE_BILINEAR);
			image = scaleOp.filter(image,new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB));
			
			loadedImages = true;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
