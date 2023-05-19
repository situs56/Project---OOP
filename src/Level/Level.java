package src.Level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import src.Entities.Arrow;
import src.Entities.TrapManager;
import src.Object.Platform;
import src.Object.Platform2;


public class Level {

    private static List<Platform2> platforms;
    private static TrapManager trapManager;
    

    public Level() {
        platforms = new ArrayList<>();
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
        Platform.drawPlatform(g);

        for (Platform2 platform : platforms) {
            platform.drawHitBox(g);
        }
    }

    public static void addPlatform(float x, float y, int height, int width) {
        Platform2 platform2 = new Platform2(x, y, height, width);
        platforms.add(platform2);
        Platform2.addPlatform2Hitbox(platform2.getHitBox());
    }

    public TrapManager getTrapManager() {
        return trapManager;
    }
}
