package shop;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.*;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
public class CharacterSelectionScreen extends FullFunctionScreen implements unlocker{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ImageButton> imagesButton;
	private ArrayList<ImageButton> selectImage;
	private ArrayList<Button> buttons;
	private int numChars;
	private String[] imageNames;
	private Boolean[] unlock;
	private String[] colorArray;
	private String  color;
	public CharacterSelectionScreen(int width, int height) {
		// TODO Auto-generated constructor stub
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		numChars = 4;
		imagesButton = new ArrayList<ImageButton>();
		selectImage = new ArrayList<ImageButton>();
		buttons = new ArrayList<Button>();
		imageNames = new String[] {"resources/sprites/defaultGuy.png","resources/sprites/redGuy.png", "resources/sprites/greenGuy.png", "resources/sprites/whiteGuy.png"};
		unlock = new Boolean[] {true, false, false, false};
		colorArray = new String[] {"default", "red", "green" , "white"};
		for(int i = 0; i < imageNames.length; i ++) {
			final int x = i;	
			selectImage.add(new ImageButton(290, 180, 200, 300, imageNames[i], ""));
			imagesButton.add(new ImageButton((125*i), 50, 100, 300, imageNames[i], ""));
			buttons.add(new Button(125*i, 50, 100, 300, "", new Action() {
				
				@Override
				public void act() {
					int j = x;
					if(unlock[j] == true) {
						for(int z = 0; z < numChars; z++) {
							selectImage.get(z).setVisible(false);
						}
						selectImage.get(j).setVisible(true);
						color = colorArray[j];
						
					}else {
						//
					}
				}
			}));
		}
		Button backButton = new Button(800, 50, 100, 30, "Back", Color.GRAY, new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(MainGUI.mainMenu);
				MainGUI.character.getSkin();
				
			}
		});
		//set things to visible 
		for(int z = 0; z < numChars; z++) {
			selectImage.get(z).setVisible(false);
		}
		
		//viewobjects
		viewObjects.add(backButton);
		for(int k = 0; k < numChars; k++) {
			viewObjects.add(buttons.get(k));
			viewObjects.add(imagesButton.get(k));
			viewObjects.add(selectImage.get(k));
		}
	}


	@Override
	public void unlock(int i) {
		unlock[i+1] = true;
		
	}

	@Override
	public String getSkin() {
		// TODO Auto-generated method stub
		return color;
	}

}