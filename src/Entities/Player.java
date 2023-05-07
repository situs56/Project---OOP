package src.Entities;

import java.awt.Graphics;

import src.Object.Platform;

public class Player extends Entity {

    private boolean left, right, up, down;
    

    public Player(float x, float y, float speed, int height, int width) {
        super(x, y, speed, height, width);
    }

    public void updatePlayer() {
        updatePos();
        updateHitBox();
    }

    public void updatePos() {

        if (getHitBox().intersects(Platform.platformHitbox())) {
            y = Platform.getY() - height;
        }

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
        g.drawRect((int) x, (int) y, 64, 64);
        drawHitBox(g);
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
