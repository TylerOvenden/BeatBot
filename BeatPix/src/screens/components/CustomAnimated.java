package screens.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gui.components.Component;

public class CustomAnimated extends Component {

	private ArrayList<BufferedImage> frames; //images displayed
	private ArrayList<Integer> times; //the time each image is displayed

	
	public CustomAnimated(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub

	}
	
	public void addSequence(String originalImgageAddress, ArrayList<Integer> times, int x, int y, int w, int h,
			int n) {
		BufferedImage originalImgage;
		try {
			originalImgage = ImageIO.read(new File(originalImgageAddress));
			for(int i = 0; i < n; i++){
				addFrame(originalImgage.getSubimage(x+w*i, y, w, h),times.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addFrame(BufferedImage image, Integer time){
		frames.add(image);
		this.times.add(time);
	}

}
