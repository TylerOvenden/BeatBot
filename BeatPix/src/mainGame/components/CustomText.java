package mainGame.components;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Component;

public class CustomText extends Component {
	
	String text;
	int w;
	int h;
	boolean keepScale;

	public CustomText(int x, int y, int w, int h, String text) {
		super(x, y, w, h);
		this.text=text.toUpperCase();
		this.w=w;
		this.h=h;
		keepScale=true;
	}
	
	public CustomText(int x, int y, int w, int h, String text,boolean keepScale) {
		super(x, y, w, h);
		this.text=text.toUpperCase();
		this.w=w;
		this.h=h;
		this.keepScale=keepScale;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public boolean isKeepScale() {
		return keepScale;
	}

	public void setKeepScale(boolean keepScale) {
		this.keepScale = keepScale;
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
