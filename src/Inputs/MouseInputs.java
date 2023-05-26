package src.Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import src.Gamestate.Gamestate;
import src.Game.gamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private gamePanel gamePanel;

	public MouseInputs(gamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (Gamestate.state) {
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseDragged(e);
				break;
			default:
				break;
			// case OPTIONS -> GamePanel.getGame().getGameOptions().mouseDragged(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mouseMoved(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseMoved(e);
				break;
			default:
				break;
			// case OPTIONS -> gamePanel.getGame().getGameOptions().mouseMoved(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Gamestate.state) {
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseClicked(e);
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mousePressed(e);
				break;
			case PLAYING:
				System.out.print("dd");
				gamePanel.getGame().getPlaying().mousePressed(e);
				break;
			default:
				break;
			// case OPTIONS -> gamePanel.getGame().getGameOptions().mousePressed(e);
			// break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().mouseReleased(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseReleased(e);
				break;
			default:
				break;
			// case OPTIONS -> gamePanel.getGame().getGameOptions().mouseReleased(e);
			// break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not In use
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not In use
	}

}
