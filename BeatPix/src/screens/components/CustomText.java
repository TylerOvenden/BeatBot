package screens.components;

import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Component;

/**
 * Creates Custom Text that scales with size
 * 
 * @author Steven
 *
 */

public class CustomText extends Component {
	
	String text;
	int w;
	int h;
	boolean keepScale;

	/**
	 * 
	 * @param x - x
	 * @param y - y
	 * @param w - w
	 * @param h - h
	 * @param text - Text
	 */
	public CustomText(int x, int y, int w, int h, String text) {
		super(x, y, w, h);
		this.text=text.toUpperCase();
		this.w=w;
		this.h=h;
		keepScale=true;
		update();
	}
	
	/**
	 * 
	 * @param x - x
	 * @param y - y
	 * @param w - w
	 * @param h - h
	 * @param text - Text
	 * @param keepScale - Keep height scale or not
	 */
	public CustomText(int x, int y, int w, int h, String text,boolean keepScale) {
		super(x, y, w, h);
		this.text=text.toUpperCase();
		this.w=w;
		this.h=h;
		this.keepScale=keepScale;
		update();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		update();
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
		update();
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
		update();
	}

	public boolean isKeepScale() {
		return keepScale;
	}

	public void setKeepScale(boolean keepScale) {
		this.keepScale = keepScale;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		super.clear();
		for(int i=0;i<text.length();i++) {
			try {
				ImageIcon icon = new ImageIcon("resources/text/"+text.substring(i,i+1)+".png");
				//System.out.println("resources/text/"+text.substring(i,i+1)+".png");
				Image img = icon.getImage();
				Image newimg;
				int newW=w-(text.length()*5);
				if(keepScale) {
					newimg = img.getScaledInstance(newW/text.length(),h/text.length(),java.awt.Image.SCALE_SMOOTH);
				}else {
					newimg = img.getScaledInstance(newW/text.length(),h,java.awt.Image.SCALE_SMOOTH);
				}
				ImageIcon newIcon = new ImageIcon(newimg);
				g.drawImage(newIcon.getImage(), i*w/text.length(), 0, null);
					
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

}
