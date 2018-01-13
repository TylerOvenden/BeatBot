package mainGame.saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import mainGame.saving.interfaces.FileProcessor;

/**
 * This class will have the general methods for saving, loading, and converting our beatmap files
 * @author Justin Yau
 *
 */
public class FileP implements FileProcessor {

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
	public FileP() {
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
			fw.write("\n");
			
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
	public boolean load(String fileName) {
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			String line = "";
			//a BufferedReader enables us to read the file one line at a time
			BufferedReader br = new BufferedReader(fileReader);
			while ((line = br.readLine()) != null) {

				String[] param = line.split(",");
				//add a new Book for each line in the save file



			}
			br.close();
			
			return true;
			
		}catch (IOException e) {
			
			return false;
		}
	}

	@Override
	public void OSUconvert(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		
		return title;
	}

	@Override
	public int getBPM() {
		
		return BPM;
	}

	@Override
	public String getArtist() {

		return artist;
	}

	@Override
	public int getOffSet() {

		return offSet;
	}

	@Override
	public ArrayList<Integer[]> getBeats() {

		return list;
	}

}
