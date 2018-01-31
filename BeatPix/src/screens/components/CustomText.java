package screens.components;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.Component;
import gui.components.Graphic;

public class CustomText extends Component {

	private String text;
	private ArrayList<Graphic> images;
	
	public CustomText(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void update(Graphics2D g) {
		for(int i = 0; i < text.length(); i++) {
			images.get(i).loadImages(letterImageLocation(text.substring(i, i+1)), i*100, 100);
		}
	}
	
	public String letterImageLocation(String letter) {
		return "resources\\customfont\\"+letter+".gif";
	}

}
