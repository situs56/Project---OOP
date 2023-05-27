package src.Gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import src.Entities.Player;
import src.Entities.TrapManager;
import src.Game.Game;
import src.Level.Level;
import src.Object.Carrots;
import src.Object.ObjectManager;
import src.Object.Platform;
import src.Object.Platform2;
import src.UI.GameOverOverlay;
import src.UI.PauseOverlay;

public class Playing extends State implements Methods {

    private Platform platform;
    private Carrots carrot;
    private Platform2 platform2;
    private Player player;
    private Level level;
    private GameOverOverlay gameOverOverlay;
    private PauseOverlay pauseOverlay;
    private ObjectManager objectManager;
    private TrapManager trapManager;
    private Timer timer;
    private TimerTask trapTask;

    private static long lastSpawn = System.currentTimeMillis();

    private boolean paused = false;
    private boolean gameOver;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    public void initClasses() {
        level = new Level();
        objectManager = new ObjectManager();
        trapManager = new TrapManager();
        gameOverOverlay = new GameOverOverlay(this);
        pauseOverlay = new PauseOverlay(this);

        createTrap();

        level.createCarrot();
        level.createPlatform();
        level.createPlatform2();

        player = new Player(Game.game_Width / 2, Game.game_Height / 2, 32, 32, this);

        objectManager.addScore();
        objectManager.addTimer();

    }

    public void createTrap() {
        level.createArrow(20, 100);
        timer = new Timer();
        scheduleTrapTask();
    }

    private void scheduleTrapTask() {
        trapTask = new TimerTask() {
            @Override
            public void run() {
                if (!paused) {
                    int randomTrap = (int) (Math.random() * 3);
    
                    switch (randomTrap) {
                        case 0:
                            level.createSaw();
                            break;
                        case 1:
                            level.createCannon();
                            break;
                        case 2:
                            level.createBall();
                            break;
                    }
                }
            }
        };
    
        timer.scheduleAtFixedRate(trapTask, 0, 10000);
    }

    @Override
    public void update() {
       
        if (paused) 
            objectManager.pauseTimer();

        if (gameOver) {
            objectManager.resetTimer();
            trapTask.cancel();
            timer.cancel();
        }

        if (!gameOver) {
            if (!paused) {
                Level.update();
                player.updatePlayer();
                objectManager.continueTimer();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        player.Render(g);
        Level.draw(g);

        if (gameOver)
            gameOverOverlay.draw(g);

        if (paused)
            pauseOverlay.draw(g);
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
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                if (!gameOver) {
                    if (paused) {
                        pauseGame();
                    }
                } else {
                    unpauseGame();
                }
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

    public void checkArrowTouched(Player player) {
        trapManager.checkArrowTouched(player);
    }

    public void checkBulletTouched(Player player) {
        trapManager.checkBulletTouched(player);
    }

    public void checkBallTouched(Player player) {
        trapManager.checkBallTouched(player);
    }

    public void checkSawTouched(Player player) {
        trapManager.checkSawTouched(player);
    }

    public void resetAll() {
        gameOver = false;

        player.resetAll();

        trapManager.clear();

        createTrap();

        objectManager.resetScore();
        objectManager.resetTimer();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mousePressed(e);
            }
        } else
            gameOverOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (paused)
                pauseOverlay.mouseReleased(e);
        } else
            gameOverOverlay.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }

    }

    public void unpauseGame() {
        paused = false;
        if (!gameOver) {
            trapTask.cancel();
            scheduleTrapTask();
        }
        objectManager.continueTimer();
    }

    private void pauseGame() {
        paused = true;
        trapTask.cancel();
        objectManager.pauseTimer();
    }
}
