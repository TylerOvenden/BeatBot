package mainGame.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mainGame.components.Holdstroke;
import mainGame.screens.GameScreen;

public class ReleasePress extends AbstractAction {

	private int columnLane;
	
	public ReleasePress(int column) {
		columnLane = column;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameScreen.game.currentlyHeldLanes().contains(columnLane)) {
			Holdstroke hold = retrieveFirstHoldInLane(columnLane, GameScreen.game.holds);
			if(hold != null) {
				GameScreen.game.removeHoldStroke(hold);
				//Calculate Accuracy
			}
		}
		if(retrieveFirstHoldInLane(columnLane, GameScreen.game.tooLongHolds) != null) {
			//Miss Accuracy
		}
	}
	
	public Holdstroke retrieveFirstHoldInLane(int columnLane, ArrayList<Holdstroke> list) {
		for(Holdstroke hold: list) {
			if(hold.getColumnLane() == columnLane) {
				return hold;
			}
		}
		return null;
	}

}
