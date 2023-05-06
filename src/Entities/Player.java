package src.Entities;

import java.awt.Graphics;

public class Player extends Entity {

    private boolean left, right, up, down;
    private float speed = 0.00005f;

    public Player(float x, float y) {
        super(x, y);
    }

    public void updatePos() {

        if (left && !right) {
            x -= speed;

        } else if (right && !left) {
            x += speed;

        }

        if (up && !down) {
            y -= speed;

        } else if (down && !up) {
            y += speed;

        }
    }

    public void Render(Graphics g) {
        g.drawRect((int) x, (int) y, 200, 200);
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
