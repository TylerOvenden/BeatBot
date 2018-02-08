package highscore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.Song;

public class TempSongSelect extends FullFunctionScreen {

	private ArrayList<Song> s;
	
	public TempSongSelect(int width, int height) {
		super(width, height);
		for(int i=0;i<new File("resources/realMaps").listFiles().length;i++) {
			System.out.println("resources/realMaps/"+new File("resources/realMaps").listFiles()[i].getName());
			s.add(new Song("resources/realMaps/"+new File("resources/realMaps").listFiles()[i].getName()));
			
		}
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub

	}

}
