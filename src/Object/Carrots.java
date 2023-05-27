package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Carrots extends GameObject {

    private ObjectManager objectManager;
    private BufferedImage img;

    public static final int size = 20;

    public static final Color COLOR = Color.ORANGE;

    private Random random;

    public Carrots(float x, float y, int height, int width) {
        super(x, y, height, width);
        this.random = new Random();
        ImportImg();
    }

    private void ImportImg() {
        InputStream is = getClass().getResourceAsStream("/res/carrot.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCarrot() {
        updateHitBox();
    }

    public void drawCarrot(Graphics g) {
        // g.setColor(COLOR);
        // g.drawRect((int) x, (int) y, size, size);
        // drawHitBox(g);
        g.drawImage(img.getSubimage(225, 69, 121, 301), (int) x, (int) (y - 16), width, (int) (height * 2), null);
    }
}
