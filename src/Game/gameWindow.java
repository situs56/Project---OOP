package src.Game;

import javax.swing.JFrame;

public class gameWindow {

    private JFrame jFrame;

    public gameWindow(gamePanel panel) {

        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(panel);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
