package src.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

    protected static float x;
    protected static float y;
    protected static float speed;
    protected static int width;
    protected static int height;
    protected static Rectangle hitBox;

    public Entity(float x, float y, float speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        hitbox();
    }

    public void hitbox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }

    public void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public static Rectangle getHitBox() {
        return hitBox;
    }

    public static void drawHitBox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(hitBox.x, hitBox.y, width, height);
    }
}
