package mainGame.saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		save("Hello", 165, "Justin", 0, arr);
		p.load("HelloJustin.csv");
		
		System.out.println(p.getOffSet());
		*/
		
		/*
		OSUconvert("osu.txt");
		FileP p = new FileP();
		
		p.load("DreadnoughtMastermind(xi+nora2r).csv");
		System.out.println(p.getTitle());
		*/
		
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

	/**
	 * This helper method will read the next line of the BufferedReader and split it by a colon as per our map file format. 
	 * It will retrieve a piece of metadata and return it.
	 * @param br - BufferedReader of the file you're trying to get metadata from
	 * @return - Returns the metadata of the next line of the BufferedReader
	 * @author - Justin Yau
	 */
	public String splitLine(BufferedReader br) {
		try {
			String line = br.readLine(); 
			String[] lineSplit = line.split(":");
			return lineSplit[1];
		}catch (IOException e) {
			
			return "";
		}
	}
	
	/**
	 * Static version of the splitLine method above. <br> 
	 * This helper method will read the next line of the BufferedReader and split it by a colon as per our map file format. 
	 * It will retrieve a piece of metadata and return it.
	 * @param br - BufferedReader of the file you're trying to get metadata from
	 * @return - Returns the metadata of the next line of the BufferedReader
	 * @author - Justin Yau
	 */
	public static String sSplitLine(BufferedReader br) {
		try {
			String line = br.readLine(); 
			String[] lineSplit = line.split(":");
			return lineSplit[1];
		}catch (IOException e) {
			
			return "";
		}
	}
	
	/**
	 * This method will attempt to use the bufferedReader to keep reading until it finds what was specified
	 * @param br - The bufferedReader with the file information 
	 * @param goal - The string you would like the reader to look for
	 * 
	 * @return - Whether the reader found the target line or not
	 * 
	 * @author Justin Yau
	 */
	public static boolean keepReadingTillYouReach(BufferedReader br, String goal) {
		try {
			while(!br.readLine().contains(goal)) {
				
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * This method will load a CSV file and save it in a instance of File.
	 * DO NOT ATTEMPT TO LOAD A OSU FILE WITH THIS METHOD. <br> <br>
	 * 
	 * Sample Format: <br> <br>
	 * 
	 * Title: Hitorigio <br>
	 * BPM: 165 <br>
	 * Artist: Claris <br>
	 * Offset: 0 <br>
	 * <br>
	 * 1(column),0(startTime),10(endTime)
	 * 
	 * @param fileName - The path of the file that you're looking to load
	 * 
	 * @return Returns whether the load was successful or not
	 * 
	 * @author Justin Yau
	 */
	@Override
	public boolean load(String fileName) {
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			String line = "";
			//A BufferedReader enables us to read the file one line at a time
			BufferedReader br = new BufferedReader(fileReader);
			
			//Retrieve title information
			title = splitLine(br);
			
			//Retrieve beats per minute
			BPM = Integer.parseInt(splitLine(br));
			
			//Retrieve artist name
			artist = splitLine(br);
			
			//Retrieve offset
			offSet = Integer.parseInt(splitLine(br));
			
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
			if(!keepReadingTillYouReach(br, "[MetaData]")) { return false; }
			
			//Retrieve title information
			tempTitle = sSplitLine(br);
			
			br.readLine(); //Skip other title information
			
			//Retrieve artist information
			tempArtist = sSplitLine(br);
			
			//Keep reading till it hits [HitObjects]
			if(!keepReadingTillYouReach(br, "[HitObjects]")) { return false; }
			
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
	 * This method returns the given string without the last character
	 * @param s - The string you would like to get rid the last character from
	 * @return - The string without the last character
	 * 
	 * @author Justin Yau
	 */
	public static String withoutEnd(String s) {
		return s.substring(0, s.length() - 1);
	}
	
	/**
	 * This method attempts to convert a step mania file (RENAMED TO .TXT) into our game format file
	 * 
	 * @param fileName - Path of the step mania formatted file
	 * @return Whether the save was sucessful or not 
	 * 
	 * @author Justin Yau
	 */
	public static boolean stepConvert(String fileName) {
		
		ArrayList<int[]> listerino = new ArrayList<int[]>(0);
		String tempTitle = "";
		String tempArtist = "";
		int tempBPM = 0;
		int tempOffSet = 0;
		int beatsEvery = 0;
		int currentTime = 0;
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			String line = "";
			//A BufferedReader enables us to read the file one line at a time
			BufferedReader br = new BufferedReader(fileReader);
			
			//Keep reading till it hit #Version
			if(!keepReadingTillYouReach(br, "#VERSION")) { return false; }
			
			//Retrieve title information
			tempTitle = withoutEnd(sSplitLine(br));
			
			br.readLine(); //Skip other title information
			
			//Retrieve artist information
			tempArtist = withoutEnd(sSplitLine(br));
			
			//Keep reading till it hits the #MUSIC
			if(!keepReadingTillYouReach(br, "#MUSIC")) { return false; }
			
			//Retrieve offset information
			tempOffSet = (int) (Float.parseFloat(withoutEnd(sSplitLine(br))));
			
			//Keep reading till you reach #SELECTABLE
			if(!keepReadingTillYouReach(br, "#SELECTABLE")) { return false; }
			
			//Retrieve BPM information
			tempBPM = (int) (Float.parseFloat(withoutEnd(sSplitLine(br)).split("=")[1]));
			beatsEvery = (int)(((double)60/tempBPM) * 750);
			
			//Keep reading till it hits #NOTES :
			if(!keepReadingTillYouReach(br, "#NOTES")) { return false; }
			
			while ((line = br.readLine()) != null) {

				if(!line.contains("// measure") && !line.contains(";")) {
					for(int i = 0; i < line.length(); i++) {
						int[] beat = new int[3];
						String lane = line.substring(i, i+1);
						if(lane.equals("1")) {
							beat[0] = i + 1;
							beat[1] = currentTime;
							beat[2] = 0;
							listerino.add(beat);
						}
					}
					currentTime += beatsEvery;
				}

			}
			br.close();
			
			save(tempTitle, tempBPM, tempArtist, tempOffSet, listerino);
			
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
	
	/**
	 * This method will return the title of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the title of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	@Override
	public String getTitle() {
		
		return title;
	}

	/**
	 * This method will return the beats per minute of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the BPM of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	@Override
	public int getBPM() {
		
		return BPM;
	}

	/**
	 * This method will return the artist of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the artist of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	@Override
	public String getArtist() {

		return artist;
	}

	/**
	 * This method will return the offSet of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the offSet of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	@Override
	public int getOffSet() {

		return offSet;
	}

	/**
	 * This method will return the beats of the beatmap from the file that you have loaded before.
	 * If you have not loaded a file prior to running this command it will return blank fields.
	 * 
	 * @return Returns the beats of the beatmap from the file that it has loaded
	 * 
	 * @author Justin Yau
	 */
	@Override
	public ArrayList<int[]> getBeats() {

		return list;
	}

}
