package src.UI;

import java.awt.Color;
import java.awt.Graphics;
import src.Game.Game;

public class UrmButton extends PauseButton {

	private int rowIndex, index;
	private boolean mouseOver, mousePressed;

    public static final int size = 50;

	public UrmButton(int x, int y, int width, int height, int rowIndex) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
	}



	/*public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;

	}*/

	public void draw(Graphics g) {
		g.drawRect(x, y, size, size);
			g.setColor(Color.RED); 
			g.fillRect(x, y, size, size);
		}
		

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
}
