/**
 * Temporary until Yonathan finishes his screen
 * 
 * @author Steven
 */

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
import mainGame.components.interfaces.SongInterface;
import mainGame.screens.GameScreen;

public class TempSongSelect extends FullFunctionScreen {

	
	public TempSongSelect(int width, int height) throws IOException {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		Button temp;
		for(int i=0;i<MainGUI.test.mySongs.size();i++) {
			int tempint=i;
			if((MainGUI.test.mySongs.get(tempint).isUnlock())) {
				temp=new Button(0,20*i+40,300,20,new File("resources/realMaps").listFiles()[i].getName(),new Action() {
					@Override
					public void act() {
						MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),MainGUI.test.mySongs.get(tempint),"resources/sample_bg.gif"));
						
					}
				});
				viewObjects.add(temp);
			}
		}
	}

}
