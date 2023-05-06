
package src.Game;

import java.awt.Graphics;

import src.Entities.Player;

public class Game implements Runnable {
    
    private gamePanel panel;
    private gameWindow window;
    private Thread gameLoopThread;
    private Player player;
    private final int FPS = 60;
    private final int UPS = 100;

    public Game() {
        initClasses();

        panel = new gamePanel(this);
        window = new gameWindow(panel);       
        panel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update() {
        player.updatePos();
    }

    public void render(Graphics g) {
        player.Render(g);
    }

    public void initClasses() {
        player = new Player(200, 200);
    }

    @Override
    public void run() {
        // UPS = update events
        // FPS = render game, level, player..

        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;

        double deltaF = 0;
        double deltaU = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaF += ((currentTime - previousTime) / timePerFrame);
            deltaU += ((currentTime - previousTime) / timePerUpdate);

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}
