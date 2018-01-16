package noteConversion;

import java.util.ArrayList;

import mainGame.components.interfaces.SongInterface;

public class NoteConversion implements SongInterface{
	public  ArrayList<String> notes = new ArrayList<String>();
	String title;
	String artist;
	int bpm;
	int offset;
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public int getBPM() {
		// TODO Auto-generated method stub
		return bpm;
	}

	@Override
	public String getArtist() {
		// TODO Auto-generated method stub
		return artist;
	}
   
	@Override
	public int getOffSet() {
		// TODO Auto-generated method stub
		return offset;
	}

	@Override
	public ArrayList<int[]> getBeats() {
		// TODO Auto-generated method stub
		return null;
	}

}
