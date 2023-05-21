package src.Level;

import java.awt.Graphics;

import src.Entities.Arrow;
import src.Entities.Player;
import src.Entities.TrapManager;
import src.Object.Carrots;
import src.Object.ObjectManager;
import src.Object.Platform;
import src.Object.Score;

public class Level {

    private static TrapManager trapManager;
    private static ObjectManager objectManager;

    public Level() {
        trapManager = new TrapManager();
        objectManager = new ObjectManager();
    }

    public void createArrow(int width, int height) {
        trapManager.addArrow(width, height);
    }

    public void removeArrow(Arrow arrow) {
        trapManager.removeArrow(arrow);
    }

    public void createCarrot(int width, int height) {
        objectManager.addCarrot(width, height);
    }

    public void removeCarrot(Carrots carrot) {
        objectManager.removeCarrot(carrot);
    }

    public static void update() {
        trapManager.updateTraps();
    }

    public static void draw(Graphics g) {
        trapManager.drawTraps(g);
        objectManager.drawObject(g);
        Score.draw(g);

    }

    public TrapManager getTrapManager() {
        return trapManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }
}
