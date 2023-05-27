package src.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Bullet extends Entity {

    private BufferedImage[] animations;
    private int aniTick, aniIndex, aniSpeed = 50;
    private float speed = 0.2f;
    private int dir;

    public Bullet(float x, float y, int width, int height, int dir) {
        super(x, y, width, height);
        this.dir = dir;
        loadAnimations();
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/bullet.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[5];
            for (int i = 0; i < 5; i++)
                animations[i] = img.getSubimage(i * 60, 0, 60, 52);

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

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5)
                aniIndex = 0;
        }
    }

    public void updatePos() {
        this.x += dir * speed;
    }

    public void UpdateBullet() {
        updatePos();
        updateHitBox();
        updateAnimationTick();
    }

    public void draw(Graphics g) {
        // g.drawRect((int) x, (int) y, width, height);
        // drawHitBox(g);
        g.drawImage(animations[aniIndex], (int) x, (int) y, width, height, null);
    }
}
