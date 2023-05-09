package src.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.Game.gamePanel;
import src.Gamestate.Gamestate;

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
        switch (Gamestate.state) {
            case MENU: 
                panel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU: 
                panel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
