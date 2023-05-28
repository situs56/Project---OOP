package src.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Cannon extends Entity {
    private BufferedImage img;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 300;

    public Cannon(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.ImportImg();
    }

    public void ImportImg() {
        InputStream is = this.getClass().getResourceAsStream("/res/cannon.png");

        try {
            this.img = ImageIO.read(is);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void updateCannon() {
        updateAnimationTick();
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

    public void draw(Graphics g) {
        // g.drawRect((int) x, (int) y, width, height);
        g.drawImage(this.img, (int)this.x, (int)this.y, 32, 32, (ImageObserver)null);
    }
}
