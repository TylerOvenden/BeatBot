package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mainGame.screens.GameScreen;

/**
 * This class will be used to create actions that can resume the game when the configured key is pressed
 * 
 * @author Justin Yau
 *
 */
public class Resume extends AbstractAction {

	/**
	 * Constructor creates a action that can be used to resume the game 
	 * 
	 * @author Justin Yau
	 */
	public Resume() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameScreen.game.resumeGame();
	}

}
