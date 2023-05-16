package src.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import src.Game.Game;
import src.Object.Platform;

public class Player extends Entity {

    private boolean left, right, up, down;
    private float gravity = 0.0005f * Game.Scale;
    private float jumpSpeed = 0.2f * Game.Scale;
    private float airSpeed = 0.0f;
    private float speed = 0.5f;
    private boolean onGround = true;

    public Player(float x, float y, int height, int width) {
        super(x, y, height, width);
    }

    public void updatePlayer() {
        updatePos();
        updateHitBox();
    }

    public void updatePos() {
        float Xmove = 0, Ymove = 0;

        if (left) {
            Xmove -= speed;
        }
        if (right) {
            Xmove += speed;
        }

        if (!onGround) {
            airSpeed += gravity;
            Ymove += airSpeed;
        }

        if (up && onGround) {
            airSpeed = -jumpSpeed;
            onGround = false;
        }

        if (!Solid(x, y, Xmove, Ymove)) {
            this.x += Xmove;
            this.y += Ymove;

            if (this.x < 0) {
                this.x = 0;
            } else if (this.x + width > Game.game_Width) {
                this.x = Game.game_Width - width;
            }

        } else if (Ymove > 0) {
            resetOnGround();
        }
    }

    public void Render(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
        drawHitBox(g);
    }

    public void resetOnGround() {
        onGround = true;
        airSpeed = 0f;
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

    private boolean Solid(float x, float y, float Xmove, float Ymove) {
        Rectangle Predict = new Rectangle((int) (x + Xmove), (int) (y + Ymove), width, height);

        if (Predict.x < 0 || Predict.x >= Game.game_Width)
            return true;

        if (Predict.y < 0 || Predict.y >= Game.game_Height)
            return true;

        if (Predict.intersects(Platform.platformHitbox()))
            return true;
        return false;
    }
}
