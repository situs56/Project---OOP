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
    private Timer timer;
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

    public void addCarrot(float x, float y, int width, int height) {
        Carrots newCarrot = new Carrots(x, y, height, width);
        carrots.add(newCarrot);
    }

    public void addScore() {
        score = new Score(10, 30, new Font("Arial", Font.BOLD, 20), Color.BLACK);
    }

    public void addTimer() {
        timer = new Timer(Game.game_Width - 200, 30, new Font("Arial", Font.BOLD, 20), Color.green);
        timer.startTimer();
    }

    public void addPlatform(float x, float y, int width, int height) {
        platform = new Platform(x, y, width, height);
        platforms.add(platform);
        Platform.addPlatformHitbox(platform.getHitBox());
    }

    public static void addPlatform2(float x, float y, int width, int height) {
        Platform2 platform2 = new Platform2(x, y, width, height);
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

    public void resetScore() {
        score.reset();
    }

    public void resetTimer() {
        timer.startTimer();
    }

    public void checkCarrotTouched(Player player) {
        for (Carrots c : getCarrots())
            if (c.getHitBox().intersects(player.getHitBox())) {
                removeCarrot(c);
                RandomCarrots();
                score.ScoreIncrease(1);
            }
    }

    public void RandomCarrots() {
        int random = (int) (Math.random() * 10);

        switch (random) {
            case 0:
                addCarrot(248, 340, 32, 32);
                break;
            case 1:
                addCarrot(490, 340, 32, 32);
                break;
            case 2:
                addCarrot(80, 270, 32, 32);
                break;
            case 3:
                addCarrot(370, 270, 32, 32);
                break;
            case 4:
                addCarrot(656, 270, 32, 32);
                break;
            case 5:
                addCarrot(248, 210, 32, 32);
                break;
            case 6:
                addCarrot(490, 210, 32, 32);
                break;
            case 7:
                addCarrot(370, 120, 32, 32);
                break;
            case 8:
                addCarrot(80, 120, 32, 32);
                break;
            case 9:
                addCarrot(656, 120, 32, 32);
                break;
        }
    }
}
