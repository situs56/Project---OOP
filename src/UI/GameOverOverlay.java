package src.UI;

import src.Gamestate.Gamestate;
import src.Gamestate.Playing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class GameOverOverlay {

	private Playing playing;
	private UrmButton menuB, replayB;

	public GameOverOverlay(Playing playing) {
		this.playing = playing;
		createUrmButtons();
	}

	private void createUrmButtons() {
		int menuX = (int) (200);
		int replayX = (int) (400);
		int bY = (int) (250);
		menuB = new UrmButton(menuX, bY, 128, 128, 2);
		replayB = new UrmButton(replayX, bY, 128, 128, 3);
	}

	public void update() {
		menuB.update();
		replayB.update();
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0.5f));
		g.fillRect(0, 0, game_Width, game_Height);
		menuB.draw(g);
		replayB.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB)) {
			menuB.setMousePressed(true);
		} else if (isIn(e, replayB)) {
			replayB.setMousePressed(true);
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
			menuB.resetBools();
			replayB.resetBools();
		}
	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
