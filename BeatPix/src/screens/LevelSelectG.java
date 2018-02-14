package screens;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.Song;
import mainGame.components.SongBundle;
import mainGame.components.interfaces.SongInterface;
import mainGame.screens.GameScreen;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.CustomText;
import mainGame.components.Song;
import mainGame.components.SongBundle;
import mainGame.screens.GameScreen;
import screens.components.ImageButton;
import shop.ShopScreen;

import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import screens.components.ImageButton;

public class LevelSelectG extends FullFunctionScreen{

	private static final long serialVersionUID = 6265786684466337399L;
	public ArrayList<ImageButton> buttons;
	public ArrayList<ImageButton> unseenButtons;
	private Graphic background;

	private CustomText displaysong;
	private int count=0;
	
	public LevelSelectG(int width, int height) throws IOException{
		super(width, height);
		}

	
	public void initAllObjects(List<Visible> viewObjects) {
		Button temp;
		background = updateBackground("resources\\mop.png");
		viewObjects.add(background);
		
		Button back = new Button(500, 40, 100, 80, "Back", new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(MainGUI.mainMenu);
			}
		});
		viewObjects.add(back);
		
		unseenButtons= new ArrayList<ImageButton>();
		ImageIcon icon = new ImageIcon("resources\\tester.jpg");
		buttons = new ArrayList<ImageButton>();
	/*
		for(int i=0; i<MainGUI.shop.getSongs().size(); i++) {

 		buttons.add(new ImageButton( 180*(-i-1)+getWidth()-10, 80*(i+1) + getHeight()-580, icon.getIconWidth(), 100 ,"resources\\tester.jpg"));

			
 		buttons.add(new ImageButton( 180*(-i-1)+getWidth()-10, 80*(i+1) + getHeight()-580, icon.getIconWidth(), 100 ,"resources\\tester.jpg"));

			//ShopScreen.getSongs();

			buttons.get(i).setAction(new Action() {
				public void act(){
		
					Test.test.setScreen(new GameScreen(getWidth(),getHeight(),MainGUI.shop.getSongs().get(i)));
				}
			});
		
		}
	*/
		int count = 0;
		for(int i=0;i<MainGUI.test.songs.size();i++) {
			int tempint=i;
			SongBundle songBundle = MainGUI.test.songs.get(i);
			if(!(MainGUI.test.songs.get(tempint).isUnlock())) {
				File[] songList = new File(songBundle.getPath()).listFiles(new FileFilter() {
		            @Override
		            public boolean accept(File pathname) {
		                return pathname.getName().toLowerCase().endsWith(".csv") 
		                    || pathname.isDirectory();
		            }
		        });
				for(int j = 0; j < songList.length; j++) {
					Song song = new Song(songList[j].getPath());
					
					temp=new Button(250,20*count,300,185, songList[j].getName() ,new Action() {
						@Override
						public void act() {
							MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),song,"resources/sample_bg.gif"));
							
						}
					});
					//displaysong = new CustomText(250,20*count,300,185,""+songList[j].getName(),true); 
					count++;
					viewObjects.add(temp);
					//viewObjects.add(displaysong);
				}
			}
		}
		this.count = count;
	//	buttons.get(2).loadImages("resources\\tester1.jpg", buttons.get(2).getWidth()+25, buttons.get(2).getHeight()+25);
		//buttons.get(2).setAction(new Action() {
		//	public void act(){

		//		Test.test.setScreen(new GameScreen(getWidth(),getHeight(),audio));

				
			//	MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),new Song("resources\\DreadnoughtMastermind(xi+nora2r).csv")));

			//}
//	});
		//buttons.get(2).setEnabled(true);
		
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
		
		for(int i=0; i<buttons.size(); i++) {
			viewObjects.add(buttons.get(i));
		}
	}
	
	private Graphic updateBackground(String path) {
		ImageIcon icon = new ImageIcon(path);
		int w; int h; // 0 for either will us e original image size/width 
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
	private void moveLeft() {
		try {
		
	} catch (IndexOutOfBoundsException e) {}
	}
}
