package highscore;

import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.Graphic;
import gui.interfaces.Visible;
import gui.userInterfaces.FullFunctionScreen;
import mainGame.MainGUI;
import mainGame.components.CustomText;
import mainGame.components.Song;
import mainGame.components.interfaces.Highscore;
import mainGame.screens.GameScreen;
import mainGame.screens.interfaces.ResizableScreen;
import screens.components.ImageButton;

public class HighscoreScreen extends ResizableScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isWin;
	private int score;
	private double acc;
	private ArrayList<Song> songList;
	private Graphic bg;
	private CustomText condition;
	private CustomText currentScore;
	private CustomText currentAcc;
	private CustomText highScore;
	private CustomText score1;
	private CustomText score2;
	private CustomText score3;
	private ImageButton retry;
	private ImageButton menu;
	private Highscore song;

	public HighscoreScreen(int width, int height,boolean isWin,int score,double acc,Highscore song) {
		super(width, height);
		this.isWin=isWin;
		this.score=score;
		this.acc=acc;
		this.song=song;
		currentAcc.setText("Accuracy "+acc+"%");
		currentScore.setText("Score "+score);
		if(isWin) {
			condition.setText("You Win");
		}
		score1.setText("1.  "+score+"   "+acc+"%");
		score2.setText("1.  "+score+"   "+acc+"%");
		score3.setText("1.  "+score+"   "+acc+"%");
	}

	public void initAllObjects(List<Visible> viewObjects) {
		bg=new Graphic(0, 0,4, "resources/background1.png");
		viewObjects.add(bg);
		condition=new CustomText(400, 100, 100, 100, "You Lose");
		viewObjects.add(condition);
		currentScore=new CustomText(380, 120, 200, 200, "Score "+score);
		viewObjects.add(currentScore);
		System.out.println(acc);
		currentAcc=new CustomText(380, 140, 200, 200, "");
		viewObjects.add(currentAcc);
		highScore=new CustomText(380, 180, 200, 200, "High Scores");
		viewObjects.add(highScore);
		score1=new CustomText(380, 210, 200, 200, "");
		viewObjects.add(score1);
		score2=new CustomText(380, 240, 200, 200, "");
		viewObjects.add(score2);
		score3=new CustomText(380, 270, 200, 200, "");
		viewObjects.add(score3);
		retry=new ImageButton(500, 300, 120, 50, "resources/re.png");
		viewObjects.add(retry);
		retry.setAction(new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(new GameScreen(getWidth(), getHeight(), (Song) song));
				
			}
		});
		menu=new ImageButton(650, 300, 120, 50, "resources/menu.png");
		viewObjects.add(menu);
		menu.setAction(new Action() {
			
			@Override
			public void act() {
				MainGUI.test.setScreen(MainGUI.test.mainMenu);
				
			}
		});
	}
	
	
	
}
