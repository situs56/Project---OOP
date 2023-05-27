package src.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.Object.Platform;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Saw extends Entity {

    private BufferedImage[] animations;
    private TrapManager trapManager;
    private Platform platform;

    private float speed = 0.5f;
    private boolean reversed = false;
    private int aniTick, aniIndex, aniSpeed = 50;

    public Saw(float x, float y, int width, int height, TrapManager trapManager) {
        super(x, y, width, height);
        this.trapManager = trapManager;
        loadAnimations();
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/saw.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[5];
            for (int i = 0; i < 4; i++)
                animations[i] = img.getSubimage(i * 45, 0, 45, 44);

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
            if (aniIndex >= 4)
                aniIndex = 0;
        }
    }

    void updateSaw() {
        updatePos();
        updateHitBox();
        updateAnimationTick();
    }

    public void updatePos() {
        if (!reversed) {
            if (x <= 0 && y <= platform.getY() - height) {
                y += speed;
            } else if (x <= game_Width - width && y >= platform.getY() - height) {
                x += speed;
            } else if (x >= game_Width - width && y > 0) {
                y -= speed;
            }
            if (y <= 0)
                reversed = true;
        }

        if (reversed) {
            if (x >= game_Width - width && y <= platform.getY() - height) {
                y += speed;
            } else if (x >= 0 && y >= platform.getY() - height) {
                x -= speed;
            } else if (x <= 0 && y > 0) {
                y -= speed;
            }
            if (y <= 0)
                reversed = false;
        }
    }

    public void draw(Graphics g) {
        // g.drawRect((int) x, (int) y, width, height);
        // drawHitBox(g);
        g.drawImage(animations[aniIndex], (int) x, (int) y, width, height, null);
    }
}
