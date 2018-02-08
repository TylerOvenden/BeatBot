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

	public HighscoreScreen(int width, int height,boolean isWin,int score,double acc,Highscore song, ArrayList<Integer> ints,ArrayList<Float> arrayList) {
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
		int temp;
		int idx=0;
		temp=ints.get(0);
		for(int i=0;i<ints.size();i++) {
			if(ints.get(i)>temp) {
				temp=ints.get(i);
				idx=i;
			}
		}
		score1.setText("1.  "+temp+"   "+arrayList.get(idx)+"%");
		ints.remove(idx);
		arrayList.remove(idx);
		if(ints.size()>0) {
			temp=ints.get(0);
			idx=0;
			for(int i=0;i<ints.size();i++) {
				if(ints.get(i)>temp) {
					temp=ints.get(i);
					idx=i;
				}
			}
			score2.setText("1.  "+temp+"   "+arrayList.get(idx)+"%");
			ints.remove(idx);
			arrayList.remove(idx);
		}else {
			score2.setText("2.                                  ");
		}
		if(ints.size()>0) {
			temp=ints.get(0);
			idx=0;
			for(int i=0;i<ints.size();i++) {
				if(ints.get(i)>temp) {
					temp=ints.get(i);
					idx=i;
				}
			}
			score3.setText("1.  "+temp+"   "+arrayList.get(idx)+"%");
			ints.remove(idx);
			arrayList.remove(idx);
		}else {
			score3.setText("3.                                  ");
		}
	}

	public void initAllObjects(List<Visible> viewObjects) {
		bg=new Graphic(0, 0,4, "resources/background1.png");
		viewObjects.add(bg);
		condition=new CustomText(400, 100, 100, 100, "You Lose",true);
		viewObjects.add(condition);
		currentScore=new CustomText(380, 120, 200, 200, "Score "+score,true);
		viewObjects.add(currentScore);
		System.out.println(acc);
		currentAcc=new CustomText(380, 140, 200, 200, "",true);
		viewObjects.add(currentAcc);
		highScore=new CustomText(380, 180, 200, 200, "High Scores",true);
		viewObjects.add(highScore);
		score1=new CustomText(380, 210, 200, 200, "",true);
		viewObjects.add(score1);
		score2=new CustomText(380, 240, 200, 200, "",true);
		viewObjects.add(score2);
		score3=new CustomText(380, 270, 200, 200, "",true);
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
