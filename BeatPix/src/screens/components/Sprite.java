package screens.components;

import java.awt.image.BufferedImage;

import gui.components.AnimatedComponent;
import gui.interfaces.Visible;

public class Sprite implements Visible {

	private int x;
	private int y;
	private int w;
	private int h;
	private AnimatedComponent[] moves;
	private AnimatedComponent currentMove;
	private boolean visible;
	private float alpha;
	
	public Sprite() {
		// TODO Auto-generated constructor stub
		AnimatedComponent[] move = {new AnimatedComponent(30, 100, 117, 84),
		new AnimatedComponent(30, 100, 117, 84),
		new AnimatedComponent(30, 100, 117, 84),
		new AnimatedComponent(30, 100, 117, 84),
		new AnimatedComponent(30, 100, 117, 84)};
		this.moves = move;
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
