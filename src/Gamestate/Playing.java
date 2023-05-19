package src.Gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.Entities.Player;
import src.Game.Game;
import src.Level.Level;
import src.Object.Platform2;

public class Playing extends State implements Methods {

    private Platform2 platform2;
    private Player player;
    private Level level;    

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    public void initClasses() {
        player = new Player(Game.game_Width / 2, Game.game_Height / 2, 64, 64);
        level = new Level();
        level.createArrow(32, 32);
        Level.addPlatform(200, 270, 100, 20);
        Level.addPlatform(500, 350, 100, 20);
        Level.addPlatform(300, 300, 100, 20);
    }

    @Override
    public void update() {
        player.updatePlayer();
        Level.update();
    }

    @Override
    public void draw(Graphics g) {
        player.Render(g);
        Level.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                // player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                // player.setDown(false);
        }
    }

    public Player getPlayer() {
        return player;
    }

}
