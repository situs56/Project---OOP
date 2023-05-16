package src.Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.Game.Game;

public class TrapManager {

    private Random random;

    private static List<Arrow> arrows;
    private static List<Bullet> bullets;

    public TrapManager() {
        arrows = new ArrayList<>();
        bullets = new ArrayList<>();
        random = new Random();
    }

    public void addArrow(int width, int height) {
        arrows.add(new Arrow(random.nextInt(Game.game_Width - width), -(random.nextInt(300) + 100), width, height, this));
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeArrow(Arrow arrow) {
        arrows.remove(arrow);
    }

    public static void updateTraps() {
        for (Arrow arrow : arrows) {
            arrow.UpdateArrow();
        }

        for (Bullet bullet : bullets) {
            bullet.UpdateBullet();
        }
    }

    public static void drawTraps(Graphics g) {
        for (Arrow arrow : arrows) {
            arrow.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }
}
