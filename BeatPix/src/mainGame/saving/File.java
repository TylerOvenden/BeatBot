package mainGame.saving;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import mainGame.saving.interfaces.FileProcessor;

/**
 * This class will have the general methods for saving, loading, and converting our beatmap files
 * @author Justin Yau
 *
 */
public class File implements FileProcessor {

	private String title;
	private int BPM;
	private String artist;
	private int offSet;
	private ArrayList<Integer[]> list;
	
	/**
	 * Constructor sets the fields up to their normal settings 
	 * 
	 * @author Justin Yau
	 */
	public File() {
		title = "";
		BPM = 0;
		artist = "";
		offSet = 0;
		list = new ArrayList<Integer[]>(0);
	}

	@Override
	public void save(String title, int BPM, String artist, int offSet, ArrayList<Integer[]> list) {
		String fileName = title + artist + ".csv";
		try{    
			
			FileWriter fw=new FileWriter(fileName);
			fw.write("Title: " + title + "\n");
			fw.write("BPM: " + BPM + "\n");
			fw.write("Artist: " + artist + "\n");
			fw.write("Offset: " + offSet + "\n");
			for(Integer[] arr: list) {
				for(int num: arr) {
					fw.write(num + ",");
				}
				fw.write("\n");    	
			}

			fw.close();    
			System.out.println("Success! File \"" + fileName + "\" saved!");
			
		}catch(IOException e){
			
			System.out.println("An IOException was thrown. \nCheck to see that the directory where you tried to save the file actually exists.");
			
		}
	}

	@Override
	public void load(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OSUconvert(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBPM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOffSet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Integer[]> getBeats() {
		// TODO Auto-generated method stub
		return null;
	}

}
