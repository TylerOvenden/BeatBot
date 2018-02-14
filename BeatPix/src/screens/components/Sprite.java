package screens.components;

import java.awt.image.BufferedImage;

import gui.components.AnimatedComponent;
import gui.interfaces.Visible;
import mainGame.MainGUI;

public class Sprite implements Visible {

	private int x;
	private int y;
	private int w;
	private int h;
	private AnimatedComponent[] moves;
	private AnimatedComponent currentMove;
	private boolean visible;
	private float alpha;
	private String rsrcFile;
	private String skin;
	private AnimatedComponent robotIdle;
	private AnimatedComponent robotHit1;
	private AnimatedComponent robotHit2;
	private AnimatedComponent robotHit3;
	private AnimatedComponent robotMiss;
	private Thread run;
	
	public Sprite() {
		// TODO Auto-generated constructor stub
		AnimatedComponent[] move = {robotHit1, robotHit2, robotHit3};
		robotIdle.addSequence(rsrcFile, 200, 0, 0, 39, 28, 2);
		robotMiss.addSequence(rsrcFile, 200, 0, 146, 39, 27, 2);
		this.moves = move;
	}
	
	public void startThread()
	{
		this.setVisible(true);
		run.start();
	}
	public void initThread()
	{
		for(int i = 0; i < moves.length; i++)
		{
			moves[i] = new AnimatedComponent(30, 100, 117, 84);
			moves[i].setVisible(false);
		}
	}

	public void changeSkin()
	{
		skin = MainGUI.test.character.getSkin();
		if(skin == "default")
			rsrcFile = "resources/sprites/defaultSprite_Transparent.png";
		if(skin == "red")
			rsrcFile = "resources/sprites/redSprite_Transparent.png";
		if(skin == "white")
			rsrcFile = "resources/sprites/whiteSprite_Transparent.png";
		if(skin == "green")
			rsrcFile = "resources/sprites/greenSprite_Transparent.png";
	}
	
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return currentMove.getImage();
	}

	public int getX() {
		return x;
	}

	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Set the pixel distance from the top side of the container
	 */
	public void setY(int y){
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		this.visible = b;
	}

	public boolean isVisible() {
		return visible;
	}

	public float getAlpha() {
		return alpha;
	}

	@Override
	public void setAlpha(float f) {
		// TODO Auto-generated method stub
		this.alpha = f;
	}

	@Override
	public void unhoverAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hoverAction() {
		// TODO Auto-generated method stub

	}

}
