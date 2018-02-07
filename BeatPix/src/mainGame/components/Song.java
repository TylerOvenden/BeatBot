package mainGame.components;

import java.util.ArrayList;

import mainGame.components.interfaces.Highscore;
import mainGame.components.interfaces.SongInterface;
import mainGame.saving.FileP;

/*
 * This class will be mainly managed by Justin Yau
 * PERIOD 4 & 5 - AP Computer Science Java
 */

/**
 * This class contains the basic metadata and beats that will be utilized by our other classes
 * @author Justin Yau
 *
 */
public class Song implements SongInterface, Highscore {

	private String title;
	private int BPM;
	private String artist;
	private int offSet;
	private ArrayList<int[]> beats;
	private ArrayList<Integer> scores;
	private ArrayList<Float> accuracy;
	
	/**
	 * This constructor will load the file utilizing a file loader and then update the metadata and beats in fields. <br>
	 * Retrieve these fields utilizing the get methods.
	 * 
	 * @param songPath - The path of the song file that you would like to load
	 * 
	 * @author Justin Yau
	 */
	public Song(String songPath) {
		FileP map = new FileP();
		map.load(songPath);
		title = map.getTitle();
		BPM = map.getBPM();
		artist = map.getArtist();
		offSet = map.getOffSet();
		beats = map.getBeats();
		scores = new ArrayList<Integer>();
		accuracy = new ArrayList<Float>();
	}

	/**
	 * This method will return the title of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the title of the beatmap
	 * 
	 * @author Justin Yau
	 */
	@Override
	public String getTitle() {
		
		return title;
	}

	/**
	 * This method will return the beats per minute of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the BPM of the beatmap
	 * 
	 * @author Justin Yau
	 */
	@Override
	public int getBPM() {
		
		return BPM;
	}

	/**
	 * This method will return the artist of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the artist of the beatmap
	 * 
	 * @author Justin Yau
	 */
	@Override
	public String getArtist() {

		return artist;
	}

	/**
	 * This method will return the offSet of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the offSet of the beatmap
	 * 
	 * @author Justin Yau
	 */
	@Override
	public int getOffSet() {

		return offSet;
	}

	/**
	 * This method will return the beats of the beatmap.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the beats of the beatmap
	 * 
	 * @author Justin Yau
	 */
	@Override
	public ArrayList<int[]> getBeats() {

		return beats;
	}
	
	/**
	 * This method adds the given score to the array list of scores and accuracies
	 * @param s - The score you would like to add to correspond with the song
	 * 
	 * @author Justin Yau
	 */
	public void addScoreAndAccuracy(int s,  float a) {
		scores.add(s);
		accuracy.add(a);
	}
	
	/**
	 * This method returns the current scores the user had 
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Integer> getScores() {
		return scores;
	}

	/**
	 * This method returns the current accuracies the user had
	 * 
	 * @author Justin Yau
	 */
	public ArrayList<Float> getAccuracies() {
		return accuracy;
	}
	
}
