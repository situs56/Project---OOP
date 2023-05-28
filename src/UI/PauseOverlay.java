package src.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.Gamestate.Gamestate;
import src.Gamestate.Playing;
import src.audio.Audio;
import src.Game.Game;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class PauseOverlay {

	private Game game;
	private Audio audio;
	private BufferedImage img;
	private Playing playing;
	private UrmButton menuB, replayB, unpauseB;
	private VolumeButton volumeButton;

	public PauseOverlay(Playing playing, Game game) {
		this.playing = playing;
		this.game = game;
		createUrmButtons();
		createVolumeButton();
		ImportImg();
	}

	private void createVolumeButton() {
		int vX = (int) (210);
		int vY = (int) (175);
		volumeButton = new VolumeButton(vX, vY, 350, 60);
	}

	private void createUrmButtons() {
		menuB = new UrmButton(195, 256, 128, 128, 2);
		replayB = new UrmButton(325, 256, 128, 128, 3);
		unpauseB = new UrmButton(448, 258, 128, 128, 0);
	}

	public void update() {
		menuB.update();
		replayB.update();
		unpauseB.update();
	}

	public void draw(Graphics g) {
		g.drawImage(img, 192, 128, game_Width / 2, game_Height / 2, null);
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		volumeButton.draw(g);
	}

	public void ImportImg() {
		InputStream is = getClass().getResourceAsStream("/res/pause.png");
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();
			volumeButton.changeX(e.getX());
			float valueAfter = volumeButton.getFloatValue();
			if (valueBefore != valueAfter) {
				game.getAudio().setVolume(valueAfter);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if (isIn(e, volumeButton)) {
			volumeButton.setMousePressed(true);
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				playing.resetAll();
				playing.setGamestate(Gamestate.MENU);
				playing.unpauseGame();
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed()) {
				playing.resetAll();
				playing.unpauseGame();
			}
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumeButton.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
