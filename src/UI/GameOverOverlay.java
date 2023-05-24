package src.UI;

import src.Gamestate.Gamestate;
import src.Gamestate.Playing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class GameOverOverlay {

    private Playing playing;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, game_Width, game_Height);

        g.setColor(Color.red);
        g.drawString("Get Good", game_Height/2, game_Width/2);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            playing.resetAll();
            Gamestate.state =Gamestate.MENU;
        }
    }
}
