package src.Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

import java.awt.Graphics;

public class Star extends Entity {

    private BufferedImage img;

    private float speed = 0.5f;

    public Star(float x, float y, int width, int height) {
        super(x, y, width, height);
        ImportImg();
    }

    public void updateStar() {
        updatePos();
        updateHitBox();
    }

    public void updatePos() {
        x += speed;
        y += speed;

        if (x > game_Width) {
            x = -width;
        } else if (x < -width) {
            x = game_Width;
        }

        if (y > game_Height) {
            y = -height;
        } else if (y < -height) {
            y = game_Height;
        }
    }

    public void draw(Graphics g) {
        // drawHitBox(g);
        g.drawImage(img.getSubimage(20, 22, 185, 178), (int) x, (int) y, width, height, null);
    }

    private void ImportImg() {
        InputStream is = getClass().getResourceAsStream("/res/star.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
