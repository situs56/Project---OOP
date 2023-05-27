package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Carrots extends GameObject {

    private ObjectManager objectManager;

    public static final int size = 32;

    public static final Color COLOR = Color.ORANGE;

    private Random random;

    public Carrots(float x, float y, int height, int width) {
        super(x, y, height, width);
        this.random = new Random();
    }

    public void updateCarrot() {
        updateHitBox();
    }

    public void drawCarrot(Graphics g) {
        g.setColor(COLOR);
        g.drawRect((int) x, (int) y, size, size);
        drawHitBox(g);
    }
}
