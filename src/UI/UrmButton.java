package src.UI;

import java.awt.Color;
import java.awt.Graphics;
import src.Game.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class UrmButton extends PauseButton {

	private BufferedImage[][] animations;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;

	public UrmButton(int x, int y, int width, int height, int rowIndex) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
		loadAnimations();
	}

	private void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/res/button2.png");
		try {
			BufferedImage img = ImageIO.read(is);

			animations = new BufferedImage[4][2];
			for (int j = 0; j < 4; j++)
				for (int i = 0; i < 2; i++)
					animations[j][i] = img.getSubimage(i * 109, j * 100, 109, 100);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		index = 0;
		if (mousePressed)
			index = 1;
	}

	public void draw(Graphics g) {
		g.drawImage(animations[rowIndex][index], x, y, width, height, null);
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
