package src.Gamestate;

import java.awt.event.MouseEvent;

import src.Game.Game;
import src.UI.MenuButton;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }

    public void setGamestate(Gamestate state) {
        switch (state) {
            case MENU -> game.getAudio().playMenuSound();
            case PLAYING -> game.getAudio().playPlayingSound();
        }

        Gamestate.state = state;
    }
}
