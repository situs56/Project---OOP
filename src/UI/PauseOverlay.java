package src.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import src.Gamestate.Gamestate;
import src.Gamestate.Playing;
import src.Game.Game;

public class PauseOverlay {
	private Playing playing;
	private UrmButton menuB, replayB, unpauseB;
	private VolumeButton volumeButton;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		createUrmButtons();
		createVolumeButton();
	}

	private void createVolumeButton() {
		int vX = (int) (200);
		int vY = (int) (100);
		volumeButton = new VolumeButton(vX, vY, 350, 44);
	}

	private void createUrmButtons() {
		int menuX = (int) (100);
		int replayX = (int) (387);
		int unpauseX = (int) (600);
		int bY = (int) (325);
		menuB = new UrmButton(menuX, bY, 50, 50, 2);
		replayB = new UrmButton(replayX, bY, 50, 50, 1);
		unpauseB = new UrmButton(unpauseX, bY, 50, 50, 0);
	}

	public void update() {

	}

	public void draw(Graphics g) {
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		volumeButton.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
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
