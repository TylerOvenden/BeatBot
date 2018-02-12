package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import gui.interfaces.Visible;
import mainGame.components.Holdstroke;
import mainGame.components.Keystroke;
import mainGame.screens.GameScreen;

/**
 * This class will be used to create "presses" that deals with the stroke calculations and removal for accuracy <br>
 * when the configured key is press.
 * 
 * @author Justin Yau
 *
 */
public class Press extends AbstractAction {

	private int columnLane; //The lane the press is meant to be for
	
	/**
	 * Constructor creates a "press" that deals with stroke calculations and removal for accuracy <br>
	 * when the configured key is pressed.
	 * 
	 * @param column - The lane that the press is meant to be for
	 * 
	 * @author Justin Yau
	 */
	public Press(int column) {
		columnLane = column;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameScreen.game.currentlyHeldLanes().contains(columnLane) || GameScreen.game.currentTooLongLanes().contains(columnLane) || GameScreen.game.getPause()) {
			return;
		}
		Visible stroke = GameScreen.game.getFirstInLane(columnLane);
		if(stroke != null) {
			if(stroke instanceof Keystroke) {
				Keystroke str = ((Keystroke)stroke);
				handleKeystroke(str);
			}
			if(stroke instanceof Holdstroke) {
				Holdstroke str = ((Holdstroke)stroke);
				handleHoldstroke(str);
			}
		}
	}
	
	/**
	 * This method handles the accuracy calculations and removal of the stroke
	 * 
	 * @param str - The stroke to do the calculations on
	 * 
	 * @author Justin Yau
	 */
	public void handleKeystroke(Keystroke str) {
		if(str.distanceFromGoal() <= GameScreen.distanceG) {
			GameScreen.game.removeStroke(str);
			//Calculate Accuracy
			GameScreen.game.getTiming().calculateAccuracy(str);
		}
	}
	
	/**
	 * This method handles the accuracy calculations and removal of the stroke
	 * 
	 * @param str - The stroke to do the calculations on
	 * 
	 * @author Justin Yau
	 */
	public void handleHoldstroke(Holdstroke str) {
		if(str.distanceFromGoal() <= GameScreen.distanceG) {
			GameScreen.game.removeFromStrokes(str);
			GameScreen.game.addHold(str);
			str.setHeld(true);
			//Calculate First Stroke Accuracy
			GameScreen.game.getTiming().calculateFirstAccuracy(str);
		}
	}

}
