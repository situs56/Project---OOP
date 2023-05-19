package src.Object;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import src.Entities.Player;
import src.Game.Game;

public class ObjectManager {

    private Random random;
    private Player player;
    private static List<Carrots> carrots;
    private static List<Platform2> platforms;

    public ObjectManager() {
        carrots = new ArrayList<>();
        platforms = new ArrayList<>();
        random = new Random();
    }

    public void addCarrot(int width, int height) {
        carrots.add(new Carrots(random.nextInt(Game.game_Width - width), 300, width, height));
    }

    public void drawObject(Graphics g) {
        for (Carrots carrot : carrots) {
            carrot.drawCarrot(g);
        }

        for (Platform2 platform : platforms) {
            platform.drawHitBox(g);
        }
    }

    public static void addPlatform(float x, float y, int height, int width) {
        Platform2 platform2 = new Platform2(x, y, height, width);
        platforms.add(platform2);
        Platform2.addPlatform2Hitbox(platform2.getHitBox());
    }

    public void removeCarrot(Carrots carrot) {
        carrots.remove(carrot);
    }

    public void checkCarrotHit(Carrots carrot) {
        if (carrot.getHitBox().intersects(player.getHitBox()))
            removeCarrot(carrot);
            addCarrot(0, 0);
    }

}
