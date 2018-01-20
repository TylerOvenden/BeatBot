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
		Visible stroke = GameScreen.game.getFirstInLane(columnLane);
		if(stroke != null) {
			if(stroke instanceof Keystroke) {
				Keystroke str = ((Keystroke)stroke);
				if(str.distanceFromGoal() <= GameScreen.distanceG) {
					if(str.getColumnLane() != columnLane) {
						GameScreen.game.removeStroke(str);
						//Calculate Miss
					}
					GameScreen.game.removeStroke(str);
					//Calculate Accuracy
				}
			}
			if(stroke instanceof Holdstroke) {
				Holdstroke str = ((Holdstroke)stroke);
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
	}

}
