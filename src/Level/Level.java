package src.Level;

import java.awt.Graphics;

import src.Entities.Arrow;
import src.Entities.TrapManager;
import src.Object.Carrots;
import src.Object.ObjectManager;
import src.Object.Score;
import src.Object.Timer;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Level {

    private static TrapManager trapManager;
    private static ObjectManager objectManager;

    public Level() {
        trapManager = new TrapManager();
        objectManager = new ObjectManager();
    }

    public static void update() {
        trapManager.updateTraps();
    }

    public static void draw(Graphics g) {
        trapManager.drawTraps(g);
        objectManager.drawObject(g);
        Score.draw(g);
        Timer.draw(g);
    }

    public void createArrow(int width, int height) {
        trapManager.addArrow(width, height);
    }

    public void createCannon() {
        randomCannons();
    }

    public void createSaw() {
        randomSaws();
    }

    public void createBall() {
        randomStar();
    }

    public void createCarrot() {
        objectManager.RandomCarrots();
    }

    public void createPlatform() {
        objectManager.addPlatform(0, game_Height - (game_Height / 5), game_Width, game_Height / 5);
    }

    public void createPlatform2() {
        objectManager.addPlatform2(160, 330, 96, 20);
        objectManager.addPlatform2(576, 330, 96, 20);
        objectManager.addPlatform2(336, 330, 96, 20);
        objectManager.addPlatform2(214, 250, 96, 20);
        objectManager.addPlatform2(458, 250, 96, 20);
        objectManager.addPlatform2(160, 170, 96, 20);
        objectManager.addPlatform2(576, 170, 96, 20);
        objectManager.addPlatform2(336, 170, 96, 20);
    }

    public void removeCarrot(Carrots carrot) {
        objectManager.removeCarrot(carrot);
    }

    public void removeArrow(Arrow arrow) {
        trapManager.removeArrow(arrow);
    }

    public TrapManager getTrapManager() {
        return trapManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void randomCannons() {
        int random1 = (int) (Math.random() * 4);

        switch (random1) {
            case 0:
                trapManager.addCannon(0, 100, 32, 32);
                break;
            case 1:
                trapManager.addCannon(0, 200, 32, 32);
                break;
            case 2:
                trapManager.addCannon(game_Width, 200, 32, 32);
                break;
            case 3:
                trapManager.addCannon(game_Width, 100, 32, 32);
                break;
        }
    }

    public void randomSaws() {
        int random2 = (int) (Math.random() * 4);

        switch (random2) {
            case 0:
                trapManager.addSaw(0, 10, 32, 32);
                break;
            case 1:
                trapManager.addSaw(game_Width, 10, 32, 32);
                break;
            case 2:
                trapManager.addSaw(0, 50, 32, 32);
                break;
            case 3:
                trapManager.addSaw(0, 60, 32, 32);
                break;
        }
    }

    public void randomStar() {
        int random3 = (int) (Math.random() * 4);

        switch (random3) {
            case 0:
                trapManager.addStar(0, 0, 32, 32);
                break;
            case 1:
                trapManager.addStar(game_Width, 0, 32, 32);
                break;
            case 2:
                trapManager.addStar(0, game_Height, 32, 32);
                break;
            case 3:
                trapManager.addStar(game_Width, game_Height, 32, 32);
                break;
        }
    }
}
