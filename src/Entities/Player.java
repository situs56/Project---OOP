package src.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import src.Game.Game;
import src.Object.ObjectManager;
import src.Object.Platform;
import src.Object.Platform2;

public class Player extends Entity {

    private boolean left, right, up;
    private float gravity = 0.0005f * Game.Scale;
    private float jumpSpeed = 0.25f * Game.Scale;
    private float airSpeed = 0.0f;
    private float speed = 0.5f;
    private boolean onGround = false;

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
        } else {
            airSpeed = 0f;
        }
    
        if (up && onGround) {
            airSpeed = -jumpSpeed;
            onGround = false;
        }
    
        if (!Solid(x, y, Xmove, Ymove)) {
            this.x += Xmove;
            this.y += Ymove;
    
            onGround = false;
    
            if (this.x < 0) {
                this.x = 0;
            } else if (this.x + width > Game.game_Width) {
                this.x = Game.game_Width - width;
            }
        } else {
            if (Ymove > 0) {
                resetOnGround();
            } else if (Ymove < 0) {
                airSpeed = 0f;
            } else if (Ymove == 0) {
                onGround = true; 
            }
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

        for (Rectangle platform2Hitbox : Platform2.platform2HitboxList()) {
            if (Predict.intersects(platform2Hitbox)) {
                if (Ymove > 0 && y + height + Ymove >= platform2Hitbox.getY()
                        && y + height + Ymove <= platform2Hitbox.getY() + 5) {
                    return true;
                }
            }
        }
        return false;
    }
}
