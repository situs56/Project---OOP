package src.Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.Game.Game;

public class TrapManager {

    private Random random;
    private Player player;

    private static List<Arrow> arrows;
    private static List<Bullet> bullets;
    private static List<Cannon> cannons;
    private static List<Saw> saws;
    private static List<Star> stars;

    private static long lastShotTime = System.currentTimeMillis();

    public TrapManager() {
        arrows = new ArrayList<>();
        bullets = new ArrayList<>();
        cannons = new ArrayList<>();
        saws = new ArrayList<>();
        stars = new ArrayList<>();
        random = new Random();
    }

    public void updateTraps() {
        for (Arrow arrow : arrows) {
            arrow.UpdateArrow();
        }

        for (Bullet bullet : bullets) {
            bullet.UpdateBullet();
        }

        for (Saw saw : saws) {
            saw.updateSaw();
        }

        for (Star star : stars) {
            star.updateStar();
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= 5000) {
            CannonShooting();
            lastShotTime = currentTime;
        }
    }

    public void drawTraps(Graphics g) {
        for (Arrow arrow : arrows) {
            arrow.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        for (Saw saw : saws) {
            saw.draw(g);
        }

        for (Cannon cannon : cannons) {
            cannon.draw(g);
        }

        for (Star star : stars) {
            star.draw(g);
        }
    }

    public void addArrow(int width, int height) {
        arrows.add(
                new Arrow(random.nextInt(Game.game_Width - width), -(random.nextInt(300) + 100), width, height, this));
    }

    public void addBullet(float x, float y, int width, int height, int dir) {
        bullets.add(new Bullet(x, y, width, height, dir));
    }

    public void addSaw(float x, float y, int width, int height) {
        saws.add(new Saw(x, y, width, height, this));
    }

    public void addCannon(float x, float y, int width, int height) {
        cannons.add(new Cannon(x, y, width, height));
    }

    public void addStar(float x, float y, int width, int height) {
        stars.add(new Star(x, y, width, height));
    }

    public void CannonShooting() {
        for (Cannon cannon : getCannons()) {
            Shoot(cannon);
        }
    }

    public void Shoot(Cannon cannon) {
        if (cannon.getHitBox().x >= Game.game_Width)
            addBullet((int) cannon.getHitBox().x - 32, (int) cannon.getHitBox().y, 32, 32, -1);
        else
            addBullet((int) cannon.getHitBox().x, (int) cannon.getHitBox().y, 32, 32, 1);
    }

    public void removeArrow(Arrow arrow) {
        arrows.remove(arrow);
    }

    public static List<Arrow> getArrows() {
        return arrows;
    }

    public static List<Bullet> getBullets() {
        return bullets;
    }

    public static List<Saw> getSaws() {
        return saws;
    }

    public static List<Cannon> getCannons() {
        return cannons;
    }

    public static List<Star> getStars() {
        return stars;
    }

    public void checkArrowTouched(Player player) {
        for (Arrow arrow : getArrows())
            if (arrow.getHitBox().intersects(player.getHitBox())) {
                player.dead();
            }
    }

    public void checkBulletTouched(Player player) {
        for (Bullet bullet : getBullets())
            if (bullet.getHitBox().intersects(player.getHitBox())) {
                player.dead();
            }
    }

    public void checkStarTouched(Player player) {
        for (Star star : getStars())
            if (star.getHitBox().intersects(player.getHitBox())) {
                player.dead();
            }
    }

    public void checkSawTouched(Player player) {
        for (Saw saw : getSaws())
            if (saw.getHitBox().intersects(player.getHitBox())) {
                player.dead();
            }
    }

    public void clear() {
        arrows.clear();
        bullets.clear();
        cannons.clear();
        saws.clear();
        stars.clear();
    }
}
