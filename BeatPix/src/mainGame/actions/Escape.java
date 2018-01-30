package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mainGame.screens.GameScreen;

/**
 * This class will contain the action associated with pressing the escape button
 * @author Justin Yau
 *
 */
public class Escape extends AbstractAction {

	/**
	 * Constructor creates an action associated with pressing the escape button
	 * 
	 * @author Justin Yau
	 */
	public Escape() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!GameScreen.game.getPause()) {
			GameScreen.game.pauseGame();
		}
		else {
			GameScreen.game.resumeGame();
		}
	}

}
