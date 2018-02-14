/**
 * Temporary until Yonathan finishes his screen
 * 
 * @author Steven
 */

package highscore;

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

public class TempSongSelect extends FullFunctionScreen {

	private int count = 0;
	
	public TempSongSelect(int width, int height) throws IOException {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		Button temp;
		int count = 0;
		for(int i=0;i<MainGUI.test.songs.size();i++) {
			int tempint=i;
			SongBundle songBundle = MainGUI.test.songs.get(i);
			if((MainGUI.test.songs.get(tempint).isUnlock())) {
				File[] songList = new File(songBundle.getPath()).listFiles(new FileFilter() {
		            @Override
		            public boolean accept(File pathname) {
		                return pathname.getName().toLowerCase().endsWith(".csv") 
		                    || pathname.isDirectory();
		            }
		        });
				for(int j = 0; j < songList.length; j++) {
					Song song = new Song(songList[j].getPath());
					temp=new Button(0,20*count+40,300,50, songList[j].getName() ,new Action() {
						@Override
						public void act() {
							MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),song,"resources/sample_bg.gif"));
							
						}
					});
					count++;
					viewObjects.add(temp);
				}
			}
		}
		this.count = count;
	}
	
	public void spawnButtons() {
		Button temp;
		int count = 0;
		for(int i=0;i<MainGUI.test.songs.size();i++) {
			int tempint=i;
			SongBundle songBundle = MainGUI.test.songs.get(i);
			if((MainGUI.test.songs.get(tempint).isUnlock())) {
				File[] songList = new File(songBundle.getPath()).listFiles(new FileFilter() {
		            @Override
		            public boolean accept(File pathname) {
		                return pathname.getName().toLowerCase().endsWith(".csv") 
		                    || pathname.isDirectory();
		            }
		        });
				for(int j = 0; j < songList.length; j++) {
					if(this.count < count) {
						Song song = new Song(songList[j].getPath());
						temp=new Button(0,20*count+40,300,50, songList[j].getName() ,new Action() {
							@Override
							public void act() {
								MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),song,"resources/sample_bg.gif"));
								
							}
						});
						addObject(temp);
					}
					count++;
				}
			}
		}
	}
	
	public void updateList() {
		spawnButtons();
	}

}
