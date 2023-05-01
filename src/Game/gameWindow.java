package src.Game;

import javax.swing.JFrame;

public class gameWindow {
    public gameWindow() {
        gamePanel panel = new gamePanel();

        JFrame applicationFrame = new JFrame();

        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.add(panel);
        applicationFrame.setSize(300, 300);
        applicationFrame.setVisible(true);
    }
}
