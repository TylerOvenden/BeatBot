package mainGame.saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;

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

	//Tester
	public static void main(String[] args) { 
		
		/*
		 
		FileP p = new FileP();
		ArrayList<int[]> arr = new ArrayList<int[]>(0);
		int[] arr1 = new int[3];
		arr1[0] = 0;
		arr1[1] = 1;
		arr1[2] = 2;
		arr.add(arr1);
		p.save("Hello", 165, "Justin", 0, arr);
		p.load("HelloJustin.csv");
		
		System.out.println(Arrays.toString(p.getBeats().get(0)));
		
		*/
		
		OSUconvert("osu.txt");
		FileP p = new FileP();
		
		p.load("DreadnoughtMastermind(xi+nora2r).csv");
		System.out.println(p.getArtist());
	}
	
	/**
	 * This method will create a CSV file named after the title and artist.
	 * 
	 * @param title - Name of the map 
	 * @param BPM - Beats per minute
	 * @param artist - Arist that created the map
	 * @param offSet - Offset 
	 * @param list - Integer Array that will 
	 * 
	 * @author Justin Yau
	 */
	public static void save(String title, int BPM, String artist, int offSet, ArrayList<int[]> list) {
		String fileName = title + artist + ".csv";
		try{    
			
			FileWriter fw=new FileWriter(fileName);
			fw.write("Title:" + title + "\n");
			fw.write("BPM:" + BPM + "\n");
			fw.write("Artist:" + artist + "\n");
			fw.write("Offset:" + offSet + "\n");
			fw.write("\n");
			
			for(int[] arr: list) {
				for(int num: arr) {
					fw.write(num + ",");
				}
				fw.write("\n");    	
			}

			fw.close();    
			
		}catch(IOException e){
			
			System.out.println("An IOException was thrown. \nCheck to see that the directory where you tried to save the file actually exists.");
			
		}
	}

	@Override
	public boolean load(String fileName) {
		try {
			FileReader fileReader = new FileReader(new File(fileName));
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
	
	/**
	 * This method will be used to convert OSU Formatted Files into our own game files for personal use.
	 * ONLY INPUT OSU FORMATTED FILES OR THERE WILL BE ERRORS.
	 * 
	 * @param fileName - The path of the file that you would like to convert
	 * @return Whether the operation has been successfully made or not due to incorrect file path
	 * @author Justin Yau
	 */
	public static boolean OSUconvert(String fileName) {
		
		ArrayList<int[]> listerino = new ArrayList<int[]>(0);
		String tempTitle = "";
		String tempArtist = "";
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			String line = "";
			//A BufferedReader enables us to read the file one line at a time
			BufferedReader br = new BufferedReader(fileReader);
			
			//Keep reading till it hit [MetaData]
			while(!br.readLine().equalsIgnoreCase("[MetaData]")) {
				
			}
			
			//Retrieve title information
			String titleLine = br.readLine(); 
			String[] titleSplit = titleLine.split(":");
			tempTitle = titleSplit[1];
			
			br.readLine(); //Skip other title information
			
			String artistLine = br.readLine();
			String[] artistSplit = artistLine.split(":");
			tempArtist = artistSplit[1];
			
			//Keep reading till it hits [HitObjects]
			while(!br.readLine().equalsIgnoreCase("[HitObjects]")) {
				
			}
			
			while ((line = br.readLine()) != null) {

				int[] beat = new int[3];
				String[] param = line.split(",");
				beat[0] = getColumn(param[0]);
				beat[1] = Integer.parseInt(param[2]);
				String[] param1 = param[5].split(":");
				beat[2] = Integer.parseInt(param1[0]);
				listerino.add(beat);

			}
			br.close();
			
			save(tempTitle, 0, tempArtist, 0, listerino);
			
			return true;
			
		}catch (IOException e) {
			
			return false;
		}
	}

	/**
	 * Based off the OSU format, this method will return the correct column number or lane that the beat appears in
	 * 
	 * @param num - The number provided by the OSU file
	 * @return Number corresponding to the lane the beat appears in
	 * @author Justin Yau
	 */
	public static int getColumn(String num) {
		int[] arr = {64,192,320,448};
		int theNum = Integer.parseInt(num);
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == theNum) {
				return i + 1;
			}
		}
		return 0;
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
