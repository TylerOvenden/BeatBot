package highscore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.Song;
import mainGame.screens.GameScreen;

public class TempSongSelect extends FullFunctionScreen {

	private ArrayList<Song> s;
	
	public TempSongSelect(int width, int height) throws IOException {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		s=new ArrayList<Song>();
		for(int i=0;i<new File("resources/realMaps").listFiles().length;i++) {
			s.add(new Song(new File("resources/realMaps").listFiles()[i].getPath()));
		}
		Button temp;
		for(int i=0;i<s.size();i++) {
			int tempint=i;
			temp=new Button(0,20*i+40,300,20,new File("resources/realMaps").listFiles()[i].getName(),new Action() {
				@Override
				public void act() {
					MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),s.get(tempint),"resources/sample_bg.gif"));
					
				}
			});
			viewObjects.add(temp);
		}
	}

}
