package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mainGame.screens.GameScreen;

/**
 * This class will be mainly responsible for causing the game to pause when a certain key is pressed
 * 
 * @author Justin Yau
 *
 */
public class Pause extends AbstractAction {

	/**
	 * This constructor creates a new action that will be used to pause the current game
	 */
	public Pause() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameScreen.game.pauseGame();
	}

}
