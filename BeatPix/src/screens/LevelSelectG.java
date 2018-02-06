package screens;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.Song;
import mainGame.screens.GameScreen;
import screens.components.ImageButton;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class LevelSelectG extends FullFunctionScreen{

	private static final long serialVersionUID = 6265786684466337399L;
	public ArrayList<ImageButton> buttons;
	public ArrayList<ImageButton> unseenButtons;
	private Graphic background;
	
	public LevelSelectG(int width, int height) {
		super(width, height);
		}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		background = updateBackground("resources\\mop.png");
		viewObjects.add(background);
		unseenButtons= new ArrayList<ImageButton>();
		ImageIcon icon = new ImageIcon("resources\\tester.jpg");
		buttons = new ArrayList<ImageButton>();
		for(int i=0; i<5; i++) {
/*P D*/		buttons.add(new ImageButton( 180*(-i-1)+getWidth()-10, 80*(i+1) + getHeight()-580, icon.getIconWidth(), 100 ,"resources\\tester.jpg"));
				
		
		
		}
		buttons.get(2).loadImages("resources\\tester1.jpg", buttons.get(2).getWidth()+25, buttons.get(2).getHeight()+25);
		buttons.get(2).setAction(new Action() {
			public void act(){
				
				Test.test.setScreen(new GameScreen(getWidth(),getHeight(),new Song("resources\\DreadnoughtMastermind(xi+nora2r).csv")));
			}
	});
		buttons.get(2).setEnabled(true);
		
		ImageButton left = new ImageButton( getWidth()-900, getHeight()-300, icon.getIconWidth(), 100 ,"resources\\LeftArrow-small.jpg");
		viewObjects.add(left);
		left.setEnabled(true);

		left.setAction(new Action() {
			public void act(){
				
				System.out.println("asd");
				
				unseenButtons.add(buttons.get(4));
				System.out.println("asd");
				buttons.set(4, buttons.get(3));
				buttons.set(3, buttons.get(2));
				buttons.set(2, buttons.get(1));
				buttons.set(1, buttons.get(0));
				buttons.set(0, unseenButtons.get(0));
				unseenButtons.remove(0);
				for(int i=0; i<5; i++) {
					buttons.get(i).setX(180*(-i-1)+getWidth()-10);
					buttons.get(i).setY(80*(i+1) + getHeight()-580);
				}
				buttons.get(2).setEnabled(true);
				buttons.get(2).loadImages("resources\\tester1.jpg", buttons.get(2).getWidth()+25, buttons.get(2).getHeight()+25);
				
				buttons.get(3).setEnabled(false);
				buttons.get(3).loadImages("resources\\tester2.jpg", buttons.get(3).getWidth()-25, buttons.get(3).getHeight()-25);
				
			}
			
	});
		
		ImageButton right = new ImageButton( getWidth()-200, getHeight()-300, icon.getIconWidth(), 100 ,"resources\\rightArrow-small.jpg");
		right.setEnabled(true);
		viewObjects.add(right);
		
		right.setAction(new Action() {
			public void act(){
				System.out.println("asd");
				unseenButtons.add(buttons.get(0));
				System.out.println("asd");
				buttons.set(0, buttons.get(1));
				buttons.set(1, buttons.get(2));
				buttons.set(2, buttons.get(3));
				buttons.set(3, buttons.get(4));
				buttons.set(4, unseenButtons.get(unseenButtons.size()-1));
				unseenButtons.remove(unseenButtons.size()-1);
				for(int i=0; i<5; i++) {
					buttons.get(i).setX(180*(-i-1)+getWidth()-10);
					buttons.get(i).setY(80*(i+1) + getHeight()-580);
				}
				buttons.get(2).setEnabled(true);
				buttons.get(2).loadImages("resources\\tester1.jpg", buttons.get(2).getWidth()+25, buttons.get(2).getHeight()+25);
				
				buttons.get(1).setEnabled(false);
				buttons.get(1).loadImages("resources\\tester2.jpg", buttons.get(1).getWidth()-25, buttons.get(1).getHeight()-25);
				
			}
			
	});
		
		for(int i=0; i<5; i++) {
			viewObjects.add(buttons.get(i));
		}
	}
	
	private Graphic updateBackground(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; // 0 for either will us e original ima ge size/width 
		int x = 0; int y = 0;
		if(background != null) {
			x = background.getX(); y = background.getY();
		}
		w = getWidth();
		//GUIApp scales the height last
/*needs fixing*/h = (int) ((getWidth()/icon.getIconWidth())*icon.getIconHeight()+100); //makes the width of background always match the screen
		return new Graphic(x,y,w,h,path);
	}
	// change to game screen calling with the appropriate songs
}
