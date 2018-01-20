package mainGame.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mainGame.screens.GameScreen;

public class Resume extends AbstractAction {

	public Resume() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameScreen.game.resumeGame();
	}

}
