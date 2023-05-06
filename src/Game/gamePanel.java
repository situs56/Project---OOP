package src.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        Dimension size = new Dimension(1000, 1000);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        // g.drawRect(100, 100, 50, 50);
    }

    public Game getGame() {
        return game;
    }
}
