package src.Entities;

import java.awt.Graphics;
import java.awt.Image;
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
    public static final int Running = 3;
    public static final int JumpingDown = 2;
    public static final int JumpingUp = 1;

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 50;
    private int playerAction;
    private int playerDir = -1;
    private boolean moving = false;
    private Playing playing;
    private Platform platform;
    private BufferedImage image;

    private int currentFrameTime;
    private int updatesPerFrame;
    private int frameIndex;

    private boolean left, right, up;
    private float gravity = 0.002f * Game.Scale;
    private float jumpSpeed = 0.4f * Game.Scale;
    private float airSpeed = 0.0f;
    private float speed = 0.5f;
    private boolean onGround = false;
    private boolean dead = false;
    private String src = "/res/IdleRight.png";;

    public Player(float x, float y, int height, int width, Playing playing) {
        super(x, y, height, width);
        this.playing = playing;
        this.updatesPerFrame = 50;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
    }

    public void updatePlayer() {
        image = getImage(src);
        updateAnimationTick(image);
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
            src = "/res/AnimationLeft.png";
        }
        if (right) {
            Xmove += speed;
            src = "/res/AnimationRight.png";
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
        // g.drawRect((int) x, (int) y, width, height);
        // drawHitBox(g);
        Image subImage = getSubImage();
        g.drawImage(subImage, (int) x - 5, (int) y - 8, 40, 40, null);
    }

    public BufferedImage getImage(String src) {
        InputStream is = getClass().getResourceAsStream(src);

        try {
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.image;
    }

    public Image getSubImage() {
        return this.image.getSubimage(frameIndex * 32, 0, 32, 32);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick(BufferedImage img) {

        if (right) {
            currentFrameTime++;
            if (currentFrameTime >= updatesPerFrame) {
                currentFrameTime = 0;
                frameIndex++;
                if (frameIndex >= img.getWidth() / 32 - 1) {
                    frameIndex = 0;
                }
            }
        } else if (left) {
            currentFrameTime++;
            if (currentFrameTime >= updatesPerFrame) {
                currentFrameTime = 0;
                frameIndex++;
                if (frameIndex >= img.getWidth() / 32 - 1) {
                    frameIndex = 0;
                }
            }
        } else
            frameIndex = 0;
    }

    private void setAnimation() {
        if (moving)
            playerAction = Running;
        else
            playerAction = IDLE;
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
