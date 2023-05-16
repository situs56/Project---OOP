package src.Level;

import java.awt.Graphics;

import src.Entities.Arrow;
import src.Entities.Bullet;
import src.Entities.TrapManager;
import src.Game.Game;
import src.Object.Platform;


public class Level {


    private static TrapManager trapManager;

    public Level() {
        trapManager = new TrapManager();
    }

    public void createArrow(int width, int height) {
        trapManager.addArrow(width, height);
    }

    public void removeArrow(Arrow arrow) {
        trapManager.removeArrow(arrow);
    }

    public static void update() {
        trapManager.updateTraps();
    }

    public static void draw(Graphics g) {
        trapManager.drawTraps(g);
    }

    public TrapManager getTrapManager() {
        return trapManager;
    }
}
