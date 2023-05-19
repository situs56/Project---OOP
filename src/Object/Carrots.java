package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import src.Game.Game;

public class Carrots extends GameObject {
    private ObjectManager objectManager;
    public static final int size = 20;
    public static final Color COLOR = Color.ORANGE;
    private Random random;

    public Carrots(float x, float y, int height, int width) {
        super(x, y, height, width);
        this.random = new Random();
    }

    private void resetPosition() {
        this.x = random.nextInt(Game.game_Width - width);
        this.y = random.nextInt(Game.game_Height - height);
    }

    public void drawCarrot(Graphics g) {
        g.setColor(COLOR);
        g.drawRect((int) x, (int) y, size, size);
        drawHitBox(g);
    }
}
