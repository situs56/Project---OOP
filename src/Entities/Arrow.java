package src.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.util.Random;

import src.Game.Game;
import static src.Game.Game.Tile_Actual_Size;
import static src.Game.Game.Scale;;

public class Arrow extends Entity {

    private BufferedImage img;
    private TrapManager trapManager;
    private Random random;

    private float speed = 0.5f;

    public Arrow(float x, float y, int width, int height, TrapManager trapManager) {
        super(x, y, width, height);
        this.trapManager = trapManager;
        this.random = new Random();
        ImportImg();
    }

    public void updatePos() {
        this.y += speed;
        if (this.y >= Game.game_Height) {
            resetPosition();
        }
    }

    private void resetPosition() {
        this.y = -(random.nextInt(300) + 100);
        this.x = random.nextInt(Game.game_Width - width);
    }

    public void UpdateArrow() {
        updatePos();
        updateHitBox();
    }

    public void draw(Graphics g) {
        // g.drawRect((int) x, (int) y, width, height);
        // drawHitBox(g);
        g.drawImage(img, (int) x, (int) y, width, height, null);
    }

    public void ImportImg() {
        InputStream is = getClass().getResourceAsStream("/res/arrow.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return 0;
    }
}
