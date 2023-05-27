
package src.Game;

import java.awt.Graphics;

import src.Entities.Player;
import src.Gamestate.Gamestate;
import src.Gamestate.Menu;
import src.Gamestate.Playing;

public class Game implements Runnable {

    private gamePanel panel;
    private gameWindow window;
    private Thread gameLoopThread;
    private Player player;
    private final int FPS = 240;
    private final int UPS = 240;

    private Playing playing;
    private Menu menu;

    public final static int Tile_Size = 32;
    public final static float Scale = 2f;
    public final static int Tiles_in_height = 8;
    public final static int Tiles_in_width = 12;
    public final static int Tile_Actual_Size = (int) Scale * Tile_Size;
    public final static int game_Height = Tile_Actual_Size * Tiles_in_height;
    public final static int game_Width = Tile_Actual_Size * Tiles_in_width;

    public Game() {
        initClasses();
        panel = new gamePanel(this);
        window = new gameWindow(panel);
        panel.requestFocus();
        startGameLoop();
    }

    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }

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
            previousTime = currentTime;

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

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
    public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING)
			playing.getPlayer().resetDirBooleans();
	}
}
