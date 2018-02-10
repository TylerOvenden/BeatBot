package mainGame.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.components.*;
import gui.interfaces.Visible;
import mainGame.MainGUI;
import mainGame.components.*;
import mainGame.saving.WavMusicBeatDetector;
import mainGame.screens.interfaces.ResizableScreen;

/**
 * A screen where users can import WAV audio files that get analyzed for beats that are converted into our own game!
 * 
 * @author Justin Yau
 */
public class ImportScreen extends ResizableScreen {
	
	private ArrayList<TextBox> boxes; //Arraylist of all the text boxes will be stored here
	private ArrayList<OptionButton> optBTN; //Arraylist of all the buttons will be stored here
	private File importedFile; //The imported file will be stored here
	private TextLabel fileName; //The file name textlabel will be stored here
	private TextLabel status; //The text label for the status of the converter will be stored here
	
	/**
	 * Constructor creates a screen where users can import WAV audio files into our own game!
	 * @param width - Width of the screen
	 * @param height - Height of the screen
	 * 
	 * @author Justin Yau
	 */
	public ImportScreen(int width, int height) {
		super(MainGUI.screenWidth, MainGUI.screenHeight);
		
		startResize(width, height);
	}

	/**
	 * Overrides the method such that the clickable components are clickable aswell
	 * 
	 * @author Justin Yau
	 */
	public void startResize(int width, int height) {
		setXScale(((double) width)/getOWidth());
		setYScale(((double) height)/getOHeight());
		for(OptionButton btn: optBTN) {
			btn.updateScales(getXScale(), getYScale());
		}
	}
	
	/**
	 * This method overrides the default adapter to resize clickable components 
	 * @return The New Component Adapter to suit our needs
	 * 
	 * @author Justin Yau
	 */
	public ComponentAdapter getComponentAdapter() {
		return new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				Component c = (Component)arg0.getSource();
				int height = c.getHeight();
				int width = c.getWidth();
				setXScale(((double) width)/getOWidth());
				setYScale(((double) height)/getOHeight());
				for(OptionButton btn: optBTN) {
					btn.updateScales(getXScale(), getYScale());
				}
			}
			
		};
	}
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {

		optBTN = new ArrayList<OptionButton>();
		Font f = new Font("Impact", Font.BOLD, 18);
		
		addBackground();
		
		CustomText txt = new CustomText((MainGUI.screenWidth/2) - 150, 50, 300, 200, "Import", true);
		viewObjects.add(txt);
		
		addTextLabels(f, viewObjects);
		addTextBoxes(f, viewObjects);
		addImportButton(viewObjects);
		addConvertButton(viewObjects);
		
	}
	
	/**
	 * This method adds the convert button to process all the information to the screen
	 * @param viewObjects - The list of visible objects for the screen
	 * 
	 * @author Justin Yau
	 */
	public void addConvertButton(List<Visible> viewObjects) {
		OptionButton btn = new OptionButton(430, 350, 75, 50, "Convert", this);
		btn.setAction(new Action() {
			
			@Override
			public void act() {
				String title = boxes.get(0).getText();
				String artist = boxes.get(1).getText();
				if(title != null && !(title.equals("")) 
						&& artist != null && !(artist.equals(""))
						&& importedFile != null) {
					processInformation(title, artist);
					status.setText("Success!");
					status.update();
				}
				else {
					status.setText("Input all required fields!");
					status.update();
				}
			}
		});
		optBTN.add(btn);
		viewObjects.add(btn);
	}
	
	/**
	 * This method processes all the inputted information and uses the beat detector to create a song file under our format style
	 * @param title - Title of the song
	 * @param artist - Artist of the song
	 * 
	 * @author Justin Yau
	 */
	public void processInformation(String title, String artist) {
		WavMusicBeatDetector detect = new WavMusicBeatDetector(title, artist, importedFile);
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("resources/maps/" + title + artist + "/" + title + artist + ".wav"));
			Files.copy(importedFile.toPath(), out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 	/**
 	 * This method adds the import button to the screen
 	 * @param viewObjects The list of visible objects for the screen
 	 * 
 	 * @author Justin Yau
 	 */
	public void addImportButton(List<Visible> viewObjects) {
		OptionButton btn = new OptionButton(430, 300, 75, 50, "Import", this);
		btn.setAction(new Action() {
			
			@Override
			public void act() {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV", "wav");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	importedFile = chooser.getSelectedFile();
			    	fileName.setText(chooser.getSelectedFile().getName());
			    	fileName.update();
			    }
			}
		});
		optBTN.add(btn);
		viewObjects.add(btn);
	}
	
 	/**
 	 * This method adds the text labels to the screen
 	 * @param f Custom font you would like to have these text boxes to have
 	 * @param viewObjects The list of visible objects for the screen
 	 * 
 	 * @author Justin Yau
 	 */
 	public void addTextLabels(Font f, List<Visible> viewObjects) {
		ArrayList<TextLabel> text = new ArrayList<TextLabel>();
		TextLabel instruction = new TextLabel(125, 125, 800, 50, "Input the title and artist that you would like and import your WAV file to get started!");
		text.add(instruction);
		
		TextLabel title = new TextLabel(325, 200, 50, 50, "Title");
		text.add(title);
		
		TextLabel artist = new TextLabel(325, 250, 50, 50, "Artist");
		text.add(artist);
		
		fileName = new TextLabel(515, 315, 300, 50, "None");
		text.add(fileName);
		
		status = new TextLabel(515, 365, 300, 50, "Status: nothing");
		text.add(status);
		
		for(TextLabel t: text) {
			t.setCustomTextColor(Color.LIGHT_GRAY);
			t.setFont(f);
			viewObjects.add(t);
		}
 	}
 	
 	/**
 	 * This method adds the text boxes to the screen
 	 * @param f Custom font you would like to have these text boxes to have
 	 * @param viewObjects The list of visible objects for the screen
 	 * 
 	 * @author Justin Yau
 	 */
 	public void addTextBoxes(Font f, List<Visible> viewObjects) {
 		
		boxes = new ArrayList<TextBox>();
		TextBox titleB = new TextBox(375,200,200,35,"");
		boxes.add(titleB);
		
		TextBox artistB = new TextBox(375,250,200,35,"");
		boxes.add(artistB);
		
		for(TextBox box: boxes) {
			box.setFont(f);
			viewObjects.add(box);
		}
 		
 	}
	
	/**
	 * This method adds the background to the screen
	 * 
	 * @author Justin Yau
	 */
	public void addBackground() {
		Graphic g = new Graphic(0,0, determineScale("resources/background1.png"), "resources/background1.png");
		addObject(g);
	}
	
	/**
	 * Determines the appropriate scale for the background image such that it fits entirely on the screen
	 * @param path - Path of the background file
	 * @return - The appropriate scale to apply to the image to make it fit the screen
	 * 
	 * @author Justin Yau
	 */
	public double determineScale(String path) {
		double scale = 1;
		try {
			BufferedImage img = ImageIO.read(new File(path));
			scale = ((double)MainGUI.screenWidth)/img.getWidth();
		} catch (IOException e) {
		}
		return scale;
	}

}
