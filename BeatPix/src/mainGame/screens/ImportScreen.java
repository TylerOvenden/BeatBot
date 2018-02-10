package mainGame.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.components.*;
import gui.interfaces.Visible;
import mainGame.MainGUI;
import mainGame.components.*;
import mainGame.screens.interfaces.ResizableScreen;

public class ImportScreen extends ResizableScreen {
	
	private ArrayList<TextBox> boxes;
	private ArrayList<OptionButton> optBTN;
	private File importedFile;
	private TextLabel fileName;
	
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
		
	}
	
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
		
		for(TextLabel t: text) {
			t.setCustomTextColor(Color.LIGHT_GRAY);
			t.setFont(f);
			viewObjects.add(t);
		}
 	}
 	
 	/**
 	 * This method adds the text boxes to the screen
 	 * @param f 
 	 * @param viewObjects
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
