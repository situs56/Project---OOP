package src.Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Cannon extends Entity {

    private BufferedImage[] animations;
    private int aniTick, aniIndex, aniSpeed = 300;

    public Cannon(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/cannon.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[5];
            for (int i = 0; i < 5; i++)
                animations[i] = img.getSubimage(5, i * 67, 54, 67);

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
        if ((int) x >= game_Width)
            g.drawImage(animations[aniIndex], (int) x, (int) y, width * -1, height, null);
        else
            g.drawImage(animations[aniIndex], (int) x, (int) y, width, height, null);
    }
}
