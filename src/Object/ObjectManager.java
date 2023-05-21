package src.Object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import src.Entities.Player;
import src.Game.Game;

public class ObjectManager {

    private Score score;
    private Random random;
    private Player player;
    private Platform platform;
    private static List<Carrots> carrots;
    private static List<Platform> platforms;
    private static List<Platform2> platform2s;

    public ObjectManager() {
        carrots = new ArrayList<>();
        platforms = new ArrayList<>();
        platform2s = new ArrayList<>();
        random = new Random();
    }

    public void updateObject() {

    }

    public void drawObject(Graphics g) {
        for (Platform platform : platforms) {
            platform.drawHitBox(g);
        }

        for (Carrots carrot : carrots) {
            carrot.drawCarrot(g);
        }

        for (Platform2 platform2 : platform2s) {
            platform2.drawHitBox(g);
        }
    }

    public void addCarrot(int width, int height) {
        Carrots newCarrot = new Carrots(random.nextInt(Game.game_Width - width), 300, width, height);
        carrots.add(newCarrot);
    }

    public void addScore() {
        score = new Score(10, 30, new Font("Arial", Font.BOLD, 20), Color.BLACK);
    }

    public void addPlatform(float x, float y, int height, int width) {
        platform = new Platform(x, y, height, width);
        platforms.add(platform);
        Platform.addPlatformHitbox(platform.getHitBox());
    }

    public static void addPlatform2(float x, float y, int height, int width) {
        Platform2 platform2 = new Platform2(x, y, height, width);
        platform2s.add(platform2);
        Platform2.addPlatform2Hitbox(platform2.getHitBox());
    }

    public void removeCarrot(Carrots carrot) {
        carrots.remove(carrot);
    }

    public static List<Carrots> getCarrots() {
        return carrots;
    }

    public Score getScore() {
        return score;
    }

    public void checkCarrotTouched(Player player) {
        for (Carrots c : getCarrots())
            if (c.getHitBox().intersects(player.getHitBox())) {
                removeCarrot(c);
                addCarrot(32, 32);
                Score.ScoreIncrease(1);
            }
    }
}
