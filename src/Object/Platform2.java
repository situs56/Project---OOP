package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Platform2 extends GameObject {

    private BufferedImage img;
    private static List<Rectangle> platform2Hitboxes = new ArrayList<>();

    public Platform2(float x, float y, int width, int height) {
        super(x, y, width, height);
        ImportImg();
    }

    public void drawPlatform2(Graphics g) {
        // g.setColor(Color.green);
        // g.fillRect((int) x, (int) y, width, height);
        g.drawImage(img.getSubimage(0, 0, 95, 13), (int) x, (int) y, width, height, null);
    }

    public void ImportImg() {
        InputStream is = getClass().getResourceAsStream("/res/tile.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Rectangle> platform2HitboxList() {
        return platform2Hitboxes;
    }

    public static void addPlatform2Hitbox(Rectangle hitbox) {
        platform2Hitboxes.add(hitbox);
    }
}
