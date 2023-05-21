package src.Entities;

import java.awt.Graphics;

public class Bullet extends Entity {

    private float speed = 1f;
    private int dir;

    public Bullet(float x, float y, int width, int height, int dir) {
        super(x, y, width, height);
        this.dir = dir;
    }

    public void updatePos() {
        this.x += dir * speed;
    }

    public void UpdateBullet() {
        updatePos();
        updateHitBox();
    }

    public void draw(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
        drawHitBox(g);
    }

    public boolean shouldRemove() {
        return false;
    }
}
