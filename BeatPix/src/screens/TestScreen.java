package screens;

import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ScalablePixelBack;

public class TestScreen extends FullFunctionScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489101954351168987L;

	public TestScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		/*viewObjects.add(new Button(50, 50, 200, 200, "Yeah", new Action() {
			
			@Override
			public void act() {
				// TODO Auto-generated method stub
				
			}
		}));*/
		
		//viewObjects.add( new ScalablePixelBack(0, 0, getWidth(), getHeight(), 0));
		
		viewObjects.add(new OptionsPopUp(0, 0, getWidth(), getHeight()));
	}

}
