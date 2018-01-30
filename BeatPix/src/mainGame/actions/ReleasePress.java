package mainGame.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import mainGame.components.Holdstroke;
import mainGame.screens.GameScreen;

/**
 * This class will be used to create "released presses" that deals with the stroke calculations and removal for accuracy <br>
 * when the configured key is released.
 * 
 * @author Justin Yau
 *
 */
public class ReleasePress extends AbstractAction {

	private int columnLane; //The lane the press is meant to be for
	
	/**
	 * Constructor creates "released presses" that deals with the stroke calculations and removal for accuracy <br>
	 * when the configured key is released.
	 * 
	 * @param column - The lane the released press is supposed to be for
	 * 
	 * @author Justin Yau
	 *
	 */
	public ReleasePress(int column) {
		columnLane = column;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameScreen.game.currentlyHeldLanes().contains(columnLane)) {
			Holdstroke hold = retrieveFirstHoldInLane(columnLane, GameScreen.game.getHolds());
			if(hold != null) {
				GameScreen.game.removeHoldStroke(hold);
				//Calculate Accuracy
			}
		}
		if(retrieveFirstHoldInLane(columnLane, GameScreen.game.getTooLongHolds()) != null) {
			GameScreen.game.removeFromTooLongHolds(retrieveFirstHoldInLane(columnLane, GameScreen.game.getTooLongHolds()));
			//Miss Accuracy
		}
	}
	
	/**
	 * This method retrieves the first hold in the given column lane <br>
	 * Returns null if there are no more holds in the given lane
	 * 
	 * @param columnLane - The lane that you would like to retrieve the stroke from
	 * @param list - The arraylist of holdstrokes that you want to retrieve from
	 * @return Returns the first hold in the given column lane. Null if none. 
	 * 
	 * @author Justin Yau
	 */
	public Holdstroke retrieveFirstHoldInLane(int columnLane, ArrayList<Holdstroke> list) {
		for(Holdstroke hold: list) {
			if(hold.getColumnLane() == columnLane) {
				return hold;
			}
		}
		return null;
	}

}
