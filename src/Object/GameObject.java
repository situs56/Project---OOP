package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {

    protected float x;
    protected float y;
    protected int height;
    protected int width;
    protected Rectangle hitBox;

    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
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

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void drawHitBox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect((int) x, (int) y, width, height);
    }
}
