package mainGame.saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
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
	private ArrayList<int[]> list;
	
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
		list = new ArrayList<int[]>(0);
	}

	@Override
	public void save(String title, int BPM, String artist, int offSet, ArrayList<Integer[]> list) {
		String fileName = title + artist + ".csv";
		try{    
			
			FileWriter fw=new FileWriter(fileName);
			fw.write("Title:" + title + "\n");
			fw.write("BPM:" + BPM + "\n");
			fw.write("Artist:" + artist + "\n");
			fw.write("Offset:" + offSet + "\n");
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
			LineNumberReader lineNum = new LineNumberReader(fileReader);
			String line = "";
			//A BufferedReader enables us to read the file one line at a time
			BufferedReader br = new BufferedReader(fileReader);
			
			//Retrieve title information
			String titleLine = br.readLine(); 
			String[] titleSplit = titleLine.split(":");
			title = titleSplit[1];
			
			//Retrieve beats per minute
			String bpmLine = br.readLine();
			String[] bpmSplit = bpmLine.split(":");
			BPM = Integer.parseInt(bpmSplit[1]);
			
			//Retrieve artist name
			String artistLine = br.readLine();
			String[] artistSplit = artistLine.split(":");
			artist = artistSplit[1];
			
			//Retrieve offset
			String offSetLine = br.readLine();
			String[] offSetSplit = offSetLine.split(":");
			offSet = Integer.parseInt(offSetSplit[1]);
			
			br.readLine(); //This compensates for the empty line after metaData 
			
			while ((line = br.readLine()) != null) {

				int[] beat = new int[3];
				String[] param = line.split(",");
				beat[0] = Integer.parseInt(param[0]);
				beat[1] = Integer.parseInt(param[1]);
				beat[2] = Integer.parseInt(param[2]);
				list.add(beat);

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
	public ArrayList<int[]> getBeats() {

		return list;
	}

}
