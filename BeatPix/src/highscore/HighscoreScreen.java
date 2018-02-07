package highscore;

import java.util.ArrayList;
import java.util.List;

import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.components.CustomText;
import mainGame.components.Song;

public class HighscoreScreen extends FullFunctionScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isWin;
	private int score;
	private double acc;
	public ArrayList<Song> songList;
	public CustomText condition;
	public CustomText currentScore;
	public CustomText currentAcc;

	public HighscoreScreen(int width, int height,boolean isWin,int score,double acc) {
		super(width, height);
		this.isWin=isWin;
		this.score=score;
		this.acc=acc;
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		condition=new CustomText(400, 100, 100, 100, "You Lose");
		viewObjects.add(condition);
		if(!isWin) {
			condition.setText("You Win");
		}
		currentScore=new CustomText(400, 200, 100, 100, "Score: "+score);
		viewObjects.add(currentScore);
	}

}
