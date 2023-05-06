package src.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.Game.gamePanel;

public class KeyboardInputs implements KeyListener {

    private gamePanel panel;

    public KeyboardInputs(gamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                panel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                panel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                panel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                panel.getGame().getPlayer().setRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                panel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
                panel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_W:
                panel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_S:
                panel.getGame().getPlayer().setDown(false);
        }
    }
}
