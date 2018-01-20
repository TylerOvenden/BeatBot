package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import gui.interfaces.Visible;
import mainGame.components.Holdstroke;
import mainGame.components.Keystroke;
import mainGame.screens.GameScreen;

public class Press extends AbstractAction {

	private int columnLane;
	
	public Press(int column) {
		columnLane = column;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameScreen.game.currentlyHeldLanes().contains(columnLane)) {
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
	
	public void handleKeystroke(Keystroke str) {
		if(str.distanceFromGoal() <= GameScreen.distanceG) {
			if(str.getColumnLane() != columnLane) {
				GameScreen.game.removeStroke(str);
				//Calculate Miss
			}
			GameScreen.game.removeStroke(str);
			//Calculate Accuracy
		}
	}
	
	public void handleHoldstroke(Holdstroke str) {
		if(str.distanceFromGoal() <= GameScreen.distanceG) {
			if(str.getColumnLane() != columnLane) {
				GameScreen.game.removeHoldStroke(str);
				//Calculate Miss
			}
			GameScreen.game.holds.add(str);
			//Calculate First Stroke Accuracy
		}
	}

}
