package src.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.Game.Game;
import src.Gamestate.Playing;
import src.Object.Platform;
import src.Object.Platform2;

public class Player extends Entity {

    public static final int IDLE = 0;
    public static final int Running = 2;
    public static final int Jumping = 3;

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 50;
    private int playerAction;
    private int playerDir = -1;
    private boolean moving = false;
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
        loadAnimations();
    }

    public void updatePlayer() {
        updateAnimationTick();
        setAnimation();
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
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height,
                null);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;

        }
    }

    private int GetSpriteAmount(int playerAction) {
        switch (playerAction) {
            case IDLE:
                return 1;
            case Running:
                return 3;
            case Jumping:
                return 3;
            default:
                return 0;
        }
    }

    private void setAnimation() {
        if (moving)
            playerAction = Running;
        else
            playerAction = IDLE;
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/rabbit.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[6][4];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 55, j * 74, 55, 74);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        this.x = Game.game_Width / 2;
        this.y = Game.game_Height / 2;
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

    public void checkStarTouched() {
        playing.checkStarTouched(this);
    }

    private void checkSawTouched() {
        playing.checkSawTouched(this);
    }

    public void Touching() {
        checkArrowTouched();
        checkBulletTouched();
        checkStarTouched();
        checkSawTouched();
        checkCarrotTouched();
    }

    public void dead() {
        dead = true;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
    }
}
