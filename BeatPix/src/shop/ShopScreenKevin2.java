package shop;

import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Button;
import gui.components.Component;
import gui.components.FullFunctionPane;
import gui.interfaces.FocusController;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;

public class ShopScreenKevin2 extends FullFunctionScreen {

	private ArrayList<Button> buttonList;
	private int index;
	private int timesClicked = 0;
	private boolean clicked = false;
	public ShopScreenKevin2(int width, int height) {
		super(width, height);

	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		buttonList = new ArrayList<Button>();
		for(int i=0; i < 5; i++){ 
			//got the index number
			final int x = i;
			buttonList.add(new Button(5,(50*i)+50,100,25,"Button "+i, new Action() {
				int j = x;
				@Override
				public void act() {
					if(clicked == false) {
						index = j;
						clicked = true;
						timesClicked ++;
						System.out.println(j);
						System.out.println("first index = " +index);
						System.out.println("Times Clicked = " +timesClicked);
						buttonList.get(index).setVisible(false);
						buttonList.remove(buttonList.get(index));
						
					}else{
						if(j > index && index != 0) {
							System.out.println(j);
							index = j - timesClicked;
							System.out.println("Times Clicked = " +timesClicked);
							System.out.println(index);
							buttonList.get(index).setVisible(false);
							buttonList.remove(buttonList.get(index));
						}else {
							timesClicked ++;
							index = j;
							System.out.println(j);
							System.out.println("first index = " +index);
							System.out.println("Times Clicked = " +timesClicked);
							buttonList.get(index).setVisible(false);
							buttonList.remove(buttonList.get(index));
						}
					}


				}
			}));
		}
		for(int a = 0; a < buttonList.size(); a++) {
			viewObjects.add(buttonList.get(a));
		}
	}
}


