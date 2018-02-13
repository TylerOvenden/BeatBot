package screens.components;

import java.util.ArrayList;

import gui.userInterfaces.FullFunctionScreen;

public class MultiLineCustomText {

	private ArrayList<CustomText> customTextArr; 
	private int x;
	private int y;
	private int w;
	private int h;
	private String text;
	private FullFunctionScreen parentScreen;
	private boolean white;
	private int lengthOfLine;
	/**Constructor
	 * 
	 * MultiLineCustomText that starts 
	 * at the designated x y coordinates
	 * 
	 * Width and Height are for the wanted 
	 * OVERALL DIMENSIONS of the box where the
	 * MultiLineCustomText will reside in
	 * 
	 * @param xTopLeft
	 * @param yTopLeft
	 * @param width
	 * @param height
	 * @param s
	 * @param screen
	 */
	public MultiLineCustomText(int xTopLeft, int yTopLeft, int width, int height, String s, FullFunctionScreen screen) {
		this.text = s;
		this.x = xTopLeft; this.w = width;
		this.y = yTopLeft; this.h = height;
		customTextArr = new ArrayList<CustomText>();
		this.parentScreen = screen;
		white = true;
		createCustomTextArray(text);
		this.lengthOfLine = 0; lineSpacing = 10;
	}
	
	public MultiLineCustomText(int xTopLeft, int yTopLeft, int width, int height, String s, FullFunctionScreen screen, int lengthOfLine) {
		this.text = s;
		this.x = xTopLeft; this.w = width;
		this.y = yTopLeft; this.h = height;
		customTextArr = new ArrayList<CustomText>();
		this.parentScreen = screen;
		white = true;
		this.lengthOfLine = lengthOfLine; lineSpacing = 10;
		createCustomTextArray(text);
	}
	
	
	public MultiLineCustomText(int xTopLeft, int yTopLeft, int width, int height, String s, FullFunctionScreen screen, int lengthOfLine, int ls) {
		this.text = s;
		this.x = xTopLeft; this.w = width;
		this.y = yTopLeft; this.h = height;
		customTextArr = new ArrayList<CustomText>();
		this.parentScreen = screen;
		white = true;
		this.lengthOfLine = lengthOfLine; lineSpacing = ls;
		createCustomTextArray(text);
	}
	
	public void setLineSpacing(int x) {
		lineSpacing = x;
	}
	public void addToScreen() {
		for(CustomText c: customTextArr) {
			parentScreen.addObject(c);
		}
	}
	
	public void removeFromScreen() {
		for(CustomText c: customTextArr) {
			parentScreen.remove(c);
		}
	}
	
	int lineSpacing;
	public void createCustomTextArray(String s) {

		int rowLength = lengthOfLine;
		if(lengthOfLine == 0)
			rowLength = 10;
		
		if(rowLength <longestWordLength(s))
			rowLength = longestWordLength(s);
		
		customTextArr = new ArrayList<CustomText>();
		ArrayList<String> temp = arrayOfBrokenUpStrings(s, rowLength);
		for(int i = 0; i< temp.size(); i++) {
			
			CustomText ct = new CustomText(x,
												y + h/temp.size()*i + lineSpacing*i, 
													w, 
														h/(temp.size()+1), 
															temp.get(i),
																false, true, white);
			customTextArr.add(ct);
		}
	}
	
	public void setWhite(boolean b) {
		white = b;
	}
	
	public static int longestWordLength(String s) {
		int longest = 0;
		String[] s1= s.split(" ");
		for(String x: s1) {
			if(x.length() > longest) {
				longest = x.length();
			}
		}
		return longest;
	}
	/**Returns idx of the last word where there are full words
	 * within a certain boundary:
	 * 
	 * indexOfLongestSentence("Doctor of Doom", 10)
	 * should return 8, -> "Doctor of" is the longest
	 * full word combo within 10 letters
	 * 
	 * @param s
	 * @param boundary
	 * @return
	 */
	public static int indexOfLongestSentence(String s, int boundary) {
		int idx = boundary;
		if(s.length() <= boundary) {
			return s.length()-1;
		}
		for(int i = boundary; i > 0; i--) {
			if(s.substring(i, i+1).equals(" ") || i == 0) {
				idx = i;
				break;
			}else {
				idx --;
			}
		}
		return idx;
	}
	/**Breaks up the string to create strings of a certain length
	 * 
	 * @param s
	 * @param boundary
	 * @return
	 */
	public static ArrayList<String> arrayOfBrokenUpStrings(String s, int boundary) {
		String temps = s;
		ArrayList<String> arrayTemp = new ArrayList<String>();
		while(temps.length() > 1) {
			if(indexOfLongestSentence(temps, boundary) == 0) {
				break;
			}
			
			int tempx = indexOfLongestSentence(temps, boundary);
			String tempSub = temps.substring(0,tempx+1);

			if(tempSub.substring(0, 1).equals(" "))
				tempSub = tempSub.substring(1,tempSub.length());

			while(tempSub.length() < boundary) {
				tempSub += " ";
			}
				
			arrayTemp.add(tempSub);
			temps = temps.substring(tempx,temps.length());
		}
		return arrayTemp;
	}
}
