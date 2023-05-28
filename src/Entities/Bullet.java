package src.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Bullet extends Entity {
    private BufferedImage img;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 50;
    private float speed = 0.2F;
    private int dir;

    public Bullet(float x, float y, int width, int height, int dir) {
        super(x, y, width, height);
        this.dir = dir;
        this.ImportImg();
    }

    public void ImportImg() {
        InputStream is = this.getClass().getResourceAsStream("/res/bullet.png");

        try {
            this.img = ImageIO.read(is);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void updatePos() {
        this.x += dir * speed;
    }

    public void UpdateBullet() {
        updatePos();
        updateHitBox();
    }

    public void draw(Graphics g) {
        // g.drawRect((int) x, (int) y, width, height);
        // drawHitBox(g);
        g.drawImage(img, (int) x, (int) y, width, height, null);
    }
}
