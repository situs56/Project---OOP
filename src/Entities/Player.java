package src.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import src.Game.Game;
import src.Gamestate.Playing;
import src.Object.Platform;
import src.Object.Platform2;

public class Player extends Entity {

    private Playing playing;
    private Platform platform;

    private boolean left, right, up;
    private float gravity = 0.002f * Game.Scale;
    private float jumpSpeed = 0.4f * Game.Scale;
    private float airSpeed = 0.0f;
    private float speed = 0.5f;
    private boolean onGround = false;
    private boolean dead = false;

    public Player(float x, float y, int height, int width, Playing playing) {
        super(x, y, height, width);
        this.playing = playing;
    }

    public void updatePlayer() {
        updatePos();
        updateHitBox();
        Touching();
        if (dead == true) {
            playing.setGameOver(true);
        }
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

        if (onGround) {
            this.x += Xmove;
        }
    }

    public void Render(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
        drawHitBox(g);
    }

    private boolean Solid(float x, float y, float Xmove, float Ymove) {
        Rectangle Predict = new Rectangle((int) (x + Xmove), (int) (y + Ymove), width, height);

        if (Predict.x < 0 || Predict.x >= Game.game_Width)
            return true;

        if (Predict.y < 0 || Predict.y >= Game.game_Height)
            return true;

        for (Rectangle platformHitbox : Platform.platformHitboxList())
            if (Predict.intersects(platformHitbox))
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

    public void resetOnGround() {
        onGround = true;
        airSpeed = 0f;
    }

    public void resetAll() {
        dead = false;
        this.x = Game.game_Width/2;
        this.y = Game.game_Height/2;
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

    public float getHeight() {
        return height;
    }

    public void checkCarrotTouched() {
        playing.checkCarrotTouched(this);
    }

    public void checkArrowTouched() {
        playing.checkArrowTouched(this);
    }

    public void checkBulletTouched() {
        playing.checkBulletTouched(this);
    }

    public void checkBallTouched() {
        playing.checkBallTouched(this);
    }

    private void checkSawTouched() {
        playing.checkSawTouched(this);
    }

    public void Touching() {
        checkArrowTouched();
        checkBulletTouched();
        checkBallTouched();
        checkSawTouched();
        checkCarrotTouched();
    }

    public void dead() {
        dead = true;
    }
}