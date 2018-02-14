package highscore;

import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.Song;
import mainGame.components.SongBundle;
import mainGame.components.interfaces.SongInterface;
import mainGame.screens.GameScreen;

/**
 * My quick implementation of a temp song select screen in case Yonathon doesn't finish his songs up 
 * @author Justin Yau
 *
 */
public class TempSongSelect extends FullFunctionScreen {
	
	private Button back; //The back button will be stored here
	private HashMap<String, Song> currentSongs; //The list of all the displayed songs will be stored here
	private Graphic background; //The graphic for the background will be stored here
	
	/**
	 * Constructor creates a new screen which will load all the unlocked songs currently on the screen
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public TempSongSelect(int width, int height) throws IOException {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
		background = new Graphic(0,0, getWidth(), getHeight(), "resources/mop.png");
		viewObjects.add(background);
		
		currentSongs = new HashMap<String, Song>();
		Button temp;
		back = new Button(getWidth() - 100, 0, 100, 80, "Back", new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(MainGUI.mainMenu);
			}
		});
		back.setForeground(Color.white);
		viewObjects.add(back);
		
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
					temp=new Button(0,20*count,300,50, songList[j].getName(), new Action() {
						@Override
						public void act() {
							MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),song,"resources/sample_bg.gif"));
							
						}
					});
					temp.setForeground(Color.WHITE);
					count++;
					currentSongs.put(songList[j].getPath(), song);
					viewObjects.add(temp);
				}
			}
		}
	}
	
	/**
	 * This method searches through the hashmap and returns whether or not the song's title was found among the entries
	 * @param song - The song to search for
	 * @return - Whether or not the song's title was found among the entries
	 * 
	 * @author Justin Yau
	 */
	public boolean isFound(String songPath) {
		for(String s: currentSongs.keySet()) {
			if(s.equalsIgnoreCase(songPath)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method searches through all the songs and displays the unlocked ones
	 * 
	 * @author Justin Yau
	 */
	public void spawnButtons() {
		addObject(background);
		addObject(back);
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
					if(!isFound(songList[j].getPath())) {
						temp=new Button(0,20*count,300,50, songList[j].getName(), new Action() {
							@Override
							public void act() {
								MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),song,"resources/sample_bg.gif"));
								
							}
						});
						currentSongs.put(songList[j].getPath(), song);
					}
					else {
						int t = j;
						temp=new Button(0,20*count,300,50, songList[j].getName(), new Action() {
							@Override
							public void act() {
								MainGUI.test.setScreen(new GameScreen(getWidth(),getHeight(),currentSongs.get(songList[t].getPath()),"resources/sample_bg.gif"));
								
							}
						});
					}
					temp.setForeground(Color.WHITE);
					count++;
					addObject(temp);
				}
			}
		}
	}
	
	/**
	 * Call this method to update the list of songs currently unlocked
	 * 
	 * @author Justin Yau
	 */
	public void updateList() {
		getObjects().clear();
		removeAll();
		spawnButtons();
	}

}