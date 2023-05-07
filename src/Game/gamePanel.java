package src.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

import javax.swing.JPanel;

import src.Inputs.KeyboardInputs;

public class gamePanel extends JPanel {

    private Game game;

    public gamePanel(Game game) {
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    public void setPanelSize() {
        Dimension size = new Dimension(game_Width, game_Height);
        setPreferredSize(size);
        System.out.println("Height: " + game_Height + "Width: " + game_Width);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
