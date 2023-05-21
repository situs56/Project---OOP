package src.Gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.Entities.Player;
import src.Game.Game;
import src.Level.Level;
import src.Object.Carrots;
import src.Object.ObjectManager;
import src.Object.Platform;
import src.Object.Platform2;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Playing extends State implements Methods {

    private Platform platform;
    private Carrots carrot;
    private Platform2 platform2;
    private Player player;
    private Level level;
    private ObjectManager objectManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    public void initClasses() {
        level = new Level();
        objectManager = new ObjectManager();
        
        level.createArrow(32, 32);

        player = new Player(Game.game_Width / 2, Game.game_Height / 2, 64, 64, this);

        objectManager.addPlatform(0, game_Height - (game_Height/5), game_Height/5, game_Width);

        objectManager.addPlatform2(200, 270, 100, 20);
        objectManager.addPlatform2(500, 350, 100, 20);
        objectManager.addPlatform2(300, 300, 100, 20);

        level.createCarrot(32, 32);

        objectManager.addScore();

    }

    @Override
    public void update() {
        Level.update();
        player.updatePlayer();
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

	public void checkCarrotTouched(Player player) {
        objectManager.checkCarrotTouched(player);
	}
}
